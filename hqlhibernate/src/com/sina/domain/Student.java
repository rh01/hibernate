package com.sina.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields

	private BigDecimal sid;
	private String sname;
	private String ssex;
	private String sdept;
	private Short sage;
	private String saddress;
	private Set studcourses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(BigDecimal sid, String sname, String ssex, String sdept) {
		this.sid = sid;
		this.sname = sname;
		this.ssex = ssex;
		this.sdept = sdept;
	}

	/** full constructor */
	public Student(BigDecimal sid, String sname, String ssex, String sdept, Short sage, String saddress,
			Set studcourses) {
		this.sid = sid;
		this.sname = sname;
		this.ssex = ssex;
		this.sdept = sdept;
		this.sage = sage;
		this.saddress = saddress;
		this.studcourses = studcourses;
	}

	// Property accessors

	public BigDecimal getSid() {
		return this.sid;
	}

	public void setSid(BigDecimal sid) {
		this.sid = sid;
	}

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSsex() {
		return this.ssex;
	}

	public void setSsex(String ssex) {
		this.ssex = ssex;
	}

	public String getSdept() {
		return this.sdept;
	}

	public void setSdept(String sdept) {
		this.sdept = sdept;
	}

	public Short getSage() {
		return this.sage;
	}

	public void setSage(Short sage) {
		this.sage = sage;
	}

	public String getSaddress() {
		return this.saddress;
	}

	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}

	public Set getStudcourses() {
		return this.studcourses;
	}

	public void setStudcourses(Set studcourses) {
		this.studcourses = studcourses;
	}

}