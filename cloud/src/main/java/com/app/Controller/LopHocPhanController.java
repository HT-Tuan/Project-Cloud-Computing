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

import com.app.Response.LopHocPhanResponse;
import com.app.Response.MonHocResponse;
import com.app.Response.ResponseObject;
import com.app.model.dao.LopHocPhanDao;
import com.app.model.dao.MonHocDao;
import com.app.model.entity.lophocphan;
import com.app.model.entity.monhoc;

@RestController
@RequestMapping(value = "/api")
public class LopHocPhanController {
	private LopHocPhanDao lopHocPhanDao = new LopHocPhanDao();
	private MonHocDao monHocDao = new MonHocDao();

	@GetMapping(value = "/lophocphan")
	public List<LopHocPhanResponse> findAll() {
		List<lophocphan> list = lopHocPhanDao.findAll();

		return convertToListResponseObject(list);
	}

	@GetMapping(value = "/lophocphan/{id}")
	public LopHocPhanResponse findById(@PathVariable int id) {
		lophocphan LopHocPhan = lopHocPhanDao.select(id);

		return convertToResponseObject(LopHocPhan);
	}

	@PostMapping(value = "/lophocphan")
	public ResponseEntity<ResponseObject> insert(@RequestBody LopHocPhanResponse response) {
		lophocphan LopHocPhan = convertToEntityObject(response);

		LopHocPhan = lopHocPhanDao.insert(LopHocPhan);

		if (LopHocPhan == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject("Failed", "Failed to insert the class", ""));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Success",
					"Insert the class successfully", convertToResponseObject(LopHocPhan)));
		}
	}

	@PutMapping(value = "/lophocphan/{id}")
	public ResponseEntity<ResponseObject> update(@PathVariable int id, @RequestBody LopHocPhanResponse response) {
		lophocphan LopHocPhan = lopHocPhanDao.select(id);
		if (LopHocPhan == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject("Failed", "Failed to update the class", ""));
		}

		LopHocPhan.setMonHoc(monHocDao.select(response.getMaMonHoc()));
		LopHocPhan.setNamHoc(response.getNamHoc());
		LopHocPhan.setHocKy(response.getHocKy());
		LopHocPhan.setGioiHanSlg(response.getGioiHanSlg());
		LopHocPhan.setThamgiahocs(null);

		LopHocPhan = lopHocPhanDao.update(LopHocPhan);

		if (LopHocPhan == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject("Failed", "Failed to update the class", ""));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Success",
					"Updated the class successfully", convertToResponseObject(LopHocPhan)));
		}
	}

	@DeleteMapping(value = "/lophocphan/{id}")
	public ResponseEntity<ResponseObject> delete(@PathVariable int id) {
		lophocphan LopHoccPhan = lopHocPhanDao.delete(id);

		return LopHoccPhan == null
				? ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject("Failed", "Failed to delete the subject", ""))
				: ResponseEntity.status(HttpStatus.OK).body(
						new ResponseObject("Success", "Delete subject successfully", convertToResponseObject(LopHoccPhan)));
	}

	private lophocphan convertToEntityObject(LopHocPhanResponse response) {
		if (response == null) {
			return null;
		}

		lophocphan LopHocPhan = new lophocphan();
		LopHocPhan.setMaLopHocPhan(response.getMaLopHocPhan());

		LopHocPhan.setMonHoc(monHocDao.select(response.getMaMonHoc()));
		LopHocPhan.setNamHoc(response.getNamHoc());
		LopHocPhan.setHocKy(response.getHocKy());
		LopHocPhan.setGioiHanSlg(response.getGioiHanSlg());

		return LopHocPhan;
	}

	private LopHocPhanResponse convertToResponseObject(lophocphan LopHocPhan) {
		if (LopHocPhan == null) {
			return null;
		}

		LopHocPhanResponse response = new LopHocPhanResponse();
		response.setMaLopHocPhan(LopHocPhan.getMaLopHocPhan());
		response.setMaMonHoc(LopHocPhan.getMonHoc().getMaMonHoc());
		response.setNamHoc(LopHocPhan.getNamHoc());
		response.setHocKy(LopHocPhan.getHocKy());
		response.setGioiHanSlg(LopHocPhan.getGioiHanSlg());

		return response;
	}

	private List<LopHocPhanResponse> convertToListResponseObject(List<lophocphan> ListLopHocPhan) {
		if (ListLopHocPhan == null || ListLopHocPhan.isEmpty()) {
			return null;
		}

		List<LopHocPhanResponse> response = new ArrayList<LopHocPhanResponse>();
		for (lophocphan LopHocPhan : ListLopHocPhan) {
			response.add(convertToResponseObject(LopHocPhan));
		}

		return response;
	}
}
