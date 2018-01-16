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
		// 我们使用hibernate完成对crude操作 这里只看见对象不再看见表了
		// 现在我们不是有service直接测试

		// addEmployee();
		/*
		 * Session s1 = HibernateUtil.openSession(); Session s2 =
		 * HibernateUtil.openSession();
		 * 
		 * System.out.println(s1.hashCode() + " " + s2.hashCode());
		 */

		// 修改用户
		// 1.创建configuration对象
		// Configuration configuration = new
		// Configuration().configure("hibernate.cfg.xml");
		// 2.创建会话工厂,重量级的对象，保证单台，
		// 3.创建session
		// 3.创建一个交易对象
		// 5.修改雇员属性
		// 6.保存会话
		// 7.交易提交
		// 8.关闭会话
		// 修改一个已有的用户
		// updateEmployee();

		// 删除一个已有的用户
		// deleteEmployee();

		Session session = HibernateUtil.getCurrentSession();
		Transaction ts = null;

		try {
			ts = session.beginTransaction();
			// do some work
			// [where后面的条件可以是类的属性名，也可以是表的字段，按照hibernate规定，我们还是使用类的属性名
			// Query query = session.createQuery("from Employee where
			// aaaid=10");
			// 通过list方法获取结果,这个list会自动的将封装为对象的domain对象
			// 所以们jdbc进行二次封装的工作没有
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
		// 删除
		// 获取一个会话
		Session session = MySessionFactory.getSessionFactory().openSession();
		// 事务
		Transaction ts = session.beginTransaction();
		// 获取该雇员
		Employee emp = (Employee) session.load(Employee.class, 4);
		// 删除
		session.delete(emp);
		ts.commit();
		session.close();
	}

	public static void updateEmployee() {
		// 获取一个会话
		/*
		 * Session session = MySessionFactory.getSessionFactory().openSession();
		 * Transaction ts = session.beginTransaction(); //修改一个用户 //1.获取一个用户
		 * //load使是通过主键属性，获取对象实例 Employee emp =
		 * (Employee)session.load(Employee.class, 4); emp.setName("申恒恒");
		 * emp.setEmail("abc@readailib.com"); // 事务回滚，如何在hibernate中回滚事务
		 * 
		 * ts.commit(); session.close();
		 */
		// 模板
		// 如何在hibernate中回滚事务与
		Session session = MySessionFactory.getSessionFactory().openSession();
		Transaction ts = null;

		try {
			ts = session.beginTransaction();
			// do some work
			Employee emp = (Employee) session.load(Employee.class, 2);
			emp.setName("申恒恒");
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
		// 1. 直接创建configurationm,该文件用于读取hibernate.cfg.xml文件
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

		// 2.创建SessionFactory这是一个绘画工厂，是一个重量级哦对象
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		// 3. 创建Session相当于jdbc
		Session session = sessionFactory.openSession();
		// 针对hibernate而言，要求程序员，在进行增删改出的时候使用事务提交
		Transaction transaction = session.beginTransaction();
		// 4. 添加一个雇员
		Employee employee = new Employee();
		employee.setName("shh2");
		employee.setEmail("shh@readailib.com");
		employee.setHiredate(new Date());

		// insert................
		// 保存
		session.save(employee);
		// 提交
		transaction.commit();
		session.close();
	}

}
