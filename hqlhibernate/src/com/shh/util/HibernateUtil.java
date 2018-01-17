package com.shh.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

final public class HibernateUtil {

	private static SessionFactory sessionFactory = null;
	// 使用线程局部模式
	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

	private HibernateUtil() {
	};

	static {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	// 获取全新的session
	public static Session openSession() {
		return sessionFactory.openSession();
	}

	// 获取和线程相关联的session
	public static Session getCurrentSession() {
		Session session = threadLocal.get();
		// 怕毛短是否得到
		if (session == null) {
			session = sessionFactory.openSession();
			// 把session对象设置到threadLocal，相当于该session和线程绑定
			threadLocal.set(session);
		}
		return session;

	}
	//统一的修改和删除(批量的hql)
	public  static void executeUpdate(String hql, String[] parameters){
		Session s = null;
		Transaction ts = null;
	

		try {
			s = getCurrentSession();
			ts = s.beginTransaction();

			Query query = s.createQuery(hql);
			// 先判断是否有参数要传入
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
	
	
	
	
	//统一的添加方法
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

	//提供一个统一的查询方并且需要分页，hql形式from类where条件
	public static List executeQueryByPages(String hql, String[] parameters, int pageSize, int pageNow){
		Session s = null;
		Transaction ts = null;
		List list = null;

		try {
			s = getCurrentSession();
			ts = s.beginTransaction();

			Query query = s.createQuery(hql);
			// 先判断是否有参数要传入
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
	
	// 提供一个统一的查询方，hql形式from类where条件
	public static List executeQuery(String hql, String[] parameters) {
		Session s = null;
		Transaction ts = null;
		List list = null;

		try {
			s = getCurrentSession();
			ts = s.beginTransaction();

			Query query = s.createQuery(hql);
			// 先判断是否有参数要传入
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
