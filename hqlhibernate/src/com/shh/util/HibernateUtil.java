package com.shh.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

final public class HibernateUtil {

	private static SessionFactory sessionFactory = null;
	// 使用线程局部模式
	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	
	private HibernateUtil(){};
	
	static {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	//获取全新的session
	public static Session openSession() {
		return sessionFactory.openSession();
	}
	
	//获取和线程相关联的session
	public static Session getCurrentSession() {
		Session session = threadLocal.get();
		//怕毛短是否得到
		if(session==null){
			session = sessionFactory.openSession();
			//把session对象设置到threadLocal，相当于该session和线程绑定
			threadLocal.set(session);
		}
		return session;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		HibernateUtil.sessionFactory = sessionFactory;
	}
	
}
