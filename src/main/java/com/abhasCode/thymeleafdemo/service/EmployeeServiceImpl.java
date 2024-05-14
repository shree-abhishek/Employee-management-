package com.abhasCode.thymeleafdemo.service;

import com.abhasCode.thymeleafdemo.DAO.EmployeeRepository;
import com.abhasCode.thymeleafdemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theemployeeRepository) {
        employeeRepository = theemployeeRepository;
    }

    @Override
    public List<Employee> findAll() {

        return employeeRepository.findAllByOrderByFirstNameAsc();
    }

    @Override
    public Employee findById(int theId) {
        Employee emp = null;
        Optional<Employee> opt = employeeRepository.findById(theId);

        if(opt.isPresent()){
            emp = opt.get();
        }

        return emp;
    }
    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }


    @Override
    public void deleteById(int theId) {

        employeeRepository.deleteById(theId);

    }
}
