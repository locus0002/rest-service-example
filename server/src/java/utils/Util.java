/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author VLAC
 */
public class Util {
    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;
    private static final StandardServiceRegistryBuilder standardServiceRegistry;
    
    static {
        try {
            
            Configuration conf = new Configuration().configure("hibernate.cfg.xml");
            
            standardServiceRegistry = new StandardServiceRegistryBuilder();
            standardServiceRegistry.applySettings(conf.getProperties());
            
            serviceRegistry = standardServiceRegistry.build();
            sessionFactory = conf.buildSessionFactory(serviceRegistry);
            
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
}
