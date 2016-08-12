package com.skycity.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skycity.entity.Permission;
import com.skycity.entity.Roles;
import com.skycity.framework.Constant;
import com.skycity.framework.UIFacade;
import com.skycity.framework.User;
import com.skycity.framework.i18n.LangMapping;
import com.skycity.framework.utility.EncryptUtil;
import com.skycity.service.RolesService;
import com.skycity.service.UserService;

/**
 * 进行管理后台框架界面的类
 * 
 * @version 1.0v
 */
@Controller
@RequestMapping("/Login/")
public class LoginController extends UIFacade<User> {
	@Autowired
	private UserService userService;
	@Autowired
	private RolesService roleService;
	/**
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		initRequest(request);
		response.setCharacterEncoding("UTF-8");
		String userName = $V("UserName");
		String password = $V("Password");
		try{
			UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
			Subject subject = SecurityUtils.getSubject();
			System.out.println(subject.getSession().getAttribute(Constant.SESSION_MENULIST));
			subject.login(token);
			Response.setSuccessMessage(LangMapping.get("Platform.LoginSuccess"));
		}catch(UnknownAccountException ex){
			Response.setFailedMessage("错误的用户名/密码");
		}catch(IncorrectCredentialsException ex){
			Response.setFailedMessage("错误的用户名/密码");
		}catch(ExcessiveAttemptsException ex){
			Response.setFailedMessage(ex.getMessage());
		}catch(AuthenticationException ex){
			Response.setFailedMessage("错误的用户名/密码");
		}catch(Exception ex){
			Response.setFailedMessage(ex.getMessage());
		}
		response.getWriter().write(Response.toJson());
	}
	
	@RequestMapping("/admin/index")
	public ModelAndView admin_index(){
		ModelAndView mv = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		String userName = (String)subject.getPrincipal();
		Session session = subject.getSession();
//		if(null == session.getAttribute(Constant.SESSION_MENULIST)){
			List<Roles> roles = roleService.queryRolesByUserName(userName);
			List<Permission> menuList = new ArrayList<Permission>();
			roles.forEach(r->{
				r.getPermissions().stream().filter(s->"1".equals(s.getType())).forEach(s->{
					menuList.add(s);
				});
			});
			session.setAttribute(Constant.SESSION_MENULIST, menuList);			//菜单权限放入session中
			mv.addObject("menuList", menuList);
//		}else{
//			mv.addObject("menuList", session.getAttribute(Constant.SESSION_MENULIST));
//		}
		mv.setViewName("/Application");
		return mv;
	}
	@RequestMapping("changePassword")
	public void changePassword(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		initRequest(request);
		response.setCharacterEncoding("UTF-8");
		com.skycity.entity.User users = userService.querySingleUser($V("userName"));
		String password = EncryptUtil.decryptMode(users.getPasswdKey(),users.getUserPassword());
		if (!password.equalsIgnoreCase($V("OldPassword"))) {
			Response.setFailedMessage(LangMapping.get("Common.WrongOldPassword"));
			response.getWriter().write(Response.toJson());
		} else {
			String key=EncryptUtil.createSecretKey();
			String pwd=EncryptUtil.encryptMode(key, $V("Password"));
			users.setPasswdKey(key);
			users.setUserPassword(pwd);
			userService.changePwd(users);
			Response.setSuccessMessage(LangMapping.get("Common.ChangePwdSuccess"));
			response.getWriter().write(Response.toJson());
		}
	}
	
	@RequestMapping("logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.getSession().removeAttribute("_ZVING_USER");
		Response.setSuccessMessage(LangMapping.get("Platform.LogoutSuccess"));
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		session.removeAttribute(Constant.SESSION_MENULIST);
		subject.logout();
		response.getWriter().write(Response.toJson());
	}
	
}