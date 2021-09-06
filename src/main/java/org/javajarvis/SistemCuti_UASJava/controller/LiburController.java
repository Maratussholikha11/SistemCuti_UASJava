package org.javajarvis.SistemCuti_UASJava.controller;

import org.javajarvis.SistemCuti_UASJava.model.Libur;
import org.javajarvis.SistemCuti_UASJava.repository.LiburRepository;
import org.javajarvis.SistemCuti_UASJava.service.LiburService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/libur")
public class LiburController {

    @Autowired
    private LiburRepository repo;

    @Autowired
    private LiburService service;

    @PostMapping
    public Libur save(@RequestBody Libur libur){
        return service.save(libur);
    }

    @PutMapping
    public Libur update(@RequestBody Libur libur){
        return  service.save(libur);
    }

    @DeleteMapping
    public void delete(@RequestBody Integer id){
        repo.deleteById(id);
    }

    @GetMapping
    public Iterable<Libur> findAll() {
        return repo.findAll();
    }
}
