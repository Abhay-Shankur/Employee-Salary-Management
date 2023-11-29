package com.employee.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.employee.app.model.Employee;
import com.employee.app.model.Login;

public class DatabaseHandle {

	private static String url = "jdbc:mysql://localhost:3307/employedb";
	private static String username = "root";
	private static String password = "";
	
	private Connection conn;
	private PreparedStatement stm;
	private ResultSet rs;
	
	public DatabaseHandle() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, username, password);
		
	}
	
	public boolean login(Login emp) throws SQLException {
//		Query String
		String query = "SELECT * FROM user_credentials WHERE username = ? and password = ?";

//		Staement 
		PreparedStatement stm = conn.prepareStatement(query);
		stm.setString(1, emp.getUsername());
		stm.setString(2, emp.getPassword());
		
		return stm.execute();
		
	}
	
	public Employee getSlip(Login user) throws SQLException {

		Employee emp = new Employee();
		
//		Query String
		String query = "SELECT id FROM user_credentials WHERE username = ? and password = ?";

//		Staement 
		stm = conn.prepareStatement(query);
		stm.setString(1, user.getUsername());
		stm.setString(2, user.getPassword());
		
		rs = stm.executeQuery();
		if(rs.next()) {
			String id = rs.getString("id");
//			Query String
			query = "SELECT * FROM employee_salary WHERE id = ?";

//			Staement 
			stm = conn.prepareStatement(query);
			stm.setString(1, id);
		
			rs = stm.executeQuery();
			
			while (rs.next()) {
				String empid = rs.getString("id");
				String name = rs.getString("name");
				String designation = rs.getString("designation");
				String department = rs.getString("department");
				double basicSalary = rs.getDouble("basic_salary");
				double hra = rs.getDouble("hra");
				double da = rs.getDouble("da");
				double ta = rs.getDouble("ta");
				emp = new Employee(name, designation, department, basicSalary, hra, da, ta);
				emp.setId(empid);
				emp.setTotal_earnings(rs.getDouble("total_earnings"));
				emp.setDeductions(rs.getDouble("deductions"));
				emp.setNet_remuneration(rs.getDouble("net_remuneration"));
			}
		}
		return emp;
		
	}
	
	public boolean update(String id, String action) throws SQLException {
		Employee emp = new Employee();
		
		String query = "UPDATE employee_salary SET action = ? WHERE id = ?";
		stm = conn.prepareStatement(query);
		stm.setString(1, action);
		stm.setString(2, id);
		if(stm.executeUpdate()>0) {
			return true;
		} else {
			return false;
		}
	}
	public Employee get(String id) throws SQLException {
		Employee emp = new Employee();
		String sql = "SELECT * FROM employee_salary WHERE id=?";
		stm=conn.prepareStatement(sql);
		stm.setString(1, id);
		
		rs = stm.executeQuery();
		
		while (rs.next()) {
			String empid = rs.getString("id");
			String name = rs.getString("name");
			String designation = rs.getString("designation");
			String department = rs.getString("department");
			double basicSalary = rs.getDouble("basic_salary");
			double hra = rs.getDouble("hra");
			double da = rs.getDouble("da");
			double ta = rs.getDouble("ta");
			emp = new Employee(name, designation, department, basicSalary, hra, da, ta);
			emp.setId(empid);
			emp.setTotal_earnings(rs.getDouble("total_earnings"));
			emp.setDeductions(rs.getDouble("deductions"));
			emp.setNet_remuneration(rs.getDouble("net_remuneration"));
		}
		
		return emp;
	}
	protected void finalize() throws SQLException {
		rs.close();
		stm.close();
		conn.close();
	}
}
