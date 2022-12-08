package com.app.Response;

import java.util.Date;

public class SinhvienResponse {
    private String masinhvien;
    private String hodem;
    private String ten;
    private int namhoc;
    private Date ngaysinh;
    private int namnhaphoc;
    private Boolean gioitinh;
    private String tenChuongTrinhDaoTao;

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

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
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

    public String getTenChuongTrinhDaoTao() {
        return tenChuongTrinhDaoTao;
    }

    public void setTenChuongTrinhDaoTao(String tenChuongTrinhDaoTao) {
        this.tenChuongTrinhDaoTao = tenChuongTrinhDaoTao;
    }

}
