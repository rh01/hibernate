package com.shh.view;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shh.util.HibernateUtil;
import com.sina.domain.Studcourse;
import com.sina.domain.Student;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// showResultByPage(3);

		Session session = null;
		Transaction ts = null;
		try {
			session = HibernateUtil.getCurrentSession();
			ts = session.beginTransaction();
			
			//1
			Query query = session.createQuery("from Student where sdept=:a1 and sage>:sage");
			query.setString("a1", "计算机系");
			query.setString("sage", "2");
			List<Student> list = query.list();
			
			//2
			/*List<Student> list = session.createQuery("from Student where sdept=:a1 and sage>:sage")
					.setString("a1", "计算机系").setString("sage", "2").list();*/
			//3
			/*List<Student> list = session.createQuery("from Student where sdept=? and sage>?")
					.setString(0, "计算机系").setString(1, "2").list();*/

			for (Student s:list) {
				System.out.println(s.getSname() + " " + s.getSage());
			}
			ts.commit();
		} catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		/*
		 * Session session = null; Transaction ts = null; try { session =
		 * HibernateUtil.getCurrentSession(); ts = session.beginTransaction();
		 * // 1.检索的学生的名字和所在系 // 在hibernate其实不可以醉醺这个规则，hibernate建议把所有属性查询出来
		 * 
		 * 
		 * List<Student> list = session.createQuery("from Student").list();
		 * for(Student s:list){ //System.out.println(s.getSname() + " 选了 " +
		 * s.getStudcourses().size() + " 门课！"); if(s.getStudcourses().size() ==
		 * 0){ System.out.println(s.getSname() + "没有选课！"); }else{
		 * Set<Studcourse> set = s.getStudcourses(); for(Studcourse sc:set){
		 * System.out.println(s.getSname() + " 选了 " +
		 * sc.getCourse().getCname()); } } }
		 * 
		 * 
		 * Student student = (Student) session.createQuery(
		 * "from Student where sid=20050003").uniqueResult();
		 * System.out.println(student.getSname()); // 比如显示所有学生的性别和年龄
		 * 
		 * List list = session.createQuery(
		 * "select distinct sage, ssex from Student").list();
		 * 
		 * for(int i=0;i<list.size();i++){ Object[] obj = (Object[] )
		 * list.get(i); System.out.println(obj[0].toString() + " " +
		 * obj[1].toString()); }
		 * 
		 * 
		 * // 年龄在20～22之间的学生
		 * 
		 * List list = session.createQuery(
		 * "select distinct sage, ssex from Student where sage between 20 and 22"
		 * ).list();
		 * 
		 * for(int i=0;i<list.size();i++){ Object[] obj = (Object[] )
		 * list.get(i); System.out.println(obj[0].toString() + " " +
		 * obj[1].toString()); }
		 * 
		 * 
		 * // 查询计算机系和外语系学生
		 * 
		 * List list = session.createQuery(
		 * "select distinct sage, ssex, sdept, sname from Student where sdept not in ('计算机系', '外语系')"
		 * ).list();
		 * 
		 * for(int i=0;i<list.size();i++){ Object[] obj = (Object[] )
		 * list.get(i); System.out.println(obj[0].toString() + " " +
		 * obj[1].toString()+ " " + obj[2].toString() + " " +
		 * obj[3].toString()); }
		 * 
		 * 
		 * // 计算每个系的学生平均年龄
		 * 
		 * List list = session.createQuery(
		 * "select avg(sage), sdept from Student group by sdept ").list();
		 * 
		 * for(int i=0;i<list.size();i++){ Object[] obj = (Object[] )
		 * list.get(i); System.out.println(obj[0].toString() + " " +
		 * obj[1].toString()); }
		 * 
		 * 
		 * // having的用法，对分组查询的结果进行筛选，比如显示人数大于3的系名称 // 计算每个系各有多少学生，然后根据学生人数进行筛选
		 * 
		 * List list = session.createQuery(
		 * "select count(*) as cl,sdept from Student group by sdept having count(*) > 3"
		 * ).list();
		 * 
		 * for(int i=0;i<list.size();i++){ Object[] obj = (Object[] )
		 * list.get(i); System.out.println(obj[0].toString() + " " +
		 * obj[1].toString()); }
		 * 
		 * 
		 * // 查询女生少于200人的系
		 * 
		 * List list = session. createQuery(
		 * "select count(*) as cl,sdept from Student where ssex='F' group by sdept"
		 * ).list();
		 * 
		 * for(int i=0;i<list.size();i++){ Object[] obj = (Object[] )
		 * list.get(i); System.out.println(obj[0].toString() + " " +
		 * obj[1].toString()); }
		 * 
		 * // 查询计算机系共多少人，如果我们返回的是一列数据 // 我们就不能使用object[]，而是应该是Object
		 * 
		 * List<Object[]> list=session.createQuery(
		 * "select sage from  Student where sdept='计算机系'").list(); //取出1. for 增强
		 * for(Object obj:list){ System.out.println(obj.toString()); }
		 * 
		 * 
		 * // 3.查询选修11号课程的最高分和最低分. List<Object[]> list = session.createQuery(
		 * "select 11,max(grade),min(grade) from Studcourse where course.cid=11"
		 * ).list(); // 取出1. for 增强 for (Object[] obj : list) {
		 * System.out.println(obj[0].toString() + " max=" + obj[1].toString() +
		 * " min=" + obj[2].toString()); }
		 * 
		 * //计算出各个科目不合格的学生的名字，科目，，和分数 List<Object[]> list=session.createQuery(
		 * "select student.sname, course.cname, grade from Studcourse where grade>=60 "
		 * ).list(); // 取出1. for 增强 for (Object[] obj : list) {
		 * System.out.println(obj[0].toString() + " " + obj[1].toString()+ " " +
		 * obj[2].toString()); }
		 * 
		 * //计算出各个科目不合格学生的人数 List<Object[]> list=session.createQuery(
		 * "select count(*),student.sdept from Studcourse where grade<60 group by student.sdept"
		 * ).list(); // 取出1. for 增强 for (Object[] obj : list) {
		 * System.out.println(obj[0].toString() + " " + obj[1].toString()); }
		 * 
		 * //取出第三个和第五个学生 List<Student> list = session.createQuery(
		 * "from Student order by sage"
		 * ).setFirstResult(3).setMaxResults(3).list(); for (Student s : list) {
		 * System.out.println(s.getSname()+" " + s.getSage()); }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * List list = session.createQuery( "select sname, sdept from Student"
		 * ).list(); for(int i=0;i<list.size();i++){ Object[] obj = (Object[] )
		 * list.get(i); System.out.println(obj[0].toString() + " " +
		 * obj[1].toString()); }
		 * 
		 * ts.commit(); } catch (Exception e) { // TODO: handle exception if (ts
		 * != null) { ts.rollback(); } throw new
		 * RuntimeException(e.getMessage()); } finally { if (session != null &&
		 * session.isOpen()) { session.close(); } }
		 */

	}

	// 分页函数

	private static void showResultByPage(int pageSize) {
		// 设置分页的变量
		int pageNow = 1;
		int pageCount = 1; // 计算
		int rowCount = 1;// 这个需要查询

		Session session = null;
		Transaction ts = null;
		try {
			session = HibernateUtil.getCurrentSession();
			ts = session.beginTransaction();
			// 查询出rowcount
			rowCount = Integer.parseInt(session.createQuery("select count(*) from Student").uniqueResult().toString());
			pageCount = (rowCount - 1) / pageSize + 1;

			// 现在我们可以循环的显示每页的信息
			for (int i = 1; i <= pageCount; i++) {
				System.out.println("**********第" + i + "页************");
				List<Student> list = session.createQuery("from Student").setFirstResult((i - 1) * pageSize)
						.setMaxResults(pageSize).list();
				for (Student s : list) {
					System.out.println(s.getSname() + " " + s.getSdept());
				}

			}
			// System.out.println(rowCount);
			ts.commit();
		} catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	public static void query2() {
		Session session = null;
		Transaction ts = null;
		try {
			session = HibernateUtil.getCurrentSession();
			ts = session.beginTransaction();
			// 1.检索的学生的名字和所在系
			// 在hibernate其实不可以醉醺这个规则，hibernate建议把所有属性查询出来

			List<Student> list = session.createQuery("from Student").list();
			for (Student s : list) {
				// System.out.println(s.getSname() + " 选了 " +
				// s.getStudcourses().size() + " 门课！");
				if (s.getStudcourses().size() == 0) {
					System.out.println(s.getSname() + "没有选课！");
				} else {
					Set<Studcourse> set = s.getStudcourses();
					for (Studcourse sc : set) {
						System.out.println(s.getSname() + " 选了 " + sc.getCourse().getCname());
					}
				}
			}

			/*
			 * List list = session.createQuery(
			 * "select sname, sdept from Student").list(); for(int
			 * i=0;i<list.size();i++){ Object[] obj = (Object[] ) list.get(i);
			 * System.out.println(obj[0].toString() + " " + obj[1].toString());
			 * }
			 */
			ts.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (ts != null) {
				ts.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public static void query1() {
		// 这里距离说明hql的使用\
		Session session = null;
		Transaction ts = null;
		try {
			session = HibernateUtil.getCurrentSession();
			ts = session.beginTransaction();
			// hql
			// 1.检索所有的信息学生
			List<Student> list = session.createQuery("from Student").list();
			// 2.取出 循环，迭代器
			for (Student s : list) {
				System.out.println(s.getSname() + " " + s.getSaddress());
			}
			System.out.println("*********************************");
			// 2.使用迭代器去数据
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				Student s = (Student) iter.next();
				System.out.println(s.getSname() + " " + s.getSaddress());
			}

			ts.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (ts != null) {
				ts.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

}
