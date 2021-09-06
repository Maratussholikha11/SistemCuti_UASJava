package org.javajarvis.SistemCuti_UASJava.controller;

import org.javajarvis.SistemCuti_UASJava.dto.ResponseData;
import org.javajarvis.SistemCuti_UASJava.model.Employee;
import org.javajarvis.SistemCuti_UASJava.model.Role;
import org.javajarvis.SistemCuti_UASJava.repository.RoleRepository;
import org.javajarvis.SistemCuti_UASJava.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleService rs;

//    @PostMapping
//    public ResponseEntity<ResponseData<Role>> create(@RequestBody Role role, Errors errors){
//        ResponseData<Role> responseData = new ResponseData<>();
//
//        if (errors.hasErrors()){
//            for (ObjectError error : errors.getAllErrors()){
//                responseData.getMessages().add(error.getDefaultMessage());
//            }
//            responseData.setStatus(false);
//            responseData.setPayload(null);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
//        }
//        responseData.setStatus(true);
//        responseData.setPayload(rs.save(role));
//        return ResponseEntity.ok(responseData);
//    }

    @PostMapping
    public Role save(@RequestBody Role role){
        return rs.save(role);
    }

    @PutMapping
    public Role update(@RequestBody Role role){
        return rs.save(role);
    }

    @GetMapping
    Iterable<Role> findAll(){
        return roleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Role findOne (@PathVariable ("id") Integer id){
        Optional<Role> p = roleRepository.findById(id);
        if (!p.isPresent()){
            return  null;
        }
        return rs.findOne(id);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") @RequestBody Integer id){
        rs.removeOne(id);
    }


}
