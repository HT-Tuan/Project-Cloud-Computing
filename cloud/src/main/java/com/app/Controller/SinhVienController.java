package com.app.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.Response.SinhvienResponse;
import com.app.model.dao.ChuongTrinhDTDao;
import com.app.model.dao.SinhVienDao;
import com.app.model.entity.sinhvien;

@RestController
public class SinhVienController {
    private SinhVienDao sinhVienDao = new SinhVienDao();
    private ChuongTrinhDTDao chuongTrinhDTDao = new ChuongTrinhDTDao();
    private sinhvien temp;

    @GetMapping("/sinhviens")
    public List<SinhvienResponse> getAll() {
        return sinhVienDao.getAll();
    }

    @DeleteMapping("/sinhvien")
    public ResponseEntity<String> deleteID(@RequestParam(name = "masinhvien") String Id) {
        temp = null;
        temp = sinhVienDao.getID(Id);
        if (temp != null) {
            temp.setTrangThai(false);
            if (sinhVienDao.update(temp))
                return new ResponseEntity<>("Thành công", HttpStatus.OK);
            else
                return new ResponseEntity<>("Thất bại", HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>("Thất bại", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/sinhvien")
    public ResponseEntity<SinhvienResponse> create(@RequestBody SinhvienResponse data) {
        temp = null;
        temp = sinhVienDao.getID(data.getMasinhvien());
        if (temp != null && temp.getTrangThai() == true) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        if (temp == null) {
            temp = new sinhvien();
        }
        temp.setMaSinhVien(data.getMasinhvien());
        temp.setHoDem(data.getHodem());
        temp.setTen(data.getTen());
        temp.setNamHoc(data.getNamhoc());
        temp.setNgaySinh(data.getNgaysinh());
        temp.setNamNhapHoc(data.getNamnhaphoc());
        temp.setGioiTinh(data.getGioitinh());
        temp.setChuongtrinh(chuongTrinhDTDao.getName(data.getTenChuongTrinhDaoTao()));
        temp.setTrangThai(true);
        if (sinhVienDao.insert(temp) == true) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/sinhvien")
    public ResponseEntity<SinhvienResponse> update(@RequestBody SinhvienResponse data)
    {
        temp = null;
        temp = new sinhvien();
        temp.setMaSinhVien(data.getMasinhvien());
        temp.setHoDem(data.getHodem());
        temp.setTen(data.getTen());
        temp.setNamHoc(data.getNamhoc());
        temp.setNgaySinh(data.getNgaysinh());
        temp.setNamNhapHoc(data.getNamnhaphoc());
        temp.setGioiTinh(data.getGioitinh());
        temp.setChuongtrinh(chuongTrinhDTDao.getName(data.getTenChuongTrinhDaoTao()));
        temp.setTrangThai(true);
        if (sinhVienDao.update(temp) == true) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
