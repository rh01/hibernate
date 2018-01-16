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
			//1.检索的学生的名字和所在系
			//在hibernate其实不可以醉醺这个规则，hibernate建议把所有属性查询出来
			
			List<Student> list = session.createQuery("from Student").list();
			for(Student s:list){
				//System.out.println(s.getSname() + " 选了 " + s.getStudcourses().size() + " 门课！");
				if(s.getStudcourses().size() == 0){
					System.out.println(s.getSname() + "没有选课！");
				}else{
					Set<Studcourse> set = s.getStudcourses();
					for(Studcourse sc:set){
						System.out.println(s.getSname() + " 选了 " + sc.getCourse().getCname());
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
			//1.检索的学生的名字和所在系
			//在hibernate其实不可以醉醺这个规则，hibernate建议把所有属性查询出来
			
			List<Student> list = session.createQuery("from Student").list();
			for(Student s:list){
				//System.out.println(s.getSname() + " 选了 " + s.getStudcourses().size() + " 门课！");
				if(s.getStudcourses().size() == 0){
					System.out.println(s.getSname() + "没有选课！");
				}else{
					Set<Studcourse> set = s.getStudcourses();
					for(Studcourse sc:set){
						System.out.println(s.getSname() + " 选了 " + sc.getCourse().getCname());
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
		// 这里距离说明hql的使用\
		Session session = null;
		Transaction ts = null;
		try {
			session = HibernateUtil.getCurrentSession();
			ts = session.beginTransaction();
			//hql
			//1.检索所有的信息学生
			List<Student> list =  session.createQuery("from Student").list();
			//2.取出 循环，迭代器
			for(Student s:list){
				System.out.println(s.getSname() + " " + s.getSaddress());
			}
			System.out.println("*********************************");
			//2.使用迭代器去数据
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
