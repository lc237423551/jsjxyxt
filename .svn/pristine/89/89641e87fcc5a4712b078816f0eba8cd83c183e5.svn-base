package com.java.listener;

import java.util.HashSet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.java.entity.User;

public class CountLineListener implements HttpSessionListener, ServletContextListener,
		HttpSessionAttributeListener {
	// 获取application对象
	ServletContext application = null;

	HttpSession session = null;

	HashSet<User> set = new HashSet<User>(); // 存放在线用户的用户名

	int count = 0;

	public void sessionCreated(HttpSessionEvent arg0) {
		//System.out.println("新的会话:");
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		session = arg0.getSession();
		if (session.getAttribute("user")!= null) {
			//System.out.println("用户注销....");
			User user = (User) session.getAttribute("user");
			set = (HashSet<User>) application.getAttribute("registerUser");
			set.remove(user);
			application.setAttribute("registerUser", set);
			count = (Integer) application.getAttribute("userCount");
			if (count > 0) {
				count--;
			}
			application.setAttribute("userCount", count);
		} 
		
		if(session==null){
			//System.out.println("用户超时....");
			count = (Integer) application.getAttribute("userCount");
			if (count > 0) {
				count--;
			}
			application.setAttribute("userCount", count);
		}
	}

	public void attributeAdded(HttpSessionBindingEvent arg0) {
		session = arg0.getSession();
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			set = (HashSet<User>) application.getAttribute("registerUser");
			// 新用户登陆
			if (!set.contains(user)) {
				set.add(user);
				count = (Integer) application.getAttribute("userCount");
				count++;
				application.setAttribute("userCount", count);
			}
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent arg0) {
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// tomcat启动时创建用于保存人数的application对象
		application = arg0.getServletContext();
		application.setAttribute("userCount", new Integer(0));// 总在线人数
		application.setAttribute("registerUser", new HashSet<String>());// 在线用户名
	}
}
