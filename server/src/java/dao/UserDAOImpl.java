/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import models.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Vladimir Aca
 */
public class UserDAOImpl extends CrudDAOImpl {
    
    @Override
    public User signIn(String email, String password, Session hibernateSession) {
        this.criterios = hibernateSession.createCriteria(User.class);
        this.criterios.add(Restrictions.eq("email", email));
        this.criterios.setMaxResults(1);
        User foundUser = (User) this.criterios.uniqueResult();
        if (foundUser != null && password.equals(foundUser.getPassword())) {
            return foundUser;
        }
        return null;
    }

    @Override
    public String signUp(User newUser, Session hibernateSession) {
        if (this.validateEmail(newUser.getEmail(), hibernateSession)) { return "EMAIL_EXISTS"; }
        return this.addObject(newUser, hibernateSession);
    }
    
    private boolean validateEmail(String email, Session hibernateSession) {
        this.criterios = hibernateSession.createCriteria(User.class);
        this.criterios.add(Restrictions.eq("email", email));
        User foundUser = (User) this.criterios.uniqueResult();
        if (foundUser != null) {
            return true;
        }
        return false;
    }
}
