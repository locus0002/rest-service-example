/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import models.User;
import org.hibernate.Session;

/**
 *
 * @author Vladimir Aca
 */
public interface CrudDAO {
    
    String addObject(Object newObject, Session hibernateSession);
    String addObjectNoTran(Object newObject, Session hibernateSession);
    void beginTransaction(Session hibernateSession);
    void commitTransaction();
    User signIn(String email, String password, Session hibernateSession);
    String signUp(User newUser, Session hibernateSession);
    <T> String massiveCreation(List<T> newObjects, Session hibernateSession);
    String deleteObject(int id, Session hibernateSession, Class typeClass);
    String editObject(Object object, Session hibernateSession);
    <T> T getAnObject(int id, Class typeClass, Session hibernateSession);
    <T> T getAnObjectNoTran(int id, Class typeClass, Session hibernateSession);
    List getAllObjects(Class typeClass, Session hibernateSession);
    List getAllObjectsByMatch(Class typeClass, String match, String column, Session hibernateSession);
    List getAllActiveObjects(Class typeClass, Session hibernateSession);
    boolean wasCommitted();
}
