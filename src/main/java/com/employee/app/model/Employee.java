package com.employee.app.model;

public class Employee {

	private String id;
    private String name;
    private String designation;
    private String department;
    private double basicSalary;
    private double hra;
    private double da;
    private double ta;
    private double total_earnings;
    private double deductions;
    private double net_remuneration;

    
    public Employee() {
		super();
	}

	public Employee(String name, String designation, String department, double basicSalary, double hra, double da, double ta) {
        this.name = name;
        this.designation = designation;
        this.department = department;
        this.basicSalary = basicSalary;
        this.hra = hra;
        this.da = da;
        this.ta = ta;
    }

    // Calculate remuneration based on the provided components
    public double calculateRemuneration() {
        return basicSalary + hra + da + ta;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public double getHra() {
		return hra;
	}

	public void setHra(double hra) {
		this.hra = hra;
	}

	public double getDa() {
		return da;
	}

	public void setDa(double da) {
		this.da = da;
	}

	public double getTa() {
		return ta;
	}

	public void setTa(double ta) {
		this.ta = ta;
	}

	public double getTotal_earnings() {
		return total_earnings;
	}

	public void setTotal_earnings(double total_earnings) {
		this.total_earnings = total_earnings;
	}

	public double getDeductions() {
		return deductions;
	}

	public void setDeductions(double deductions) {
		this.deductions = deductions;
	}

	public double getNet_remuneration() {
		return net_remuneration;
	}

	public void setNet_remuneration(double net_remuneration) {
		this.net_remuneration = net_remuneration;
	}

    // Getters (You can generate them using your IDE)
    
	
}
