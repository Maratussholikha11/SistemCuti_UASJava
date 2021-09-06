package org.javajarvis.SistemCuti_UASJava.service;

import org.javajarvis.SistemCuti_UASJava.model.Libur;
import org.javajarvis.SistemCuti_UASJava.model.PengajuanCuti;
import org.javajarvis.SistemCuti_UASJava.model.StatusCuti;
import org.javajarvis.SistemCuti_UASJava.repository.PengajuanCutiRepository;
import org.javajarvis.SistemCuti_UASJava.repository.StatusCutiRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PengajuanCutiService {

    @Autowired
    private PengajuanCutiRepository repo;

    @Autowired DetailPengajuanCutiService detail;

    @Autowired
    StatusCutiRepository sr;

    @Autowired
    private ModelMapper modelMapper;

    public PengajuanCuti save(PengajuanCuti pengajuanCuti){
        if(pengajuanCuti.getId()!=null){ //
            PengajuanCuti current = repo.findById(pengajuanCuti.getId()).get();
//            PengajuanCuti pc  = modelMapper.map(pengajuanCuti, PengajuanCuti.class);
//            StatusCuti s = sr.getById(1);
            current.setStatusCuti(pengajuanCuti.getStatusCuti());
            current.setPenggantiId(pengajuanCuti.getPenggantiId());
            current.setNoTelp(pengajuanCuti.getNoTelp());
            current.setLamaCuti(pengajuanCuti.getLamaCuti());
            current.setKeterangan(pengajuanCuti.getKeterangan());
            current.setHrdId(pengajuanCuti.getHrdId());
            current.setEmployee(pengajuanCuti.getEmployee());
            current.setAlamat(pengajuanCuti.getAlamat());
            pengajuanCuti = current;
        }
        return repo.save(pengajuanCuti);
    }

    public List<PengajuanCuti> findByStatusCutiOpen(Integer id){
        return repo.findPengajuanCutisByStatusCuti(id);
    }


}
