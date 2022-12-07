package com.app.Util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {

    private static final SessionFactory FACTORY = createFACTORY();

    private static SessionFactory createFACTORY() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            System.out.println("Khoi tao thanh cong SessionFactory");
            return configuration.configure().buildSessionFactory();
        } catch (HibernateException ex) {
            System.out.println("Khong The Khoi Tao SessionFactory" + ex);
            throw new ExceptionInInitializerError(ex);               
        }
    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void closeSessionFactory()
    {
         if (FACTORY != null) {
            FACTORY.close();
        }
    }
}
