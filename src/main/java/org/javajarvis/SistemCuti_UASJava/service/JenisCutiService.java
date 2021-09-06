package org.javajarvis.SistemCuti_UASJava.service;

import org.javajarvis.SistemCuti_UASJava.model.JenisCuti;
import org.javajarvis.SistemCuti_UASJava.repository.JenisCutiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JenisCutiService {

    @Autowired
    private JenisCutiRepository rr;

    public JenisCuti save(JenisCuti jenisCuti){
        if(jenisCuti.getId()!=null){ //
            JenisCuti current = rr.findById(jenisCuti.getId()).get();
            current.setJenisCuti(jenisCuti.getJenisCuti());
            current.setDeskripsi(jenisCuti.getDeskripsi());
            jenisCuti = current;
        }
        return rr.save(jenisCuti);
    }

}
