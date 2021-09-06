package org.javajarvis.SistemCuti_UASJava.controller;

import org.javajarvis.SistemCuti_UASJava.model.HakCuti;
import org.javajarvis.SistemCuti_UASJava.model.JenisCuti;
import org.javajarvis.SistemCuti_UASJava.repository.HakCutiRepository;
import org.javajarvis.SistemCuti_UASJava.service.HakCutiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hakcuti")
public class HakCutiController {

    @Autowired
    private HakCutiRepository repo;

    @Autowired
    private HakCutiService service;

    @PostMapping
    public HakCuti save(@RequestBody HakCuti hakCuti){
        return service.save(hakCuti);
    }

    @PutMapping
    public HakCuti update(@RequestBody HakCuti hakCuti){
        return  service.save(hakCuti);
    }

    @DeleteMapping
    public void delete(@RequestBody Integer id){
        repo.deleteById(id);
    }

    @GetMapping
    public Iterable<HakCuti> findAll() {
        return repo.findAll();
    }
}
