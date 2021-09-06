package org.javajarvis.SistemCuti_UASJava.controller;

import org.javajarvis.SistemCuti_UASJava.model.JenisCuti;
import org.javajarvis.SistemCuti_UASJava.model.StatusCuti;
import org.javajarvis.SistemCuti_UASJava.repository.StatusCutiRepository;
import org.javajarvis.SistemCuti_UASJava.service.StatusCutiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statuscuti")
public class StatusCutiController {

    @Autowired
    StatusCutiRepository repo;

    @Autowired
    StatusCutiService service;

    @PostMapping
    public StatusCuti save(@RequestBody StatusCuti statusCuti){
        return service.save(statusCuti);
    }

    @PutMapping
    public StatusCuti update(@RequestBody StatusCuti statusCuti){
        return  service.save(statusCuti);
    }

    @DeleteMapping
    public void delete(@RequestBody Integer id){
        repo.deleteById(id);
    }

    @GetMapping
    public Iterable<StatusCuti> findAll(){
        return repo.findAll();
    }
}
