package org.javajarvis.SistemCuti_UASJava.service;

import org.javajarvis.SistemCuti_UASJava.model.StatusCuti;
import org.javajarvis.SistemCuti_UASJava.repository.StatusCutiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusCutiService {

    @Autowired
    private StatusCutiRepository repo;

    public StatusCuti save(StatusCuti statusCuti){
        if(statusCuti.getId()!=null){ //
            StatusCuti current = repo.findById(statusCuti.getId()).get();
            current.setStatusCuti(statusCuti.getStatusCuti());
            current.setDeskripsi(statusCuti.getDeskripsi());
            statusCuti = current;
        }
        return repo.save(statusCuti);
    }
}
