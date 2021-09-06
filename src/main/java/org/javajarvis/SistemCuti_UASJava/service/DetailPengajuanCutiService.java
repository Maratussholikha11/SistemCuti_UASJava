package org.javajarvis.SistemCuti_UASJava.service;

import org.javajarvis.SistemCuti_UASJava.model.DetailPengajuanCuti;
import org.javajarvis.SistemCuti_UASJava.repository.DetailPengajuanCutiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetailPengajuanCutiService {

    @Autowired
    private DetailPengajuanCutiRepository repo;

    public DetailPengajuanCuti save(DetailPengajuanCuti dpc){
        if(dpc.getId()!=null){ //
            DetailPengajuanCuti current = repo.findById(dpc.getId()).get();
            current.setJenisCuti(dpc.getJenisCuti());
            current.setPengajuanCuti(dpc.getPengajuanCuti());
            current.setTglCuti(dpc.getTglCuti());
            dpc = current;
        }
        return repo.save(dpc);
    }

    public DetailPengajuanCuti findOne(Integer id){
        Optional<DetailPengajuanCuti> detail = repo.findById(id);
        if (!detail.isPresent()){
            return null;
        }
        return detail.get();
    }
}
