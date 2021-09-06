package org.javajarvis.SistemCuti_UASJava.service;

import org.javajarvis.SistemCuti_UASJava.model.Libur;
import org.javajarvis.SistemCuti_UASJava.model.Role;
import org.javajarvis.SistemCuti_UASJava.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository rr;

    public Role save(Role role){
        if(role.getId()!=null){ //
            Role current = rr.findById(role.getId()).get();
            current.setNameRole(role.getNameRole());
            role = current;
        }
        return rr.save(role);
    }

    public void removeOne(Integer id){
        rr.deleteById(id);
    }

    public Role findOne(Integer id){
        Optional<Role> role = rr.findById(id);
        if (!role.isPresent()){
            return  null;
        }
        return role.get();
    }
}
