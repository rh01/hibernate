package com.shh.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// ��ʹ��hibernate����Ŀʱ��һ��Ҫ��ֻ֤��һ��sessionFactory
//һ�����ݿ��Ӧһ��sessionFactory
final public class MySessionFactory {

	private static SessionFactory sessionFactory = null;
	private  MySessionFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		MySessionFactory.sessionFactory = sessionFactory;
	}

	static {
		setSessionFactory(new  Configuration().configure().buildSessionFactory());
	}
	
	
}
