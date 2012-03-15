package jp.co.shantery.spring.web.support.csv.bean;

import jp.co.shantery.spring.web.support.annotation.CsvColumn;

public class MyBean {

	@CsvColumn(index = 0)
	private String name;

	@CsvColumn(index = 1)
	private Integer employeeNo;

	@CsvColumn(index = 2)
	private String deptName;

	@CsvColumn(index = 3)
	private Double salary;

	@CsvColumn(index = 4)
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(Integer employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
