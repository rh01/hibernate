package com.shh.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

final public class HibernateUtil {

	private static SessionFactory sessionFactory = null;
	// ʹ���ֲ߳̾�ģʽ
	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

	private HibernateUtil() {
	};

	static {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	// ��ȡȫ�µ�session
	public static Session openSession() {
		return sessionFactory.openSession();
	}

	// ��ȡ���߳��������session
	public static Session getCurrentSession() {
		Session session = threadLocal.get();
		// ��ë���Ƿ�õ�
		if (session == null) {
			session = sessionFactory.openSession();
			// ��session�������õ�threadLocal���൱�ڸ�session���̰߳�
			threadLocal.set(session);
		}
		return session;

	}
	//ͳһ���޸ĺ�ɾ��(������hql)
	public  static void executeUpdate(String hql, String[] parameters){
		Session s = null;
		Transaction ts = null;
	

		try {
			s = getCurrentSession();
			ts = s.beginTransaction();

			Query query = s.createQuery(hql);
			// ���ж��Ƿ��в���Ҫ����
			if (parameters != null && parameters.length > 0) {
				for (int i = 0; i < parameters.length; i++) {
					query.setString(i, parameters[i]);
				}
			}
			int i = query.executeUpdate();
			ts.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (ts != null) {
				ts.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			if (s != null && s.isOpen()) {
				s.close();
			}
		}
		
	}
	
	
	
	
	//ͳһ����ӷ���
	public  static void save(Object obj){
		Session s = null;
		Transaction ts = null;
		
		try {
			s = openSession();
			ts = s.beginTransaction();
			s.save(obj);
			ts.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			if(ts!=null){
				ts.rollback();
			}
			throw new RuntimeException(e.getMessage());
		}finally{
			if(s!=null&&s.isOpen()){
				s.close();
			}
		}
		
	}

	//�ṩһ��ͳһ�Ĳ�ѯ��������Ҫ��ҳ��hql��ʽfrom��where����
	public static List executeQueryByPages(String hql, String[] parameters, int pageSize, int pageNow){
		Session s = null;
		Transaction ts = null;
		List list = null;

		try {
			s = getCurrentSession();
			ts = s.beginTransaction();

			Query query = s.createQuery(hql);
			// ���ж��Ƿ��в���Ҫ����
			if (parameters != null && parameters.length > 0) {
				for (int i = 0; i < parameters.length; i++) {
					query.setString(i, parameters[i]);
				}
			}
			query.setFirstResult((pageNow - 1)*pageSize);
			query.setMaxResults(pageSize);
			ts.commit();
			list = query.list();
			

		} catch (Exception e) {
			// TODO: handle exception
			if (ts != null) {
				ts.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			if (s != null && s.isOpen()) {
				s.close();
			}
		}
		return list;
	}
	
	// �ṩһ��ͳһ�Ĳ�ѯ����hql��ʽfrom��where����
	public static List executeQuery(String hql, String[] parameters) {
		Session s = null;
		Transaction ts = null;
		List list = null;

		try {
			s = getCurrentSession();
			ts = s.beginTransaction();

			Query query = s.createQuery(hql);
			// ���ж��Ƿ��в���Ҫ����
			if (parameters != null && parameters.length > 0) {
				for (int i = 0; i < parameters.length; i++) {
					query.setString(i, parameters[i]);
				}
			}
			list = query.list();
			ts.commit();
			

		} catch (Exception e) {
			// TODO: handle exception
			if (ts != null) {
				ts.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			if (s != null && s.isOpen()) {
				s.close();
			}
		}
		return list;

	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		HibernateUtil.sessionFactory = sessionFactory;
	}

}
