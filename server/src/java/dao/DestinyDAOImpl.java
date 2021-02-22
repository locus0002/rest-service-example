/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import models.Destiny;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Vladimir Aca
 */
public class DestinyDAOImpl extends CrudDAOImpl {
    public Destiny getDestiny(Integer userId, Integer externalId, Session session) {
        this.criterios = session.createCriteria(Destiny.class);
        this.criterios.add(Restrictions.eq("user.id", userId));
        this.criterios.add(Restrictions.eq("externalId", externalId));
        return (Destiny) this.criterios.uniqueResult();
    }
}
