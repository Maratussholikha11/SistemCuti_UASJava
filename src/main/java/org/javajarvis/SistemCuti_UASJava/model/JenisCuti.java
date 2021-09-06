package org.javajarvis.SistemCuti_UASJava.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tbl_jenis_cuti")
@Data
@Setter @Getter
public class JenisCuti extends BaseEntity<String> {

    private Integer id;

    @Column(name = "jenis_cuti" )
    private String jenisCuti;
    private String deskripsi;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jenis_cuti_id", nullable = false, unique = true)
    public Integer getId() {
        return id;
    }


}
