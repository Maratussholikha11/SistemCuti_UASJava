package org.javajarvis.SistemCuti_UASJava.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_role")
@Data
@Getter @Setter
public class Role extends  BaseEntity<String> {

    private Integer id;


    @Column(name = "nama_role")
    private String nameRole;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    public Integer getId() {
        return id;
    }

}
