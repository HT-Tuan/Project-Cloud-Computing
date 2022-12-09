package com.app.model.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "lophocphan")
public class lophocphan implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maLopHocPhan;

    private int namHoc;
    private String hocKy;

    @Column(name = "gioiHanSoLuongSinhVien")
    private int gioiHanSlg;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maMonHoc")
    private monhoc monHoc;

    @OneToMany(mappedBy = "lophoc", fetch = FetchType.LAZY)
    private Set<thamgiahoc> thamgiahocs;

    public int getMaLopHocPhan() {
        return maLopHocPhan;
    }

    public void setMaLopHocPhan(int maLopHocPhan) {
        this.maLopHocPhan = maLopHocPhan;
    }

    public int getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(int namHoc) {
        this.namHoc = namHoc;
    }

    public String getHocKy() {
        return hocKy;
    }

    public void setHocKy(String hocKy) {
        this.hocKy = hocKy;
    }

    public int getGioiHanSlg() {
        return gioiHanSlg;
    }

    public void setGioiHanSlg(int gioiHanSlg) {
        this.gioiHanSlg = gioiHanSlg;
    }

    public monhoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(monhoc monHoc) {
        this.monHoc = monHoc;
    }

    public Set<thamgiahoc> getThamgiahocs() {
        return thamgiahocs;
    }

    public void setThamgiahocs(Set<thamgiahoc> thamgiahocs) {
        this.thamgiahocs = thamgiahocs;
    }

}
