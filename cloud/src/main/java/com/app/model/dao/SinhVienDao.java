package com.app.model.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.app.Response.SinhvienResponse;
import com.app.Util.HibernateUtils;
import com.app.model.entity.sinhvien;

public class SinhVienDao extends AbstractDao<sinhvien> implements IAbstracDao<sinhvien> {
    private SessionFactory sessionFactory = HibernateUtils.getFACTORY();

    @Override
    public boolean insert(sinhvien entity) {
        // TODO Auto-generated method stub
        return super.insert(entity);
    }
    @Override
    public boolean update(sinhvien entity) {
        // TODO Auto-generated method stub
        return super.update(entity);
    }
    @Override
    public boolean delete(sinhvien entity) {
        // TODO Auto-generated method stub
        return super.delete(entity);
    }


    public List<SinhvienResponse> getAll() {
        Session session = sessionFactory.openSession();
        try {
            String hql = "FROM sinhvien WHERE trangThai = TRUE";
            Query<sinhvien> query = session.createQuery(hql);
            List<sinhvien> kq = query.list();
            List<SinhvienResponse> sv = new ArrayList<>();
            for(sinhvien item : kq)
            {
                SinhvienResponse temp = new SinhvienResponse();
                temp.setGioitinh(item.getGioiTinh());
                temp.setHodem(item.getHoDem());
                temp.setMasinhvien(item.getMaSinhVien());
                temp.setNamhoc(item.getNamHoc());
                temp.setNamnhaphoc(item.getNamNhapHoc());
                temp.setNgaysinh(item.getNgaySinh());
                temp.setTen(item.getTen());
                temp.setTenChuongTrinhDaoTao(item.getChuongtrinh().getTenChuongTrinhDaoTao());
                sv.add(temp);
            }
            return sv;
        } catch (Exception e) {
            System.out.println("loi" + e.toString());
        }
        return null;
    }

    public sinhvien getID(String ID)
    {
        Session session = sessionFactory.openSession();
        try {
            String hql = "FROM sinhvien WHERE maSinhVien = :id";
            Query<sinhvien> query = session.createQuery(hql);
            query.setParameter("id", ID);
            return query.list().get(0);
        } catch (Exception e) {
            System.out.println("loi" + e.toString());
        } 
        return null;
    }
}
