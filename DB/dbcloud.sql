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
	trangThai BOOLEAN,
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
VALUES ('CNTT', 'C??ng ngh??? Th??ng tin', 120, 2022);
INSERT INTO chuongtrinhdaotao (idchuongTrinhDaoTao, tenChuongTrinhDaoTao, soTinChi, namBatDauDaoTao)
VALUES ('KHMT', 'Khoa h???c M??y t??nh', 150, 2020);
INSERT INTO chuongtrinhdaotao (idchuongTrinhDaoTao, tenChuongTrinhDaoTao, soTinChi, namBatDauDaoTao)
VALUES ('KT', 'K??? to??n', 130, 2022);
INSERT INTO chuongtrinhdaotao (idchuongTrinhDaoTao, tenChuongTrinhDaoTao, soTinChi, namBatDauDaoTao)
VALUES ('QTKD', 'Qu???n tr??? kinh doanh', 120, 2021);
INSERT INTO chuongtrinhdaotao (idchuongTrinhDaoTao, tenChuongTrinhDaoTao, soTinChi, namBatDauDaoTao)
VALUES ('TC', 'T??i ch??nh', 130, 2020);
--
INSERT INTO monhoc (maMonHoc, tenmonhoc, sotinchi, theloai, idchuongtrinhdaotao) 
VALUES ('CSKH', 'Ch??m s??c kh??ch h??ng', 5, 'T??? ch???n', 'QTKD');
INSERT INTO monhoc (maMonHoc, tenmonhoc, sotinchi, theloai, idchuongtrinhdaotao)
VALUES ('TRR', 'To??n R???i R???c', 3, 'B???t bu???c', 'CNTT');
--
INSERT INTO lophocphan (malophocphan, maMonHoc, namHoc, hocKy, gioiHanSoLuongSinhVien) 
VALUES (4, 'TRR', 2022, 'H???c k??? 1', 50);
INSERT INTO lophocphan (malophocphan, maMonHoc, namHoc, hocKy, gioiHanSoLuongSinhVien)
VALUES (5, 'CSKH', 2022, 'H???c k??? 1', 100);
--
INSERT INTO sinhvien (maSinhVien, hoDem, ten, ngaySinh, namHoc, namNhapHoc, idChuongTrinhDaoTao, gioiTinh, trangThai)
VALUES ('01', 'L?? Nh???t', 'Th???o', '2000-01-05',1 , 2022, 'CNTT', TRUE, TRUE);
INSERT INTO sinhvien (maSinhVien, hoDem, ten, ngaySinh, namHoc, namNhapHoc, idChuongTrinhDaoTao, gioiTinh, trangThai)
VALUES ('02', 'Tr???n V??n', 'An', '2000-07-05',1 , 2022, 'KHMT', TRUE, TRUE);
INSERT INTO sinhvien (maSinhVien, hoDem, ten, ngaySinh, namHoc, namNhapHoc, idChuongTrinhDaoTao, gioiTinh, trangThai)
VALUES ('03', 'L?? Th???', 'Th???y', '1999-01-05', 3, 2020, 'QTKD', FALSE, TRUE);
INSERT INTO sinhvien (maSinhVien, hoDem, ten, ngaySinh, namHoc, namNhapHoc, idChuongTrinhDaoTao, gioiTinh, trangThai)
VALUES ('04', 'Ho??ng M???', 'N????ng', '1999-01-06',3 ,2020, 'QTKD', FALSE, TRUE);
INSERT INTO sinhvien (maSinhVien, hoDem, ten, ngaySinh, namHoc, namNhapHoc, idChuongTrinhDaoTao, gioiTinh, trangThai)
VALUES ('05', 'Nguy??n Ho??ng', 'Y???n', '1999-12-16',1 ,2022, 'CNTT', FALSE, TRUE);
--
INSERT INTO thamgiahoc(maLopHocPhan, maSinhVien, diemSo)
VALUES (4, '01', 9);
INSERT INTO thamgiahoc(maLopHocPhan, maSinhVien, diemSo)
VALUES (4, '02', 9);
INSERT INTO thamgiahoc(maLopHocPhan, maSinhVien, diemSo)
VALUES (5, '03', 9);
INSERT INTO thamgiahoc(maLopHocPhan, maSinhVien, diemSo)
VALUES (5, '04', 9);
INSERT INTO thamgiahoc(maLopHocPhan, maSinhVien, diemSo)
VALUES (5, '05', 9);
