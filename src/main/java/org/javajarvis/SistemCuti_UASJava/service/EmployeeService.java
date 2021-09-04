package org.javajarvis.SistemCuti_UASJava.service;

import org.javajarvis.SistemCuti_UASJava.model.Employee;
import org.javajarvis.SistemCuti_UASJava.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee findOne(Integer id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if (!employee.isPresent()){
            return null;
        }
        return employee.get();
    }



    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> findByUsername(String name){
        return employeeRepository.findByUsername(name);
    }

    public Iterable<Employee> saveBatch(Iterable<Employee> employees){
        return employeeRepository.saveAll(employees);
    }

    public  void  removeOne (Integer id){
        employeeRepository.deleteById(id);
    }

    public List<Employee> findEmployeeByNameLike(String name){
        return employeeRepository.findEmployeeByNameLike("%"+ name +"%");
    }


}
