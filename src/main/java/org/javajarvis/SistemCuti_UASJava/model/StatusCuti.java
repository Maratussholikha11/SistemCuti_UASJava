package org.javajarvis.SistemCuti_UASJava.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tbl_status_cuti")
@Data
@Setter @Getter
public class StatusCuti extends BaseEntity<String>{

    private Integer id;
    private String deskripsi;

    @Column(name = "status_cuti")
    private String statusCuti;

    @Id
    @Column(name = "statuscuti_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
}
