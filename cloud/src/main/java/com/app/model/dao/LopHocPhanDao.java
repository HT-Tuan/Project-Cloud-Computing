package com.app.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.Util.HibernateUtils;
import com.app.model.entity.lophocphan;

public class LopHocPhanDao {
	public lophocphan insert (lophocphan LopHocPhan) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtils.getFACTORY().openSession();
			transaction = session.beginTransaction();
			session.save(LopHocPhan);
			transaction.commit();
			
			return LopHocPhan;
		}
		catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	public lophocphan select (int id) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtils.getFACTORY().openSession();
			transaction = session.beginTransaction();
			String hql = "FROM lophocphan WHERE malophocphan = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			lophocphan LopHocPhan = (lophocphan) query.getSingleResult();
			transaction.commit();
			
			return LopHocPhan;
		}
		catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	public List<lophocphan> findAll () {
		List<lophocphan> list = new ArrayList<lophocphan>();
		Transaction transaction = null;
		try {
			Session session = HibernateUtils.getFACTORY().openSession();
			transaction = session.beginTransaction();
			
			String hql = "FROM lophocphan";
			Query query = session.createQuery(hql);
			list = query.getResultList();
			
			transaction.commit();
			
			return list;
		}
		catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	public lophocphan update (lophocphan LopHocPhan) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtils.getFACTORY().openSession();
			transaction = session.beginTransaction();
			
			session.update(LopHocPhan);
			
			transaction.commit();
			
			return LopHocPhan;
		}
		catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	public lophocphan delete (int id) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtils.getFACTORY().openSession();
			transaction = session.beginTransaction();
			lophocphan LopHocPhan = select(id);
			if (LopHocPhan == null) {
				return null;
			}
			String hql = "DELETE FROM thamgiahoc WHERE lophoc.maLopHocPhan = :key";
			Query query = session.createQuery(hql);
			query.setParameter("key", id);
			query.executeUpdate();
			
			String hql1 = "DELETE FROM lophocphan WHERE maLopHocPhan = :key";
			
			Query query2 = session.createQuery(hql1);
			query2.setParameter("key", id);
			query2.executeUpdate();
			transaction.commit();
			
			return LopHocPhan;
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
