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
     private String maSinhVien;

     private String hoDem;
     private String ten;
     private int namHoc;

     @Temporal(javax.persistence.TemporalType.DATE)
     private Date ngaySinh;

     private int namNhapHoc;
     private Boolean gioiTinh;
     private Boolean trangThai;
     @ManyToOne(fetch = FetchType.EAGER)
     @JoinColumn(name = "idChuongTrinhDaoTao")
     private chuongtrinhdaotao chuongtrinh;

     @OneToMany(mappedBy = "Sinhvien", fetch = FetchType.LAZY)
     private Set<thamgiahoc> thamgiahocs;

     public String getMaSinhVien() {
          return maSinhVien;
     }

     public void setMaSinhVien(String maSinhVien) {
          this.maSinhVien = maSinhVien;
     }

     public String getHoDem() {
          return hoDem;
     }

     public void setHoDem(String hoDem) {
          this.hoDem = hoDem;
     }

     public String getTen() {
          return ten;
     }

     public void setTen(String ten) {
          this.ten = ten;
     }

     public int getNamHoc() {
          return namHoc;
     }

     public void setNamHoc(int namHoc) {
          this.namHoc = namHoc;
     }

     public Date getNgaySinh() {
          return ngaySinh;
     }

     public void setNgaySinh(Date ngaySinh) {
          this.ngaySinh = ngaySinh;
     }

     public int getNamNhapHoc() {
          return namNhapHoc;
     }

     public void setNamNhapHoc(int namNhapHoc) {
          this.namNhapHoc = namNhapHoc;
     }

     public Boolean getGioiTinh() {
          return gioiTinh;
     }

     public void setGioiTinh(Boolean gioiTinh) {
          this.gioiTinh = gioiTinh;
     }

     public Boolean getTrangThai() {
          return trangThai;
     }

     public void setTrangThai(Boolean trangThai) {
          this.trangThai = trangThai;
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

     
}
