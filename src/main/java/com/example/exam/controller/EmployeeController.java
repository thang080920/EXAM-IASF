package com.example.exam.controller;

import com.example.exam.entity.Employee;
import com.example.exam.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Employee> employees = findAllEmployees();
        model.addAttribute("employees", employees);

        return "index";
    }

    public List<Employee> findAllEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @GetMapping("/employee-add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());

        return "employee-add";
    }

    @PostMapping("/employee-save")
    public String saveEmployee(Employee employee) {
        createEmployee(employee);

        return "redirect:/";
    }

    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
