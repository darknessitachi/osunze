package com.skycity.framework.interceptor.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skycity.entity.Roles;
import com.skycity.entity.User;
import com.skycity.framework.Constant;
import com.skycity.service.RolesService;
import com.skycity.service.UserService;

@Service
public class ShiroUserNameRealm extends AuthorizingRealm {
	@Autowired
    private UserService userService;  
	@Autowired
	private RolesService roleService;
	@Override
	/** 
     * 为当前登录的Subject授予角色和权限 
     * 该方法的调用时机为需授权资源被访问时 
     * 
     * 
     */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String currentUserName = (String)principals.getPrimaryPrincipal();  
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        //实际中可能会像上面注释的那样从数据库取得  
        if(null!=currentUserName){  
        	List<Roles> roles = roleService.queryRolesByUserName(currentUserName);
        	roles.forEach(role->{
        		simpleAuthorInfo.addRole(role.getName());
//        		role.getPermissions().forEach(p->{
//        			simpleAuthorInfo.addStringPermission(p.getName());
//        		});
        	});
            //添加权限  
            return simpleAuthorInfo;  
        }
		return null;
	}

	@Override
	/** 
     * 验证当前登录的Subject 
     * @see 调用时机为LoginController.login()方法中执行Subject.login()时 
     */  
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		System.out.println("验证用户名登陆");
		String username = (String) token.getPrincipal(); // 得到用户名
		User user = userService.querySingleUser(username);
		if(user==null){
			throw new UnknownAccountException("No account found for user:"+username);
		}
		if(Constant.USER_FORBIDDEN == user.getStatus()){
			throw new DisabledAccountException("Account is Disabled!");
		}
		if(Constant.USER_LOCK == user.getStatus()){
			throw new LockedAccountException("Account is Locked!");
		}
//		this.setSession("userName", user.getUserName());
		return new SimpleAuthenticationInfo(username, user.getUserPassword(),ByteSource.Util.bytes(user.getPasswdKey().getBytes()),getName());
	}

	/** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * @see  比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */  
//    private void setSession(Object key, Object value){  
//        Subject currentUser = SecurityUtils.getSubject();  
//        if(null != currentUser){  
//            Session session = currentUser.getSession();  
//            if(null != session){  
//                session.setAttribute(key, value);  
//            }
//        }  
//    }  
}
