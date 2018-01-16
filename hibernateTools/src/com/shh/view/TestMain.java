package com.shh.view;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.shh.domain.Employee;
import com.shh.util.HibernateUtil;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Session session = null;
		Transaction ts = null;
		try {
			session = HibernateUtil.getCurrentSession();
			ts = session.beginTransaction();
			Employee e = new Employee();
			e.setName("ggg");
			e.setEmail("ggg@readailib.com");
			e.setHiredate(new Date());
			session.persist(e);
			ts.commit();
		} catch (Exception e) {
		  if (ts!=null) {
			ts.rollback();
		}
		  throw new RuntimeException(e.getMessage());
		} finally {
			// TODO: handle finally clause
			if(session!=null && session.isOpen()){
				session.close();
			}
		}

	}

}
