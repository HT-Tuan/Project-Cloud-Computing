package com.app.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.Util.HibernateUtils;
import com.app.model.entity.thamgiahoc;

public class ThamGiaHocDao {
	public thamgiahoc select (int maLopHocPhan, String maSinhVien) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtils.getFACTORY().openSession();
			transaction = session.beginTransaction();
			
			String hql = "FROM thamgiahoc WHERE lophoc.maLopHocPhan = :malop AND Sinhvien.maSinhVien = :maSV";
			Query query = session.createQuery(hql);
			query.setParameter("malop", maLopHocPhan);
			query.setParameter("maSV", maSinhVien);
			thamgiahoc ThamGia = (thamgiahoc) query.getSingleResult();
			
			transaction.commit();
			
			return ThamGia;
		}
		catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	public List<thamgiahoc> selectByMaLop (int maLopHocPhan) {
		List<thamgiahoc> ThamGia = new ArrayList<thamgiahoc>();
		Transaction transaction = null;
		try {
			Session session = HibernateUtils.getFACTORY().openSession();
			transaction = session.beginTransaction();
			
			String hql = "FROM thamgiahoc WHERE lophoc.maLopHocPhan = :malop";
			Query query = session.createQuery(hql);
			query.setParameter("malop", maLopHocPhan);
			ThamGia = query.getResultList();
			
			transaction.commit();
			
			return ThamGia;
		}
		catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	public List<thamgiahoc> selectByMaSV (String maSV) {
		List<thamgiahoc> ThamGia = new ArrayList<thamgiahoc>();
		Transaction transaction = null;
		try {
			Session session = HibernateUtils.getFACTORY().openSession();
			transaction = session.beginTransaction();
			
			String hql = "FROM thamgiahoc WHERE Sinhvien.maSinhVien = :maSV";
			Query query = session.createQuery(hql);
			query.setParameter("maSV", maSV);
			ThamGia = query.getResultList();
			
			transaction.commit();
			
			return ThamGia;
		}
		catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	public List<thamgiahoc> selectAll () {
		List<thamgiahoc> ThamGia = new ArrayList<thamgiahoc>();
		Transaction transaction = null;
		try {
			Session session = HibernateUtils.getFACTORY().openSession();
			transaction = session.beginTransaction();
			
			String hql = "FROM thamgiahoc";
			Query query = session.createQuery(hql);
			ThamGia = query.getResultList();
			
			transaction.commit();
			
			return ThamGia;
		}
		catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	public thamgiahoc insert (thamgiahoc ThamGia) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtils.getFACTORY().openSession();
			transaction = session.beginTransaction();
			
			session.save(ThamGia);
			
			transaction.commit();
			
			return ThamGia;
		}
		catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	public thamgiahoc update (thamgiahoc ThamGia) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtils.getFACTORY().openSession();
			transaction = session.beginTransaction();
			
			session.update(ThamGia);
			
			transaction.commit();
			
			return ThamGia;
		}
		catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	public thamgiahoc delete (int maLopHocPhan, String maSinhVien) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtils.getFACTORY().openSession();
			transaction = session.beginTransaction();
			
			thamgiahoc ThamGia = select(maLopHocPhan, maSinhVien);
			if (ThamGia == null) {
				return null;
			}
			session.delete(ThamGia);
			
			transaction.commit();
			
			return ThamGia;
		}
		catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
}
