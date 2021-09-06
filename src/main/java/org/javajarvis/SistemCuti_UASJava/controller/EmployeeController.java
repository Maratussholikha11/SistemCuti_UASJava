package org.javajarvis.SistemCuti_UASJava.controller;


import org.javajarvis.SistemCuti_UASJava.dto.EmployeeDto;
import org.javajarvis.SistemCuti_UASJava.dto.ResponseData;
import org.javajarvis.SistemCuti_UASJava.model.Employee;
import org.javajarvis.SistemCuti_UASJava.repository.EmployeeRepository;
import org.javajarvis.SistemCuti_UASJava.repository.RoleRepository;
import org.javajarvis.SistemCuti_UASJava.service.EmailService;
import org.javajarvis.SistemCuti_UASJava.service.EmployeeService;
import org.javajarvis.SistemCuti_UASJava.service.UserDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService services;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private  ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmailService emailService;

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody Employee employee){

        ResponseData<Employee> response = new ResponseData<>();
        Employee user = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee1  = modelMapper.map(employee, Employee.class);
        employee1.setPassword(employee.getPassword());
        String[] email = new String[1];
        email[0] = employee1.getEmail();
        String text = "Welcome to Leave Management System\n" +
                "here is your username and password to login in your account Leave management system\n" +
                "username : " + employee.getUsername() +
                "\npassword : " + employee.getPassword() +
                "\nor you can login by click the link below\n" +
                "ini link\n" +
                "thank you!!!\n";
        emailService.sendEmail(email[0],"Acces Login Employee",text);
        response.setPayload(userDetailService.save(employee1));
        return "Registered";
    }

    @PutMapping("/register")
    @ResponseBody
    public String updateRegister(@RequestBody Employee employee){
        userDetailService.save(employee);
        return "Registered";
    }


    @GetMapping
    public List<Employee> findAll(){
        return services.findAll();
    }

    @GetMapping("/{id}")
    public Employee findOne (@PathVariable ("id") Integer id){
        Optional<Employee> p = employeeRepository.findById(id);
        if (!p.isPresent()){
            return  null;
        }
        return services.findOne(id);
    }

    @PutMapping("/changepassword")
    public String change(@RequestBody String pass){
        Employee user = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setPassword(pass);
        return "pass";
    }

}
