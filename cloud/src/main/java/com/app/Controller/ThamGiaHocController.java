package com.app.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Response.ResponseObject;
import com.app.Response.ThamGiaHocResponse;
import com.app.model.dao.LopHocPhanDao;
import com.app.model.dao.SinhVienDao;
import com.app.model.dao.ThamGiaHocDao;
import com.app.model.entity.thamgiahoc;

@RestController
@RequestMapping (value = "/api")
public class ThamGiaHocController {
	private LopHocPhanDao lopHocPhanDao = new LopHocPhanDao();
	private SinhVienDao sinhVienDao = new SinhVienDao();
	private ThamGiaHocDao thamGiaHocDao = new ThamGiaHocDao();
	
	@GetMapping (value = "/thamgiahoc/{maLopHocPhan}/{maSinhVien}")
	public ThamGiaHocResponse select (@PathVariable int maLopHocPhan, @PathVariable String maSinhVien) {
		thamgiahoc ThamGia = thamGiaHocDao.select(maLopHocPhan, maSinhVien);
		
		return convertToResponseObject(ThamGia);		
	}
	
	@GetMapping (value = "/thamgiahoc/malop/{maLopHocPhan}")
	public List<ThamGiaHocResponse> selectByMaLop (@PathVariable int maLopHocPhan) {
		List<thamgiahoc> ThamGia = thamGiaHocDao.selectByMaLop(maLopHocPhan);
		
		return convertToListResponseObject(ThamGia);
	}
	
	@GetMapping (value = "/thamgiahoc/masv/{maSinhVien}")
	public List<ThamGiaHocResponse> selectByMaSV (@PathVariable String maSinhVien) {
		List<thamgiahoc> ThamGia = thamGiaHocDao.selectByMaSV(maSinhVien);
		
		return convertToListResponseObject(ThamGia);		
	}
	
	@GetMapping (value = "/thamgiahoc")
	public List<ThamGiaHocResponse> selectAll () {
		List<thamgiahoc> ThamGia = thamGiaHocDao.selectAll();
		
		return convertToListResponseObject(ThamGia);
	}
	
	@PostMapping (value = "/thamgiahoc")
	public ResponseEntity<ResponseObject> insert (@RequestBody ThamGiaHocResponse response) {
		thamgiahoc ThamGia = convertToEntityObject(response);
		ThamGia = thamGiaHocDao.insert(ThamGia);
		
		if (ThamGia == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseObject("Failed", "Failed to insert the object", ""));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("Success", "Insert the object successfully", convertToResponseObject(ThamGia)));
		}
	} 
	
	@PutMapping (value = "/thamgiahoc/{maLopHocPhan}/{maSinhVien}")
	public ResponseEntity<ResponseObject> update (@PathVariable int maLopHocPhan, @PathVariable String maSinhVien,
			@RequestBody ThamGiaHocResponse response) {
		thamgiahoc ThamGia = thamGiaHocDao.select(maLopHocPhan, maSinhVien);
		if (ThamGia == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseObject("Failed", "Failed to update the object", ""));
		}
				
		ThamGia.setDiemSo(response.getDiemSo());
		
		ThamGia = thamGiaHocDao.update(ThamGia);
		
		if (ThamGia == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseObject("Failed", "Failed to update the object", ""));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("Success", "Update the object successfully", convertToResponseObject(ThamGia)));
		}
	} 
	
	@DeleteMapping (value = "/thamgiahoc/{maLopHocPhan}/{maSinhVien}")
	public ResponseEntity<ResponseObject> update (@PathVariable int maLopHocPhan, 
			@PathVariable String maSinhVien) {
		thamgiahoc ThamGia = thamGiaHocDao.delete(maLopHocPhan, maSinhVien);
		
		if (ThamGia == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseObject("Failed", "Failed to update the object", ""));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("Success", "Delete the object successfully", convertToResponseObject(ThamGia)));
		}
	} 
	
	private thamgiahoc convertToEntityObject (ThamGiaHocResponse response) {
		thamgiahoc ThamGia = new thamgiahoc();
		
		ThamGia.setLophoc(lopHocPhanDao.select(response.getMaLopHocPhan()));
		ThamGia.setSinhvien(sinhVienDao.getID(response.getMaSinhVien()));
		ThamGia.setDiemSo(response.getDiemSo());
		
		return ThamGia;
	}
	
	private ThamGiaHocResponse convertToResponseObject (thamgiahoc ThamGia) {
		if (ThamGia == null) {
			return null;
		}
		
		ThamGiaHocResponse response = new ThamGiaHocResponse();
		response.setMaLopHocPhan(ThamGia.getLophoc().getMaLopHocPhan());
		response.setMaSinhVien(ThamGia.getSinhvien().getMaSinhVien());
		response.setDiemSo(ThamGia.getDiemSo());
		
		return response;
	}
	
	private List<ThamGiaHocResponse> convertToListResponseObject (List<thamgiahoc> ListThamGia) {
		if (ListThamGia == null || ListThamGia.isEmpty()) {
			return null;
		}
		
		List<ThamGiaHocResponse> listResponse = new ArrayList<ThamGiaHocResponse>();
		for (thamgiahoc ThamGia : ListThamGia) {
			listResponse.add(convertToResponseObject(ThamGia));
		}
		
		return listResponse;
	}
}
