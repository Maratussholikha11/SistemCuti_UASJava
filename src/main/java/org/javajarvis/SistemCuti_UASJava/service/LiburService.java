package org.javajarvis.SistemCuti_UASJava.service;

import org.javajarvis.SistemCuti_UASJava.model.Libur;
import org.javajarvis.SistemCuti_UASJava.repository.LiburRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LiburService {

    @Autowired
    private LiburRepository rr;

    public Libur save(Libur libur){
//        Optional<Libur> cek = rr.findById(libur.getId());
        if(libur.getId()!=null){ //
            Libur current = rr.findById(libur.getId()).get();
            current.setDeskripsi(libur.getDeskripsi());
            current.setTglLibur(libur.getTglLibur());
            current.setNamaLibur(libur.getNamaLibur());
            libur = current;
        }
        return rr.save(libur);
    }
}
