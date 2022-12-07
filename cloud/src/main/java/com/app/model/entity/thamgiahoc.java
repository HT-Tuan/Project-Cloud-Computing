package com.app.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "thamgiahoc")
public class thamgiahoc implements Serializable{
    @Id
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "maLopHocPhan")
    private lophocphan lophoc;

    @Id
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "maSinhVien")
    private sinhvien Sinhvien;

    private Float diemSo;

    public lophocphan getLophoc() {
        return lophoc;
    }

    public void setLophoc(lophocphan lophoc) {
        this.lophoc = lophoc;
    }

    public sinhvien getSinhvien() {
        return Sinhvien;
    }

    public void setSinhvien(sinhvien sinhvien) {
        Sinhvien = sinhvien;
    }

    public Float getDiemSo() {
        return diemSo;
    }

    public void setDiemSo(Float diemSo) {
        this.diemSo = diemSo;
    }

}
