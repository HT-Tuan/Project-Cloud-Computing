--drop table
DROP TABLE IF EXISTS public.thamgiahoc;
DROP TABLE IF EXISTS public.sinhvien;
DROP TABLE IF EXISTS public.lophocphan;
DROP TABLE IF EXISTS public.monhoc;
DROP TABLE IF EXISTS public.chuongtrinhdaotao;

-- 
CREATE TABLE IF NOT EXISTS public.chuongtrinhdaotao
(
	idChuongTrinhDaoTao character varying(10),
    tenChuongTrinhDaoTao text,
	soTinChi FLOAT,
    namBatDauDaoTao INT,
    CONSTRAINT chuongtrinhdaotao_pkey PRIMARY KEY (idChuongTrinhDaoTao)
);

--
CREATE TABLE IF NOT EXISTS public.monhoc
(
    maMonHoc TEXT,
    tenMonHoc TEXT,
   	soTinChi FLOAT,
	theLoai TEXT,
	idChuongTrinhDaoTao TEXT,
    CONSTRAINT monhoc_pkey PRIMARY KEY (maMonHoc),
    CONSTRAINT FK_monhoc_chuongtrinhdaotao FOREIGN KEY (idChuongTrinhDaoTao)
        REFERENCES public.chuongtrinhdaotao (idChuongTrinhDaoTao) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

--create table account
CREATE TABLE IF NOT EXISTS public.lophocphan
(
    maLopHocPhan SERIAL,
    maMonHoc TEXT,
    namHoc INT,
	hocKy TEXT,
	gioiHanSoLuongSinhVien INT,
    CONSTRAINT lophocphan_pkey PRIMARY KEY (maLopHocPhan),
    CONSTRAINT FK_lophocphan_monhoc FOREIGN KEY (maMonHoc)
        REFERENCES public.monhoc (maMonHoc) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
--

CREATE TABLE IF NOT EXISTS public.sinhvien
(
    maSinhVien TEXT,
    hoDem TEXT,
    namHoc INT,
	ten TEXT,
	ngaySinh date,
	namNhapHoc INT,
	idChuongTrinhDaoTao TEXT,
	gioiTinh BOOLEAN,
    CONSTRAINT sinhvien_pkey PRIMARY KEY (maSinhVien),
    CONSTRAINT FK_sinhvien_chuongtrinhdaotao FOREIGN KEY (idChuongTrinhDaoTao)
        REFERENCES public.chuongtrinhdaotao (idChuongTrinhDaoTao) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

--
CREATE TABLE IF NOT EXISTS public.thamgiahoc
(
    maLopHocPhan INT,
    maSinhVien TEXT,
    diemSo FLOAT,
    CONSTRAINT thamgiahoc_pkey PRIMARY KEY (maLopHocPhan, maSinhVien),
    CONSTRAINT FK_thamgiahoc_lophocphan FOREIGN KEY (maLopHocPhan)
        REFERENCES public.lophocphan (maLopHocPhan) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT FK_thamgiahoc_sinhvien FOREIGN KEY (maSinhVien)
        REFERENCES public.sinhvien (maSinhVien) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
--data
INSERT INTO chuongtrinhdaotao (idchuongTrinhDaoTao, tenChuongTrinhDaoTao, soTinChi, namBatDauDaoTao)
VALUES ('CNTT', 'Công nghệ Thông tin', 120, 2022);
INSERT INTO chuongtrinhdaotao (idchuongTrinhDaoTao, tenChuongTrinhDaoTao, soTinChi, namBatDauDaoTao)
VALUES ('KHMT', 'Khoa học Máy tính', 150, 2020);
INSERT INTO chuongtrinhdaotao (idchuongTrinhDaoTao, tenChuongTrinhDaoTao, soTinChi, namBatDauDaoTao)
VALUES ('KT', 'Kế toán', 130, 2022);
INSERT INTO chuongtrinhdaotao (idchuongTrinhDaoTao, tenChuongTrinhDaoTao, soTinChi, namBatDauDaoTao)
VALUES ('QTKD', 'Quản trị kinh doanh', 120, 2021);
INSERT INTO chuongtrinhdaotao (idchuongTrinhDaoTao, tenChuongTrinhDaoTao, soTinChi, namBatDauDaoTao)
VALUES ('TC', 'Tài chính', 130, 2020);
--
INSERT INTO monhoc (maMonHoc, tenmonhoc, sotinchi, theloai, idchuongtrinhdaotao) 
VALUES ('CSKH', 'Chăm sóc khách hàng', 5, 'Tự chọn', 'QTKD');
INSERT INTO monhoc (maMonHoc, tenmonhoc, sotinchi, theloai, idchuongtrinhdaotao)
VALUES ('TRR', 'Toán Rời Rạc', 3, 'Bắt buộc', 'CNTT');
--
INSERT INTO lophocphan (malophocphan, maMonHoc, namHoc, hocKy, gioiHanSoLuongSinhVien) 
VALUES (4, 'TRR', 2022, 'Học kỳ 1', 50);
INSERT INTO lophocphan (malophocphan, maMonHoc, namHoc, hocKy, gioiHanSoLuongSinhVien)
VALUES (5, 'CSKH', 2022, 'Học kỳ 1', 100);
--
INSERT INTO sinhvien (maSinhVien, hoDem, ten, ngaySinh, namNhapHoc, idChuongTrinhDaoTao, gioiTinh)
VALUES ('01', 'Lê Nhật', 'Thảo', '2000-01-05', 2022, 'CNTT', TRUE);
INSERT INTO sinhvien (maSinhVien, hoDem, ten, ngaySinh, namNhapHoc, idChuongTrinhDaoTao, gioiTinh)
VALUES ('02', 'Trần Văn', 'An', '2000-07-05', 2022, 'KHMT', TRUE);
INSERT INTO sinhvien (maSinhVien, hoDem, ten, ngaySinh, namNhapHoc, idChuongTrinhDaoTao, gioiTinh)
VALUES ('03', 'Lê Thị', 'Thủy', '1999-01-05', 2020, 'QTKD', FALSE);
INSERT INTO sinhvien (maSinhVien, hoDem, ten, ngaySinh, namNhapHoc, idChuongTrinhDaoTao, gioiTinh)
VALUES ('04', 'Hoàng Mỹ', 'Nương', '1999-01-06', 2020, 'QTKD', FALSE);
INSERT INTO sinhvien (maSinhVien, hoDem, ten, ngaySinh, namNhapHoc, idChuongTrinhDaoTao, gioiTinh)
VALUES ('05', 'Nguyên Hoàng', 'Yến', '1999-12-16', 2022, 'CNTT', FALSE);
--
INSERT INTO thamgiahoc(maLopHocPhan, maSinhVien, diemSo)
VALUES (4, '01', NULL);
INSERT INTO thamgiahoc(maLopHocPhan, maSinhVien, diemSo)
VALUES (4, '02', NULL);
INSERT INTO thamgiahoc(maLopHocPhan, maSinhVien, diemSo)
VALUES (5, '03', NULL);
INSERT INTO thamgiahoc(maLopHocPhan, maSinhVien, diemSo)
VALUES (5, '04', NULL);
INSERT INTO thamgiahoc(maLopHocPhan, maSinhVien, diemSo)
VALUES (5, '05', NULL);
