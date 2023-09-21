 package com.employee.employeemanagement.model;

 import java.io.Serializable;


 import jakarta.persistence.*;
 import lombok.Getter;
 import lombok.NoArgsConstructor;
 import lombok.Setter;
 import lombok.ToString;

 @Entity
 @Table(name="employees")
 @Getter
 @Setter
 @NoArgsConstructor
 @ToString
 public class Employee implements Serializable{

 	/**
 	 *
 	 */
 	private static final long serialVersionUID = 1L;
 	@Id
 	@GeneratedValue(strategy = GenerationType.AUTO)
 	private int employeeId;

 	@Column(name="employeename")
 	private String employeeName;

 	@Column(name="title")
 	private String title;

 	@Column(name="businessunit")
 	private String businessUnit;

 	@Column(name="place")
 	private String place;

 	@Column(name="supervisorid")
 	private String supervisorId;

 	@Column(name="competencies")
 	private String competencies;

 	@Column(name="salary")
 	private String salary;

 }