/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import models.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Vladimir Aca
 */
public class CrudDAOImpl implements CrudDAO{
    
    public Transaction transaccion;
    public Criteria criterios;

    @Override
    public String addObject(Object newObject, Session hibernateSession) {
        try {
            this.transaccion = hibernateSession.beginTransaction();
            hibernateSession.save(newObject);
            this.transaccion.commit();
            return "";
        } catch (Exception e) {
            this.transaccion.rollback();
            return "Error: " + e.getMessage() + " Cause: " + e.getCause().getMessage();
        }
    }
    
     @Override
    public String addObjectNoTran(Object newObject, Session hibernateSession) {
        try {
            hibernateSession.save(newObject);
            return "";
        } catch (Exception e) {
            return "Error: " + e.getMessage() + " Cause: " + e.getCause().getMessage();
        }
    }
    
    @Override
    public void beginTransaction(Session hibernateSession) {
        this.transaccion = hibernateSession.beginTransaction();
    }

    @Override
    public void commitTransaction() {
        this.transaccion.commit();
    }

    @Override
    public String deleteObject(int id, Session hibernateSession, Class typeClass) {
        try {
            this.transaccion = hibernateSession.beginTransaction();
            Object obj = hibernateSession.get(typeClass, id);
            if(obj != null){
                hibernateSession.delete(obj);
            }
            this.transaccion.commit();
            return "";
        } catch (Exception e) {
            this.transaccion.rollback();
            return "Error: " + e.getMessage() + " Cause: " + e.getCause().getMessage();
        }
    }

    @Override
    public String editObject(Object object, Session hibernateSession) {
        try {
            this.transaccion = hibernateSession.beginTransaction();
            hibernateSession.update(object);
            this.transaccion.commit();
            return "";
        } catch (Exception e) {
            this.transaccion.rollback();
            return "Error: " + e.getMessage() + " Cause: " + e.getCause().getMessage();
        }
    }

    @Override
    public <T> T getAnObject(int id, Class typeClass, Session hibernateSession) {
        this.transaccion = hibernateSession.beginTransaction();
        T obj = (T) hibernateSession.get(typeClass, id);
        this.transaccion.commit();
        return obj;
    }
    
    @Override
    public <T> T getAnObjectNoTran(int id, Class typeClass, Session hibernateSession) {
        T obj = (T) hibernateSession.get(typeClass, id);
        return obj;
    }

    @Override
    public List getAllObjects(Class typeClass, Session hibernateSession) {
        
        this.criterios = hibernateSession.createCriteria(typeClass);
        List<Object> listObjects = this.criterios.list();
        return listObjects;
    }

    @Override
    public List getAllObjectsByMatch(Class typeClass, String match, String column, Session hibernateSession) {
        
        this.criterios = hibernateSession.createCriteria(typeClass);
        if(match != null){
            if(!match.isEmpty()){
                this.criterios.add( Restrictions.ilike(column, match, MatchMode.ANYWHERE ) );
            }
        }
        this.criterios.setMaxResults(5);
        List<Object> listObjects = this.criterios.list();
        return listObjects;
    }

    @Override
    public List getAllActiveObjects(Class typeClass, Session hibernateSession) {
        
        this.criterios = hibernateSession.createCriteria(typeClass);
        this.criterios.add( Restrictions.eq("status", true) );
        List<Object> listObjects = this.criterios.list();
        return listObjects;
    }

    @Override
    public <T> String massiveCreation(List<T> newObjects, Session hibernateSession) {
        try {
            this.transaccion = hibernateSession.beginTransaction();
            newObjects.forEach((currentObject) -> {
                hibernateSession.save(currentObject);
            });
            this.transaccion.commit();
            return "";
        } catch (Exception e) {
            this.transaccion.rollback();
            return "Error: " + e.getMessage() + " Cause: " + e.getCause().getMessage();
        }
    }

    @Override
    public User signIn(String email, String password, Session hibernateSession) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String signUp(User newUser, Session hibernateSession) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean wasCommitted() {
        if (this.transaccion != null) {
            return this.transaccion.wasCommitted();
        }
        return false;
    }
    
}
