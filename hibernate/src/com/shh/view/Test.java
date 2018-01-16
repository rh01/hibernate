package com.shh.view;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;

import com.shh.domain.Employee;
import com.shh.util.HibernateUtil;
import com.shh.util.MySessionFactory;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ����ʹ��hibernate��ɶ�crude���� ����ֻ���������ٿ�������
		// �������ǲ�����serviceֱ�Ӳ���

		// addEmployee();
		/*
		 * Session s1 = HibernateUtil.openSession(); Session s2 =
		 * HibernateUtil.openSession();
		 * 
		 * System.out.println(s1.hashCode() + " " + s2.hashCode());
		 */

		// �޸��û�
		// 1.����configuration����
		// Configuration configuration = new
		// Configuration().configure("hibernate.cfg.xml");
		// 2.�����Ự����,�������Ķ��󣬱�֤��̨��
		// 3.����session
		// 3.����һ�����׶���
		// 5.�޸Ĺ�Ա����
		// 6.����Ự
		// 7.�����ύ
		// 8.�رջỰ
		// �޸�һ�����е��û�
		// updateEmployee();

		// ɾ��һ�����е��û�
		// deleteEmployee();

		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = null;

		try {
			ts = session.beginTransaction();
			// do some work
			// [where��������������������������Ҳ�����Ǳ���ֶΣ�����hibernate�涨�����ǻ���ʹ�����������
			// Query query = session.createQuery("from Employee where
			// aaaid=10");
			// ͨ��list������ȡ���,���list���Զ��Ľ���װΪ�����domain����
			// ������jdbc���ж��η�װ�Ĺ���û��
			/*
			 * List<Employee> list = query.list(); for(Employee e:list ){
			 * System.out.println(e.getName() + " " + e.getEmail()); }
			 */
			Criteria cri = session.createCriteria(Employee.class).setMaxResults(2).addOrder(Order.desc("id"));
			List<Employee> list = cri.list();
			for (Employee e : list) {
				System.out.println(e.getAaaid());
			}

			ts.commit();
		} catch (Exception e) {
			if (ts != null)
				ts.rollback();
			throw e;
		} finally {
			session.close();
		}

	}

	public static void deleteEmployee() {
		// ɾ��
		// ��ȡһ���Ự
		Session session = MySessionFactory.getSessionFactory().openSession();
		// ����
		Transaction ts = session.beginTransaction();
		// ��ȡ�ù�Ա
		Employee emp = (Employee) session.load(Employee.class, 4);
		// ɾ��
		session.delete(emp);
		ts.commit();
		session.close();
	}

	public static void updateEmployee() {
		// ��ȡһ���Ự
		/*
		 * Session session = MySessionFactory.getSessionFactory().openSession();
		 * Transaction ts = session.beginTransaction(); //�޸�һ���û� //1.��ȡһ���û�
		 * //loadʹ��ͨ���������ԣ���ȡ����ʵ�� Employee emp =
		 * (Employee)session.load(Employee.class, 4); emp.setName("����");
		 * emp.setEmail("abc@readailib.com"); // ����ع��������hibernate�лع�����
		 * 
		 * ts.commit(); session.close();
		 */
		// ģ��
		// �����hibernate�лع�������
		Session session = MySessionFactory.getSessionFactory().openSession();
		Transaction ts = null;

		try {
			ts = session.beginTransaction();
			// do some work
			Employee emp = (Employee) session.load(Employee.class, 2);
			emp.setName("����");
			emp.setEmail("abc@readailib.com");
			int i = 9 / 0;
			ts.commit();
		} catch (Exception e) {
			if (ts != null)
				ts.rollback();
			throw e;
		} finally {
			session.close();
		}

	}

	public static void addEmployee() {
		// 1. ֱ�Ӵ���configurationm,���ļ����ڶ�ȡhibernate.cfg.xml�ļ�
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

		// 2.����SessionFactory����һ���滭��������һ��������Ŷ����
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		// 3. ����Session�൱��jdbc
		Session session = sessionFactory.openSession();
		// ���hibernate���ԣ�Ҫ�����Ա���ڽ�����ɾ�ĳ���ʱ��ʹ�������ύ
		Transaction transaction = session.beginTransaction();
		// 4. ���һ����Ա
		Employee employee = new Employee();
		employee.setName("shh2");
		employee.setEmail("shh@readailib.com");
		employee.setHiredate(new Date());

		// insert................
		// ����
		session.save(employee);
		// �ύ
		transaction.commit();
		session.close();
	}

}
