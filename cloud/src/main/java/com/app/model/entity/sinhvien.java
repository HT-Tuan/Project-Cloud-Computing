package com.app.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "sinhvien")
public class sinhvien implements Serializable{

     @Id
     private String masinhvien;

     private String hodem;
     private String ten;
     private int namhoc;

     @Temporal(javax.persistence.TemporalType.DATE)
     private Date ngaysinh;

     private int namnhaphoc;
     private Boolean gioitinh;

     @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
     @JoinColumn(name = "idchuongtrinhdaotao")
     private chuongtrinhdaotao chuongtrinh;

     @OneToMany(mappedBy = "Sinhvien", fetch = FetchType.EAGER)
     private Set<thamgiahoc> thamgiahocs;

     public String getMasinhvien() {
          return masinhvien;
     }

     public void setMasinhvien(String masinhvien) {
          this.masinhvien = masinhvien;
     }

     public String getHodem() {
          return hodem;
     }

     public void setHodem(String hodem) {
          this.hodem = hodem;
     }

     public int getNamhoc() {
          return namhoc;
     }

     public void setNamhoc(int namhoc) {
          this.namhoc = namhoc;
     }

     public Date getNgaysinh() {
          return ngaysinh;
     }

     public void setNgaysinh(Date ngaysinh) {
          this.ngaysinh = ngaysinh;
     }

     public int getNamnhaphoc() {
          return namnhaphoc;
     }

     public void setNamnhaphoc(int namnhaphoc) {
          this.namnhaphoc = namnhaphoc;
     }

     public Boolean getGioitinh() {
          return gioitinh;
     }

     public void setGioitinh(Boolean gioitinh) {
          this.gioitinh = gioitinh;
     }

     public chuongtrinhdaotao getChuongtrinh() {
          return chuongtrinh;
     }

     public void setChuongtrinh(chuongtrinhdaotao chuongtrinh) {
          this.chuongtrinh = chuongtrinh;
     }

     public Set<thamgiahoc> getThamgiahocs() {
          return thamgiahocs;
     }

     public void setThamgiahocs(Set<thamgiahoc> thamgiahocs) {
          this.thamgiahocs = thamgiahocs;
     }

     public String getTen() {
          return ten;
     }

     public void setTen(String ten) {
          this.ten = ten;
     }
     
}
