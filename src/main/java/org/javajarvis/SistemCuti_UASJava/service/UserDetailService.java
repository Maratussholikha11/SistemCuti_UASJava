package org.javajarvis.SistemCuti_UASJava.service;

import org.javajarvis.SistemCuti_UASJava.model.Employee;
import org.javajarvis.SistemCuti_UASJava.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserDetailService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return employeeRepository.findByUsername(s)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("User with email '%s' not found", s )));

    }

    public List<Employee>getAll(){
        return employeeRepository.findAll();
    }

    public Employee save (Employee employee){
        boolean userExist = employeeRepository.findByUsername(employee.getUsername()).isPresent();
        if (userExist){
            throw new RuntimeException(String.format("user with username '%s' is present", employee.getUsername()));
        }

        String encodePassword = bCryptPasswordEncoder.encode(employee.getPassword());
        employee.setPassword(encodePassword);
        return employeeRepository.save(employee);

    }

}
