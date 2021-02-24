/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import models.RoutePath;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Vladimir Aca
 */
public class PathDAOImpl extends CrudDAOImpl{
    public String massivePositions(List<RoutePath> newPositions, String type, Session session) {
        try {
            this.transaccion = session.beginTransaction();
            newPositions.forEach((currentObject) -> {
                currentObject.setType(type);
                session.save(currentObject);
            });
            this.transaccion.commit();
            return "";
        } catch (Exception e) {
            this.transaccion.rollback();
            return "Error: " + e.getMessage() + " Cause: " + e.getCause().getMessage();
        }
    }
    
    public List<RoutePath> getPathById(Long id, boolean byExternal, Session hibernateSession) {
        this.transaccion = hibernateSession.beginTransaction();
        this.criterios = hibernateSession.createCriteria(RoutePath.class);
        this.criterios.add( Restrictions.eq((byExternal ? "externalId" : "destinyId"), id));
        List<RoutePath> paths = this.criterios.list();
        this.transaccion.commit();
        return paths;
    }
}
