package com.shh.view;
import java.math.BigDecimal;
import java.util.List;

import com.shh.util.*;

import com.shh.util.HibernateUtil;
import com.sina.domain.Course;
import com.sina.domain.Student;

public class Test2Main {

	public static void main(String[] args) {
		/*String hql = "select sname,saddress from Student";
		List<Object []> list = HibernateUtil.executeQuery(hql, null);
		for(Object [] s:list){
			System.out.println(s[0].toString()+" "+s[1].toString());
		}*/
		
		//使用工具分页
		/*String hql = "select sname,saddress from Student";
		String parameters[] = null;

		List<Object []> list = HibernateUtil.executeQueryByPages(hql, parameters, 2, 3);
		for(Object [] s:list){
			System.out.println(s[0].toString()+" "+s[1].toString());
		}*/
		
		//添加
		Course c = new Course();
		c.setCid(new BigDecimal(4));
		c.setCname("serclet");
		HibernateUtil.save(c);
		
		
	}
}
