package com.shh.view;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.shh.util.HibernateUtil;
import com.sina.domain.Studcourse;
import com.sina.domain.Student;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Session session = null;
		Transaction ts = null;
		try {
			session = HibernateUtil.getCurrentSession();
			ts = session.beginTransaction();
			//1.������ѧ�������ֺ�����ϵ
			//��hibernate��ʵ�����������������hibernate������������Բ�ѯ����
			
			List<Student> list = session.createQuery("from Student").list();
			for(Student s:list){
				//System.out.println(s.getSname() + " ѡ�� " + s.getStudcourses().size() + " �ſΣ�");
				if(s.getStudcourses().size() == 0){
					System.out.println(s.getSname() + "û��ѡ�Σ�");
				}else{
					Set<Studcourse> set = s.getStudcourses();
					for(Studcourse sc:set){
						System.out.println(s.getSname() + " ѡ�� " + sc.getCourse().getCname());
					}
				}
			}
			
			
			
			/*List list = session.createQuery("select sname, sdept from Student").list();
			for(int i=0;i<list.size();i++){
				Object[] obj = (Object[] ) list.get(i);
				System.out.println(obj[0].toString() + " " + obj[1].toString());
			}*/
			ts.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if(ts!=null){
				ts.rollback();
			}
			throw new RuntimeException(e.getMessage());
		}finally{
			if(session!=null && session.isOpen()){
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
			//1.������ѧ�������ֺ�����ϵ
			//��hibernate��ʵ�����������������hibernate������������Բ�ѯ����
			
			List<Student> list = session.createQuery("from Student").list();
			for(Student s:list){
				//System.out.println(s.getSname() + " ѡ�� " + s.getStudcourses().size() + " �ſΣ�");
				if(s.getStudcourses().size() == 0){
					System.out.println(s.getSname() + "û��ѡ�Σ�");
				}else{
					Set<Studcourse> set = s.getStudcourses();
					for(Studcourse sc:set){
						System.out.println(s.getSname() + " ѡ�� " + sc.getCourse().getCname());
					}
				}
			}
			
			
			
			/*List list = session.createQuery("select sname, sdept from Student").list();
			for(int i=0;i<list.size();i++){
				Object[] obj = (Object[] ) list.get(i);
				System.out.println(obj[0].toString() + " " + obj[1].toString());
			}*/
			ts.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if(ts!=null){
				ts.rollback();
			}
			throw new RuntimeException(e.getMessage());
		}finally{
			if(session!=null && session.isOpen()){
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
			//hql
			//1.�������е���Ϣѧ��
			List<Student> list =  session.createQuery("from Student").list();
			//2.ȡ�� ѭ����������
			for(Student s:list){
				System.out.println(s.getSname() + " " + s.getSaddress());
			}
			System.out.println("*********************************");
			//2.ʹ�õ�����ȥ����
			Iterator iter = list.iterator();
			while(iter.hasNext()){
				Student s = (Student)iter.next();
				System.out.println(s.getSname() + " " + s.getSaddress());
			}
			
			
			ts.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if(ts!=null){
				ts.rollback();
			}
			throw new RuntimeException(e.getMessage());
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
	}

}
