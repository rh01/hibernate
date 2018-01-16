package com.shh.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

final public class HibernateUtil {

	private static SessionFactory sessionFactory = null;
	// ʹ���ֲ߳̾�ģʽ
	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	
	private HibernateUtil(){};
	
	static {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	//��ȡȫ�µ�session
	public static Session openSession() {
		return sessionFactory.openSession();
	}
	
	//��ȡ���߳��������session
	public static Session getCurrentSession() {
		Session session = threadLocal.get();
		//��ë���Ƿ�õ�
		if(session==null){
			session = sessionFactory.openSession();
			//��session�������õ�threadLocal���൱�ڸ�session���̰߳�
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
