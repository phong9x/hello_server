package org.trams.hello.web.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.trams.hello.bean.UserItem;

public class Login {
	
	private static HashMap<Integer, HttpSession> userLoginSessions = new HashMap<>();
	private static HashMap<Integer, HttpSession> counselorLoggedIn = new HashMap<>();

	public static void  saveUser(UserItem user,HttpSession session) {
		session.setAttribute("user", user);
	}
	
	public static void  saveMobile(UserItem user,HttpSession session) {
		session.setAttribute("mobile", user);
		userLoginSessions.put(user.getId(), session);
	}
	
	public static String checkUserLogin(HttpSession session) {
		if (session.getAttribute("user") != null && session.getAttribute("user")!="" ) {
			return "1";
		} 
			return "0";
	}
	
	public static String checkMobileLogin(HttpSession session) {
		if (session.getAttribute("mobile") != null && session.getAttribute("mobile")!="" ) {
			return "1";
		} 
			return "0";
	}
	
	public static UserItem  getUserLogin(HttpSession session) {
		UserItem user =(UserItem) session.getAttribute("user");
		return user;
	}
	
	public static UserItem  getMobileLogin(HttpSession session) {
		UserItem user =(UserItem) session.getAttribute("mobile");
		return user;
	}

	public  static void   removeUserLogin(HttpSession session) {
		session.setAttribute("user",null);
		
	}
	
	public  static void   removeMobileLogin(HttpSession session) {
		UserItem ui = getMobileLogin(session);
		session.setAttribute("mobile",null);
		userLoginSessions.remove(ui.getId());
	}
	
	public static boolean checkAdminLogin(HttpSession session) {
		if (session.getAttribute("admin") != null && session.getAttribute("admin")!="") {
			return true;
		} 
			return false;
	}
	
	public static void  saveAdmin(UserItem user,HttpSession session) {
		session.setAttribute("admin", user);
	}
	
	public static UserItem  getAdminLogin(HttpSession session) {
		UserItem user =(UserItem) session.getAttribute("admin");
		return user;
	}
	
	public  static void   removeAdminLogin(HttpSession session) {
		session.setAttribute("admin",null);
		
	}
	
	// COMPANY
	
	public static void  saveCompany(UserItem user,HttpSession session) {
		session.setAttribute("company", user);
	}
	
	public static UserItem  getCompanyLogin(HttpSession session) {
		UserItem user =(UserItem) session.getAttribute("company");
		return user;
	}
	
	public  static void   removeCompanyLogin(HttpSession session) {
		session.setAttribute("company",null);
		
	}
	
	public static boolean checkCompanyLogin(HttpSession session) {
		if (session.getAttribute("company") != null && session.getAttribute("company")!="") {
			return true;
		} 
			return false;
	}
	
	// Counseling center
	public static void  saveCounselingCenter(UserItem user,HttpSession session) {
		session.setAttribute("counselingCenter", user);
	}
	
	public static UserItem  getCounselingCenterLogin(HttpSession session) {
		UserItem user =(UserItem) session.getAttribute("counselingCenter");
		return user;
	}
	
	public  static void   removeCounselingCenterLogin(HttpSession session) {
		session.setAttribute("counselingCenter",null);
		
	}
	
	public static boolean checkCounselingCenterLogin(HttpSession session) {
		if (session.getAttribute("counselingCenter") != null && session.getAttribute("counselingCenter")!="") {
			return true;
		} 
			return false;
	}
	
	
	
	public static void saveLoggedInUser(UserItem user, HttpSession session) {
		session.setAttribute("loggedInUser", user);
		counselorLoggedIn.put(user.getId(), session);
	}
	
	public static void removeLoggedInUser(HttpSession session) {
		try {
			Integer loggedInUser = ((UserItem) session.getAttribute("loggedInUser")).getId();

			counselorLoggedIn.remove(loggedInUser);
			session.removeAttribute("loggedInUser");
		} catch (Exception e) {

		}
	}
	
	public static UserItem getLoggedInUser(HttpSession session) {
		return (UserItem) session.getAttribute("loggedInUser");
	}

	public static Map<Integer, HttpSession> getOnlineCounselor() {
		return counselorLoggedIn;
	}

}