package org.javajarvis.SistemCuti_UASJava.service;

import org.javajarvis.SistemCuti_UASJava.model.HakCuti;
import org.javajarvis.SistemCuti_UASJava.repository.HakCutiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HakCutiService {

    @Autowired
    private HakCutiRepository repo;

    public HakCuti save(HakCuti hakCuti){
        if(hakCuti.getId()!=null){ //
            HakCuti current = repo.findById(hakCuti.getId()).get();
            current.setEmployee(hakCuti.getEmployee());
            current.setJenisCuti(hakCuti.getJenisCuti());
            current.setSisaCuti(hakCuti.getSisaCuti());
            hakCuti = current;
        }
        return repo.save(hakCuti);
    }

}
