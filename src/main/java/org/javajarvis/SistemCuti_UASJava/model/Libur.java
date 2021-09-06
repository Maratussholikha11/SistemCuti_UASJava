package org.javajarvis.SistemCuti_UASJava.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_libur")
@Data
@Setter @Getter
public class Libur extends BaseEntity<String>{

    private Integer id;
    private String deskripsi;

    @Column(name = "tgl_libur")
    private Date tglLibur;

    @Column(name = "nama_libur")
    private String namaLibur;

    @Id
    @Column(name = "libur_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
}
