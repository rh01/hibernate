package com.shh.domain;

import java.io.Serializable;

// �������ǵ�domain��������ƾ��Ƕ�Ӧ�������ĸ��д
// domain����/javabean/pojo[plain old]
//��pojo���չ淶Ӧ�����л������л���Ŀ���ǿ���Ψһ�ı�ʾ�ö���ͬʱ��������ļ�����

public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer aaaid;
	private String name;
	private String email;
	private java.util.Date hiredate;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public java.util.Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(java.util.Date hiredate) {
		this.hiredate = hiredate;
	}
	public Integer getAaaid() {
		return aaaid;
	}
	public void setAaaid(Integer aaaid) {
		this.aaaid = aaaid;
	}
}
