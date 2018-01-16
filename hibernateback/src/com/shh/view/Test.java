package com.shh.view;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.shh.domain.Employee;
import com.shh.util.MySessionFactory;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ����ʹ��hibernate��ɶ�crude���� ����ֻ���������ٿ�������
		// �������ǲ�����serviceֱ�Ӳ���
		
		addEmployee();

		// �޸��û�
		//1.����configuration����
		//Configuration configuration = new  Configuration().configure("hibernate.cfg.xml");
		//2.�����Ự����,�������Ķ��󣬱�֤��̨��
		//3.����session
		//3.����һ�����׶���
		//5.�޸Ĺ�Ա����
		//6.����Ự
		//7.�����ύ
		//8.�رջỰ
		// �޸�һ�����е��û�
		updateEmployee();
		
		//ɾ��һ�����е��û�
		deleteEmployee();
		

	}


	public static void deleteEmployee() {
		// ɾ��
		//��ȡһ���Ự
		Session session = MySessionFactory.getSessionFactory().openSession();
		//����
		Transaction ts = session.beginTransaction();
		//��ȡ�ù�Ա
		Employee emp = (Employee)session.load(Employee.class, 2);
		//ɾ��
		session.delete(emp);
		ts.commit();
		session.close();
	}


	public static void updateEmployee() {
		//��ȡһ���Ự
		Session session = MySessionFactory.getSessionFactory().openSession();
		Transaction ts = session.beginTransaction();
		//�޸�һ���û�
		//1.��ȡһ���û�
		//loadʹ��ͨ���������ԣ���ȡ����ʵ��
		Employee emp = (Employee)session.load(Employee.class, 2);
		emp.setName("����");
		emp.setEmail("abc@readailib.com");
		ts.commit();
		session.close();
	}
	

	public static void addEmployee() {
		// 1. ֱ�Ӵ���configurationm,���ļ����ڶ�ȡhibernate.cfg.xml�ļ�
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		
		// 2.����SessionFactory����һ���滭��������һ��������Ŷ����
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		
		//3. ����Session�൱��jdbc
		Session session = sessionFactory.openSession();
		//���hibernate���ԣ�Ҫ�����Ա���ڽ�����ɾ�ĳ���ʱ��ʹ�������ύ
		Transaction transaction = session.beginTransaction();
		//4. ���һ����Ա
		Employee employee = new Employee();
		employee.setName("shh2");
		employee.setEmail("shh@readailib.com");
		employee.setHiredate(new Date());
		
		// insert................
		//����
		session.save(employee);
		// �ύ
		transaction.commit();
		session.close();
	}

}
