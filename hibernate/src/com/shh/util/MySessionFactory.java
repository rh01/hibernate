package com.shh.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// 在使用hibernate做项目时，一定要保证只有一个sessionFactory
//一个数据库对应一个sessionFactory
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
