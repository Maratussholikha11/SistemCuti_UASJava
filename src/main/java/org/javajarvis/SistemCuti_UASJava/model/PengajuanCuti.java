package org.javajarvis.SistemCuti_UASJava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tbl_pengajuan_cuti")
@Data
@Setter @Getter
public class PengajuanCuti extends BaseEntity<String> implements Serializable {
    private Integer id;
    private Employee employee;
    private StatusCuti statusCuti;
    private DetailPengajuanCuti detailPengajuanCuti;

    @Column(name = "pengganti_id")
    private  Integer penggantiId;

    @Column(name = "hrd_id")
    private Integer hrdId;

    private String alamat;

    @Column(name = "no_telp")
    private String noTelp;

    private String keterangan;

    @Column(name = "lama_cuti")
    private Integer lamaCuti;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setStatusCuti(StatusCuti statusCuti) {
        this.statusCuti = statusCuti;
    }

    public Integer getPenggantiId() {
        return penggantiId;
    }

    public void setPenggantiId(Integer penggantiId) {
        this.penggantiId = penggantiId;
    }

    public Integer getHrdId() {
        return hrdId;
    }

    public void setHrdId(Integer hrdId) {
        this.hrdId = hrdId;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getLamaCuti() {
        return lamaCuti;
    }

    public void setLamaCuti(Integer lamaCuti) {
        this.lamaCuti = lamaCuti;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pengajuan_cuti_id")

    public Integer getId() {
        return id;
    }



    @ManyToOne
    @JoinColumn(name = "employee_id")
    public Employee getEmployee() {
        return employee;
    }

    @ManyToOne
    @JoinColumn(name = "status_cuti_id")
    public StatusCuti getStatusCuti() {
        return statusCuti;
    }

    @JsonIgnoreProperties({"pengajuanCuti"})
    @OneToOne(mappedBy = "pengajuanCuti", cascade = CascadeType.ALL)
    public DetailPengajuanCuti getDetailPengajuanCuti() {
        return detailPengajuanCuti;
    }

    public void setDetailPengajuanCuti(DetailPengajuanCuti detailPengajuanCuti) {
        detailPengajuanCuti.setPengajuanCuti(this);
        this.detailPengajuanCuti = detailPengajuanCuti;
    }


}
