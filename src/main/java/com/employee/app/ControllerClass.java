package com.employee.app;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.app.database.DatabaseHandle;
import com.employee.app.model.Employee;
import com.employee.app.model.Login;

@Controller
public class ControllerClass {

	@GetMapping("/")
	public String showIndex(Model model) {
		model.addAttribute("user", new Login());
		return "index";
	}
	
	@PostMapping("/login")
	public String login(Model model, Login user) throws SQLException, ClassNotFoundException {
		DatabaseHandle db =new DatabaseHandle();
		
		if(db.login(user)) {
			Employee emp = db.getSlip(user);
			model.addAttribute("emp", emp);
			return "salary-slip";
		} else {
			model.addAttribute("error", "Invalid credentials. Please try again.");
			return "index";
		}
		
	}
	
	@PostMapping("/update")
	public String showSalarySlip(Model model, @RequestParam String empId, @RequestParam String action) throws ClassNotFoundException, SQLException {
		DatabaseHandle db = new DatabaseHandle();
		if(db.update(empId, action)) {
			if(action.equals("Confirm")){
				model.addAttribute("succes", "You have Confirmed your Salary Slip.");
			} else {
				model.addAttribute("deny", "You have Rejected your Salary Slip.");
			}
		}
		Employee emp = db.get(empId);
		model.addAttribute("emp", emp);
		return "salary-slip";
	}
	
}
