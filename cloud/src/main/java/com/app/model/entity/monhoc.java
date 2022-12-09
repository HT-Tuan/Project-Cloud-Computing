package com.app.model.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "monhoc")
public class monhoc implements Serializable{
    @Id
    private String maMonHoc;

    private String tenMonHoc;
    private Float soTinchi;
    private String theLoai;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idchuongtrinhdaotao")
    private chuongtrinhdaotao chuongtrinh;

    @OneToMany(mappedBy = "monHoc", fetch = FetchType.LAZY)
    private Set<lophocphan> lophocphans;

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public Float getSoTinchi() {
        return soTinchi;
    }

    public void setSoTinchi(Float soTinchi) {
        this.soTinchi = soTinchi;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public chuongtrinhdaotao getChuongtrinh() {
        return chuongtrinh;
    }

    public void setChuongtrinh(chuongtrinhdaotao chuongtrinh) {
        this.chuongtrinh = chuongtrinh;
    }

    public Set<lophocphan> getLophocphans() {
        return lophocphans;
    }

    public void setLophocphans(Set<lophocphan> lophocphans) {
        this.lophocphans = lophocphans;
    }

}
