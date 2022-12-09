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

import com.app.Response.MonHocResponse;
import com.app.Response.ResponseObject;
import com.app.model.dao.MonHocDao;
import com.app.model.entity.monhoc;

@RestController
@RequestMapping (value = "/api")
public class MonHocController {
	private MonHocDao monHocDao = new MonHocDao();
	
	@GetMapping (value = "/monhoc")
	public List<MonHocResponse> findAll () {
		List<monhoc> monHocList = monHocDao.getAll();
		
		return convertToListResponseObject(monHocList);
	}
	
	@GetMapping (value = "/monhoc/{id}")
	public MonHocResponse findById (@PathVariable String id) { 
		monhoc MonHoc = monHocDao.select(id);
		
		return convertToResponseObject(MonHoc);
	}
	
	@PostMapping (value = "/monhoc")
	public ResponseEntity<ResponseObject> insert (@RequestBody MonHocResponse monHocResponse) {
		monhoc MonHoc = convertToEntityObject(monHocResponse);
		
		if (monHocDao.select(monHocResponse.getMaMonHoc()) != null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseObject("Failed", "The subject was existed", ""));
		}
		
		MonHoc = monHocDao.insert(MonHoc);
		
		return MonHoc == null ? 
				ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ResponseObject("Failed", "Failed to insert the subject", "")
				):
				ResponseEntity.status(HttpStatus.OK).body(
							new ResponseObject("Success", "Insert subject successfully", convertToResponseObject(MonHoc))
				);
	}
	
	@PutMapping (value = "/monhoc/{id}")
	private ResponseEntity<ResponseObject> update (@PathVariable String id, @RequestBody MonHocResponse monHocResponse) {
		monhoc MonHoc = monHocDao.select(id);	
		if (MonHoc == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseObject("Failed", "Can't find the subject to update", ""));
		}
		
		MonHoc.setLophocphans(null);
		MonHoc.setTenMonHoc(monHocResponse.getTenMonHoc());
		MonHoc.setSoTinchi(monHocResponse.getSoTinchi());
		MonHoc.setTheLoai(monHocResponse.getTheLoai());
				
		MonHoc.setChuongtrinh(monHocDao.selectChuongTrinhDaoTao(monHocResponse.getIdChuongTrinhDaoTao()));
	
		MonHoc = monHocDao.update(MonHoc);
		
		return MonHoc == null ?
				ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ResponseObject("Failed", "Failed to update the subject", "")
				):
				ResponseEntity.status(HttpStatus.OK).body(
						new ResponseObject("Success", "Update the subject successfully", convertToResponseObject(MonHoc))
				);				
	}
	
	@DeleteMapping (value = "/monhoc/{id}")
	public ResponseEntity<ResponseObject> delete (@PathVariable String id) {
		monhoc MonHoc = monHocDao.delete(id);
		
		return MonHoc == null ? 
				ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ResponseObject("Failed", "Failed to delete the subject", "")
				):
				ResponseEntity.status(HttpStatus.OK).body(
							new ResponseObject("Success", "Delete subject successfully", convertToResponseObject(MonHoc))
				);
	}
	
	private monhoc convertToEntityObject (MonHocResponse response) {
		if (response == null) {
			return null;
		}
		monhoc MonHoc = new monhoc();
		MonHoc.setMaMonHoc(response.getMaMonHoc());
		MonHoc.setSoTinchi(response.getSoTinchi());
		MonHoc.setTenMonHoc(response.getTenMonHoc());
		MonHoc.setTheLoai(response.getTheLoai());
		MonHoc.setChuongtrinh(monHocDao.selectChuongTrinhDaoTao(response.getIdChuongTrinhDaoTao()));
		
		return MonHoc;
	}
	
	private MonHocResponse convertToResponseObject (monhoc MonHoc) {
		if (MonHoc == null) {
			return null;
		}
		MonHocResponse response = new MonHocResponse();
		response.setMaMonHoc(MonHoc.getMaMonHoc());
		response.setSoTinchi(MonHoc.getSoTinchi());
		response.setTenMonHoc(MonHoc.getTenMonHoc());
		response.setTheLoai(MonHoc.getTheLoai());
		response.setIdChuongTrinhDaoTao(MonHoc.getChuongtrinh().getIdChuongTrinhDaoTao());
		
		return response;
	}
	
	private List<MonHocResponse> convertToListResponseObject (List<monhoc> ListMonHoc) {
		if (ListMonHoc == null || ListMonHoc.isEmpty()) {
			return null;
		}
		
		List<MonHocResponse> response = new ArrayList<MonHocResponse>();
		for (monhoc MonHoc : ListMonHoc) {
			response.add(convertToResponseObject(MonHoc));
		}
		
		return response;
	}
}
