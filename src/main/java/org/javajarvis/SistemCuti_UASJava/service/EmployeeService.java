package org.javajarvis.SistemCuti_UASJava.service;

import org.javajarvis.SistemCuti_UASJava.model.Employee;
import org.javajarvis.SistemCuti_UASJava.model.HakCuti;
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
        if(employee.getId()!=null){ //
            Employee current = employeeRepository.findById(employee.getId()).get();
            current.setNamaLengkap(employee.getNamaLengkap());
            current.setNip(employee.getNip());
            current.setEmail(employee.getEmail());
            current.setDivisi(employee.getDivisi());
            current.setPassword(employee.getPassword());
            current.setUsername(employee.getUsername());
            employee = current;
        }
        return employeeRepository.save(employee);
    }
    public Employee findOne(Integer id){
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElse(null);
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
