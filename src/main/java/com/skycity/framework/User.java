package com.skycity.framework;

import java.io.Serializable;

import com.skycity.framework.collection.Mapx;
import com.skycity.framework.i18n.LangMapping;

public class User {
	private static ThreadLocal<User.UserData> current = new ThreadLocal<User.UserData>();

	public static String getUserName() {
		return getCurrent().UserName;
	}

	public static void setUserName(String UserName) {
		getCurrent().setUserName(UserName);
	}

	public static int getValueCount() {
		return getCurrent().size();
	}

	public static Object getValue(Object key) {
		return getCurrent().get(key);
	}

	public static Mapx<String, Object> getValues() {
		return getCurrent();
	}

	public static void setValue(String key, Object value) {
		Mapx<String, Object> map = getCurrent();
		synchronized (map) {
			map.put(key, value);
		}
	}

	public static boolean isLogin() {
		return getCurrent().isLogin;
	}

	public static void setLogin(boolean isLogin) {
		getCurrent().isLogin = isLogin;
	}

	public static void setCurrent(UserData user) {
		current.set(user);
	}

	public static UserData getCurrent() {
		UserData ud = current.get();
		if(ud==null){
			ud = new User.UserData();
			setCurrent(ud);
		}
		return ud;
	}

	public static void clear(){
		current.remove();
	}
//	protected static void cacheUser(UserData u) {
//		if ((getCurrent() != u) || (getCurrent() == null)) {
//			return;
//		}
//		try {
//			File dir = new File(Config.getContextRealPath() + "WEB-INF/cache/");
//			if (!dir.exists()) {
//				dir.mkdirs();
//			}
//			if (u.getSessionID() == null) {
//				return;
//			}
//			FileOutputStream f = new FileOutputStream(
//					Config.getContextRealPath() + "WEB-INF/cache/"
//							+ u.getSessionID());
//			ObjectOutputStream s = new ObjectOutputStream(f);
//			s.writeObject(u);
//			s.flush();
//			s.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	public static UserData getCachedUser(String sessionID) {
//		try {
//			File in = new File(Config.getContextRealPath() + "WEB-INF/cache/"
//					+ sessionID);
//			if (in.exists()) {
//				ObjectInputStream s = new ObjectInputStream(
//						new FileInputStream(in));
//				Object o = s.readObject();
//				if (UserData.class.isInstance(o)) {
//					s.close();
//					in.delete();
//					UserData u = (UserData) o;
//					return u;
//				}
//				s.close();
//			}
//		} catch (Exception e) {
//			LogUtil.warn("getCachedUser() failed");
//		}
//		return null;
//	}

	public static void destory() {
		setCurrent(new UserData());
	}

	public static String getSessionID() {
		return getCurrent().SessionID;
	}

	protected static void setSessionID(String sessionID) {
		getCurrent().SessionID = sessionID;
	}

	public static String getLanguage() {
		return getCurrent().Language;
	}

	public static void setLanguage(String language) {
		getCurrent().Language = language;
	}

	public static class UserData extends Mapx<String, Object> implements
			Serializable {
		private static final long serialVersionUID = 1L;
		private String UserName;
		private boolean isLogin = false;
		private String SessionID;

		private String Language = LangMapping.getInstance().getDefaultLanguage();

		public String getUserName() {
			return this.UserName;
		}

		public void setUserName(String userName) {
			this.UserName = userName;
		}

		public boolean isLogin() {
			return this.isLogin;
		}

		public void setLogin(boolean isLogin) {
			this.isLogin = isLogin;
		}

		public String getSessionID() {
			return this.SessionID;
		}

		public void setSessionID(String sessionID) {
			this.SessionID = sessionID;
		}

		public String getLanguage() {
			return this.Language;
		}

		public void setLanguage(String language) {
			this.Language = language;
		}
	}
}