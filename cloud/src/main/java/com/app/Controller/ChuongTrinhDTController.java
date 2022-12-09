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

import com.app.Response.ChuongTrinhReponse;
import com.app.model.dao.ChuongTrinhDTDao;
import com.app.model.entity.chuongtrinhdaotao;

@RestController
public class ChuongTrinhDTController {
    private ChuongTrinhDTDao chuongTrinhDTDao = new ChuongTrinhDTDao();
    private chuongtrinhdaotao chuongtrinh;

    @GetMapping("/chuongtrinhs")
    public List<ChuongTrinhReponse> getAll() {
        return chuongTrinhDTDao.getAll();
    }

    @DeleteMapping("chuongtrinh")
    public ResponseEntity<String> deleteID(@RequestParam(name = "idChuongTrinhDaoTao") String Id) {
        chuongtrinh = null;
        if (chuongTrinhDTDao.deleteFK(Id) == true) {
            chuongtrinh = chuongTrinhDTDao.getFindId(Id);
            if (chuongtrinh != null && chuongTrinhDTDao.delete(chuongtrinh) == true) {
                return new ResponseEntity<>("Thành công", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Thất bại", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/chuongtrinh")
    public ResponseEntity<ChuongTrinhReponse> create(@RequestBody ChuongTrinhReponse data) {
        chuongtrinh = null;
        chuongtrinh = chuongTrinhDTDao.getFindId(data.getIdChuongTrinhDaoTao());
        if (chuongtrinh != null) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        chuongtrinh = new chuongtrinhdaotao();
        chuongtrinh.setIdChuongTrinhDaoTao(data.getIdChuongTrinhDaoTao());
        chuongtrinh.setTenChuongTrinhDaoTao(data.getTenChuongTrinhDaoTao());
        chuongtrinh.setSoTinChi(data.getSoTinChi());
        chuongtrinh.setNamBatDauDaoTao(data.getNamBatDauDaoTao());

        if (chuongTrinhDTDao.insert(chuongtrinh) == true) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PutMapping("/chuongtrinh")
    public ResponseEntity<ChuongTrinhReponse> update(@RequestBody ChuongTrinhReponse data) {
        chuongtrinh = null;
        chuongtrinh = new chuongtrinhdaotao();
        chuongtrinh.setIdChuongTrinhDaoTao(data.getIdChuongTrinhDaoTao());
        chuongtrinh.setTenChuongTrinhDaoTao(data.getTenChuongTrinhDaoTao());
        chuongtrinh.setSoTinChi(data.getSoTinChi());
        chuongtrinh.setNamBatDauDaoTao(data.getNamBatDauDaoTao());
        if (chuongTrinhDTDao.update(chuongtrinh) == true) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
