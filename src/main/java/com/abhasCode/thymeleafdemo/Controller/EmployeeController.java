package com.abhasCode.thymeleafdemo.Controller;

import com.abhasCode.thymeleafdemo.entity.Employee;
import com.abhasCode.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {


    private EmployeeService employeeService;


    @Autowired
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    //mapping for list employees

    @GetMapping("/list")
    public String listEmployees(Model theModel){

        List<Employee> employees = employeeService.findAll();

        theModel.addAttribute("employees", employees);



        return "employees/list-employees";

    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";

    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        //save the Employee

        employeeService.save(theEmployee);


        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")

    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel){

        Employee emp = employeeService.findById(theId);

        theModel.addAttribute("employee",emp);

        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId){

        employeeService.deleteById(theId);

        return "redirect:/employees/list";
    }



}
