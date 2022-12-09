package com.app.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.app.Response.ChuongTrinhReponse;
import com.app.Util.HibernateUtils;
import com.app.model.entity.chuongtrinhdaotao;
import com.app.model.entity.monhoc;
import com.app.model.entity.sinhvien;

public class ChuongTrinhDTDao extends AbstractDao<chuongtrinhdaotao> implements IAbstracDao<chuongtrinhdaotao> {
    private SessionFactory sessionFactory = HibernateUtils.getFACTORY();

    @Override
    public boolean insert(chuongtrinhdaotao entity) {
        // TODO Auto-generated method stub
        return super.insert(entity);
    }

    @Override
    public boolean update(chuongtrinhdaotao entity) {
        // TODO Auto-generated method stub
        return super.update(entity);
    }

    @Override
    public boolean delete(chuongtrinhdaotao entity) {
        // TODO Auto-generated method stub
        return super.delete(entity);
    }

    public List<ChuongTrinhReponse> getAll() {
        Session session = sessionFactory.openSession();
        try {
            String hql = "FROM chuongtrinhdaotao";
            Query<chuongtrinhdaotao> query = session.createQuery(hql);
            List<chuongtrinhdaotao> kq = query.list();
            List<ChuongTrinhReponse> ct = new ArrayList<>();
            for (chuongtrinhdaotao item : kq) {
                ChuongTrinhReponse temp = new ChuongTrinhReponse();
                temp.setIdChuongTrinhDaoTao(item.getIdChuongTrinhDaoTao());
                temp.setNamBatDauDaoTao(item.getNamBatDauDaoTao());
                temp.setSoTinChi(item.getSoTinChi());
                temp.setTenChuongTrinhDaoTao(item.getTenChuongTrinhDaoTao());
                ct.add(temp);
            }
            return ct;
        } catch (Exception e) {
            System.out.println("loi" + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    public chuongtrinhdaotao getName(String name) {
        Session session = sessionFactory.openSession();
        try {
            String hql = "FROM chuongtrinhdaotao WHERE tenChuongTrinhDaoTao = :ten";
            Query<chuongtrinhdaotao> query = session.createQuery(hql);
            query.setParameter("ten", name);
            return query.list().get(0);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("loi" + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    public chuongtrinhdaotao getFindId(String id) {
        Session session = sessionFactory.openSession();
        try {
            String hql = "FROM chuongtrinhdaotao WHERE idChuongTrinhDaoTao = :key";
            Query<chuongtrinhdaotao> query = session.createQuery(hql);
            query.setParameter("key", id);
            return query.list().get(0);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("loi" + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    public boolean deleteFK(String id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "UPDATE sinhvien SET chuongtrinh = null WHERE chuongtrinh.idChuongTrinhDaoTao = :key";
            Query<sinhvien> query = session.createQuery(hql);
            query.setParameter("key", id);
            query.executeUpdate();

            hql = "UPDATE monhoc SET chuongtrinh = null WHERE chuongtrinh.idChuongTrinhDaoTao = :key";
            Query<monhoc> query2 = session.createQuery(hql);
            query2.setParameter("key", id);
            query2.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("loi" + e.toString());
        } finally {
            session.close();
        }
        return false;
    }
}
