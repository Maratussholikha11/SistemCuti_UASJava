package org.javajarvis.SistemCuti_UASJava.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_detail_pengajuan_cuti")
@Data @Setter @Getter
public class    DetailPengajuanCuti extends BaseEntity<String>  {

    private Integer id;
    private  PengajuanCuti pengajuanCuti;
    private JenisCuti jenisCuti;

    @Column(name = "tgl_cuti")
    private Date tglCuti;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_pengajuan_cuti_id")
    public Integer getId() {
        return id;
    }

    @OneToOne
    @JsonIgnoreProperties({"detailPengajuanCuti"})
    @JoinColumn(name = "pengajuan_cuti_id")
    public PengajuanCuti getPengajuanCuti() {
        return pengajuanCuti;
    }

    @ManyToOne
    @JoinColumn(name = "jenis_cuti_id")
    public JenisCuti getJenisCuti() {
        return jenisCuti;
    }
}
