package org.javajarvis.SistemCuti_UASJava.controller;

import org.javajarvis.SistemCuti_UASJava.model.JenisCuti;
import org.javajarvis.SistemCuti_UASJava.repository.JenisCutiRepository;
import org.javajarvis.SistemCuti_UASJava.service.JenisCutiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jeniscuti")
public class JenisCutiController {

    @Autowired
    private JenisCutiRepository repo;

    @Autowired
    private JenisCutiService service;

    @PostMapping
    public JenisCuti save(@RequestBody JenisCuti jenisCuti){
        return service.save(jenisCuti);
    }

    @PutMapping
    public JenisCuti update(@RequestBody JenisCuti jenisCuti){
        return  service.save(jenisCuti);
    }

    @DeleteMapping
    public void delete(@RequestBody Integer id){
        repo.deleteById(id);
    }

    @GetMapping
    public Iterable<JenisCuti> findAll(){
        return repo.findAll();
    }
}
