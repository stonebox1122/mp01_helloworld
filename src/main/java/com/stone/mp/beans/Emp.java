package com.stone.mp.beans;

import com.baomidou.mybatisplus.annotations.TableField;

/**
 * 定义Javabean中成员变量时所使用的类型：包装类
 * @author lei.shi445
 *
 */

/**
 * MP默认使用实体类的类名到数据库中找对应的表。
 * @author lei.shi445
 *
 */
//@TableName(value="tbl_emp")
public class Emp {
	/**
	 * value：指定表中的主键列的列名，如果实体属性名与列名一致，可以省略不指定。
	 * type：指定主键策略。
	 */
	//@TableId(value="id",type=IdType.AUTO)
	private Integer id;
	//@TableField(value="last_name")，如果MP是3.x版本，则需要添加此注解
	private String lastName;
	private String email;
	private Integer gender;
	private Integer age;
	@TableField(exist=false)//此属性在数据库中没有对应的字段
	private Double salary;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email=" + email + ", gender=" + gender + ", age="
				+ age + "]";
	}
}
