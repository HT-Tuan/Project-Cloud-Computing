package com.app.model.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "chuongtrinhdaotao")
public class chuongtrinhdaotao implements Serializable{
    @Id
    private String idChuongTrinhDaoTao;

    private String tenChuongTrinhDaoTao;
    private Float soTinChi;
    private int namBatDauDaoTao;

    @OneToMany(mappedBy = "chuongtrinh", fetch = FetchType.EAGER)
    private Set<sinhvien> sinhviens;

    @OneToMany(mappedBy = "chuongtrinh", fetch = FetchType.EAGER)
    private Set<monhoc> monhocs;

    public String getIdChuongTrinhDaoTao() {
        return idChuongTrinhDaoTao;
    }

    public void setIdChuongTrinhDaoTao(String idChuongTrinhDaoTao) {
        this.idChuongTrinhDaoTao = idChuongTrinhDaoTao;
    }

    public String getTenChuongTrinhDaoTao() {
        return tenChuongTrinhDaoTao;
    }

    public void setTenChuongTrinhDaoTao(String tenChuongTrinhDaoTao) {
        this.tenChuongTrinhDaoTao = tenChuongTrinhDaoTao;
    }

    public Float getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(Float soTinChi) {
        this.soTinChi = soTinChi;
    }

    public int getNamBatDauDaoTao() {
        return namBatDauDaoTao;
    }

    public void setNamBatDauDaoTao(int namBatDauDaoTao) {
        this.namBatDauDaoTao = namBatDauDaoTao;
    }

    public Set<sinhvien> getSinhviens() {
        return sinhviens;
    }

    public void setSinhviens(Set<sinhvien> sinhviens) {
        this.sinhviens = sinhviens;
    }

    public Set<monhoc> getMonhocs() {
        return monhocs;
    }

    public void setMonhocs(Set<monhoc> monhocs) {
        this.monhocs = monhocs;
    }
}
