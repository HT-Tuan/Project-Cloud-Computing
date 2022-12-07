package com.app.Controller;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.app.Util.HibernateUtils;
import com.app.model.entity.sinhvien;


public class SinhVienController {
    
    private SessionFactory sessionFactory = HibernateUtils.getFACTORY();

    public void getAll()
    {
        Session session = sessionFactory.openSession();
        try {
            String hql = "FROM sinhvien";
            Query<sinhvien> query = session.createQuery(hql);
            List<sinhvien> x1 = query.list();
            for(sinhvien item : x1)
            System.out.print(item.getMasinhvien());
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("loi" + e.toString());
        }
    }
}
