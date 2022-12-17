package com.app.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.Util.HibernateUtils;
import com.app.model.entity.chuongtrinhdaotao;
import com.app.model.entity.monhoc;

public class MonHocDao {
	public monhoc insert (monhoc Monhoc) {		
		Transaction transaction = null;
		try {
			Session session = HibernateUtils.getFACTORY().openSession();
			transaction = session.beginTransaction();
			session.save(Monhoc);
			transaction.commit();
			return Monhoc;
		}
		catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}		
			e.printStackTrace();	
			return null;
		}
	}
	
	public monhoc select (String maMonHoc) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtils.getFACTORY().openSession();
			transaction = session.beginTransaction();
			
			String Hql = "FROM monhoc WHERE maMonHoc = :maMonHoc";
			Query query = session.createQuery(Hql);
			query.setParameter("maMonHoc", maMonHoc);
			monhoc MonHoc = (monhoc) query.getSingleResult();
			transaction.commit();
			
			return MonHoc;
		}
		catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}			
			e.printStackTrace();	
			return null;
		}		
	}
	
	public monhoc update (monhoc MonHoc) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtils.getFACTORY().openSession();
			transaction = session.beginTransaction();
			session.update(MonHoc);
			transaction.commit();
			
			return MonHoc;
		}
		catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}		
			e.printStackTrace();	
			return null;
		}
	}
	
	public List<monhoc> getAll () {
		List<monhoc> monHocList = new ArrayList<monhoc>();
		Transaction transaction = null;
		try {
			Session session = HibernateUtils.getFACTORY().openSession();
			transaction = session.beginTransaction();
			String hql = "SELECT M FROM monhoc M";
			Query query = session.createQuery(hql);
			monHocList = query.getResultList();
			transaction.commit();
			
			return monHocList;
		}
		catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();			
			}		
			e.printStackTrace();	
			return null;
		}
	}
	
	public monhoc delete (String maMonHoc) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtils.getFACTORY().openSession();
			transaction = session.beginTransaction();
			monhoc MonHoc = select(maMonHoc);
			if (MonHoc == null) {
				return null;
			}
			
			String hql = "UPDATE lophocphan SET monHoc = null WHERE monHoc.maMonHoc = :key";
			Query query = session.createQuery(hql);
			query.setParameter("key", maMonHoc);
			query.executeUpdate();

			String hql1 = "DELETE FROM monhoc WHERE maMonHoc = :key";
			Query query2 = session.createQuery(hql1);
			query2.setParameter("key", maMonHoc);
			query2.executeUpdate();

			transaction.commit();

			return MonHoc;
		}
		catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();							
			}	
			e.printStackTrace();	
			return null;
		}
	}
	
	public chuongtrinhdaotao selectChuongTrinhDaoTao (String id) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtils.getFACTORY().openSession();
			transaction = session.beginTransaction();
			
			String hql = "FROM chuongtrinhdaotao WHERE idChuongTrinhDaoTao = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			chuongtrinhdaotao ChuongTrinh = (chuongtrinhdaotao) query.getSingleResult();
			transaction.commit();
			
			return ChuongTrinh;
		}
		catch (Exception e) {
			if (transaction != null) {
				transaction.commit();
			}
			e.printStackTrace();
			return null;
		}
	}
}
