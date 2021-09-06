package org.javajarvis.SistemCuti_UASJava.repository;

import org.javajarvis.SistemCuti_UASJava.model.PengajuanCuti;
import org.javajarvis.SistemCuti_UASJava.model.StatusCuti;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

public interface PengajuanCutiRepository extends CrudRepository<PengajuanCuti, Integer> {
    @Query("SELECT p FROM PengajuanCuti p WHERE p.statusCuti.id = :id")
    public List<PengajuanCuti> findPengajuanCutisByStatusCuti(@PathParam("b") Integer id);

    Optional<StatusCuti> findByStatusCuti (StatusCuti id);
}
