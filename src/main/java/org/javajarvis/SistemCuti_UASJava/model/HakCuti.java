package org.javajarvis.SistemCuti_UASJava.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tbl_hak_cuti")
@Setter @Getter @Data
public class HakCuti extends BaseEntity<String> {

    private Integer id;
    private JenisCuti jenisCuti;
    private Employee employee;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hak_cuti_id")
    public Integer getId() {
        return id;
    }

    @Column(name = "sisa_cuti")
    private  Integer sisaCuti;

    @ManyToOne
    @JoinColumn(name = "jenis_cuti_id")
    public JenisCuti getJenisCuti() {
        return jenisCuti;
    }

    public void setJenisCuti(JenisCuti jenisCuti) {
        this.jenisCuti = jenisCuti;
    }

    @ManyToOne
    @JoinColumn(name = "employee_id")
    public Employee getEmployee() {
        return employee;
    }
}
