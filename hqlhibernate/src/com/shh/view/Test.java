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
			query.setString("a1", "�����ϵ");
			query.setString("sage", "2");
			List<Student> list = query.list();
			
			//2
			/*List<Student> list = session.createQuery("from Student where sdept=:a1 and sage>:sage")
					.setString("a1", "�����ϵ").setString("sage", "2").list();*/
			//3
			/*List<Student> list = session.createQuery("from Student where sdept=? and sage>?")
					.setString(0, "�����ϵ").setString(1, "2").list();*/

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
		 * // 1.������ѧ�������ֺ�����ϵ // ��hibernate��ʵ�����������������hibernate������������Բ�ѯ����
		 * 
		 * 
		 * List<Student> list = session.createQuery("from Student").list();
		 * for(Student s:list){ //System.out.println(s.getSname() + " ѡ�� " +
		 * s.getStudcourses().size() + " �ſΣ�"); if(s.getStudcourses().size() ==
		 * 0){ System.out.println(s.getSname() + "û��ѡ�Σ�"); }else{
		 * Set<Studcourse> set = s.getStudcourses(); for(Studcourse sc:set){
		 * System.out.println(s.getSname() + " ѡ�� " +
		 * sc.getCourse().getCname()); } } }
		 * 
		 * 
		 * Student student = (Student) session.createQuery(
		 * "from Student where sid=20050003").uniqueResult();
		 * System.out.println(student.getSname()); // ������ʾ����ѧ�����Ա������
		 * 
		 * List list = session.createQuery(
		 * "select distinct sage, ssex from Student").list();
		 * 
		 * for(int i=0;i<list.size();i++){ Object[] obj = (Object[] )
		 * list.get(i); System.out.println(obj[0].toString() + " " +
		 * obj[1].toString()); }
		 * 
		 * 
		 * // ������20��22֮���ѧ��
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
		 * // ��ѯ�����ϵ������ϵѧ��
		 * 
		 * List list = session.createQuery(
		 * "select distinct sage, ssex, sdept, sname from Student where sdept not in ('�����ϵ', '����ϵ')"
		 * ).list();
		 * 
		 * for(int i=0;i<list.size();i++){ Object[] obj = (Object[] )
		 * list.get(i); System.out.println(obj[0].toString() + " " +
		 * obj[1].toString()+ " " + obj[2].toString() + " " +
		 * obj[3].toString()); }
		 * 
		 * 
		 * // ����ÿ��ϵ��ѧ��ƽ������
		 * 
		 * List list = session.createQuery(
		 * "select avg(sage), sdept from Student group by sdept ").list();
		 * 
		 * for(int i=0;i<list.size();i++){ Object[] obj = (Object[] )
		 * list.get(i); System.out.println(obj[0].toString() + " " +
		 * obj[1].toString()); }
		 * 
		 * 
		 * // having���÷����Է����ѯ�Ľ������ɸѡ��������ʾ��������3��ϵ���� // ����ÿ��ϵ���ж���ѧ����Ȼ�����ѧ����������ɸѡ
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
		 * // ��ѯŮ������200�˵�ϵ
		 * 
		 * List list = session. createQuery(
		 * "select count(*) as cl,sdept from Student where ssex='F' group by sdept"
		 * ).list();
		 * 
		 * for(int i=0;i<list.size();i++){ Object[] obj = (Object[] )
		 * list.get(i); System.out.println(obj[0].toString() + " " +
		 * obj[1].toString()); }
		 * 
		 * // ��ѯ�����ϵ�������ˣ�������Ƿ��ص���һ������ // ���ǾͲ���ʹ��object[]������Ӧ����Object
		 * 
		 * List<Object[]> list=session.createQuery(
		 * "select sage from  Student where sdept='�����ϵ'").list(); //ȡ��1. for ��ǿ
		 * for(Object obj:list){ System.out.println(obj.toString()); }
		 * 
		 * 
		 * // 3.��ѯѡ��11�ſγ̵���߷ֺ���ͷ�. List<Object[]> list = session.createQuery(
		 * "select 11,max(grade),min(grade) from Studcourse where course.cid=11"
		 * ).list(); // ȡ��1. for ��ǿ for (Object[] obj : list) {
		 * System.out.println(obj[0].toString() + " max=" + obj[1].toString() +
		 * " min=" + obj[2].toString()); }
		 * 
		 * //�����������Ŀ���ϸ��ѧ�������֣���Ŀ�����ͷ��� List<Object[]> list=session.createQuery(
		 * "select student.sname, course.cname, grade from Studcourse where grade>=60 "
		 * ).list(); // ȡ��1. for ��ǿ for (Object[] obj : list) {
		 * System.out.println(obj[0].toString() + " " + obj[1].toString()+ " " +
		 * obj[2].toString()); }
		 * 
		 * //�����������Ŀ���ϸ�ѧ�������� List<Object[]> list=session.createQuery(
		 * "select count(*),student.sdept from Studcourse where grade<60 group by student.sdept"
		 * ).list(); // ȡ��1. for ��ǿ for (Object[] obj : list) {
		 * System.out.println(obj[0].toString() + " " + obj[1].toString()); }
		 * 
		 * //ȡ���������͵����ѧ�� List<Student> list = session.createQuery(
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

	// ��ҳ����

	private static void showResultByPage(int pageSize) {
		// ���÷�ҳ�ı���
		int pageNow = 1;
		int pageCount = 1; // ����
		int rowCount = 1;// �����Ҫ��ѯ

		Session session = null;
		Transaction ts = null;
		try {
			session = HibernateUtil.getCurrentSession();
			ts = session.beginTransaction();
			// ��ѯ��rowcount
			rowCount = Integer.parseInt(session.createQuery("select count(*) from Student").uniqueResult().toString());
			pageCount = (rowCount - 1) / pageSize + 1;

			// �������ǿ���ѭ������ʾÿҳ����Ϣ
			for (int i = 1; i <= pageCount; i++) {
				System.out.println("**********��" + i + "ҳ************");
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
			// 1.������ѧ�������ֺ�����ϵ
			// ��hibernate��ʵ�����������������hibernate������������Բ�ѯ����

			List<Student> list = session.createQuery("from Student").list();
			for (Student s : list) {
				// System.out.println(s.getSname() + " ѡ�� " +
				// s.getStudcourses().size() + " �ſΣ�");
				if (s.getStudcourses().size() == 0) {
					System.out.println(s.getSname() + "û��ѡ�Σ�");
				} else {
					Set<Studcourse> set = s.getStudcourses();
					for (Studcourse sc : set) {
						System.out.println(s.getSname() + " ѡ�� " + sc.getCourse().getCname());
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
		// �������˵��hql��ʹ��\
		Session session = null;
		Transaction ts = null;
		try {
			session = HibernateUtil.getCurrentSession();
			ts = session.beginTransaction();
			// hql
			// 1.�������е���Ϣѧ��
			List<Student> list = session.createQuery("from Student").list();
			// 2.ȡ�� ѭ����������
			for (Student s : list) {
				System.out.println(s.getSname() + " " + s.getSaddress());
			}
			System.out.println("*********************************");
			// 2.ʹ�õ�����ȥ����
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
