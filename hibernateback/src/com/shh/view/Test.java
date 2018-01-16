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
		// 我们使用hibernate完成对crude操作 这里只看见对象不再看见表了
		// 现在我们不是有service直接测试
		
		addEmployee();

		// 修改用户
		//1.创建configuration对象
		//Configuration configuration = new  Configuration().configure("hibernate.cfg.xml");
		//2.创建会话工厂,重量级的对象，保证单台，
		//3.创建session
		//3.创建一个交易对象
		//5.修改雇员属性
		//6.保存会话
		//7.交易提交
		//8.关闭会话
		// 修改一个已有的用户
		updateEmployee();
		
		//删除一个已有的用户
		deleteEmployee();
		

	}


	public static void deleteEmployee() {
		// 删除
		//获取一个会话
		Session session = MySessionFactory.getSessionFactory().openSession();
		//事务
		Transaction ts = session.beginTransaction();
		//获取该雇员
		Employee emp = (Employee)session.load(Employee.class, 2);
		//删除
		session.delete(emp);
		ts.commit();
		session.close();
	}


	public static void updateEmployee() {
		//获取一个会话
		Session session = MySessionFactory.getSessionFactory().openSession();
		Transaction ts = session.beginTransaction();
		//修改一个用户
		//1.获取一个用户
		//load使是通过主键属性，获取对象实例
		Employee emp = (Employee)session.load(Employee.class, 2);
		emp.setName("申恒恒");
		emp.setEmail("abc@readailib.com");
		ts.commit();
		session.close();
	}
	

	public static void addEmployee() {
		// 1. 直接创建configurationm,该文件用于读取hibernate.cfg.xml文件
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		
		// 2.创建SessionFactory这是一个绘画工厂，是一个重量级哦对象
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		
		//3. 创建Session相当于jdbc
		Session session = sessionFactory.openSession();
		//针对hibernate而言，要求程序员，在进行增删改出的时候使用事务提交
		Transaction transaction = session.beginTransaction();
		//4. 添加一个雇员
		Employee employee = new Employee();
		employee.setName("shh2");
		employee.setEmail("shh@readailib.com");
		employee.setHiredate(new Date());
		
		// insert................
		//保存
		session.save(employee);
		// 提交
		transaction.commit();
		session.close();
	}

}
