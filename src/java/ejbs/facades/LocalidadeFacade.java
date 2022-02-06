/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.Localidade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ed
 */
@Stateless
public class LocalidadeFacade extends AbstractFacade<Localidade> {

    @PersistenceContext(unitName = "UcanPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LocalidadeFacade() {
        super(Localidade.class);
    }

    @Override
    public String toString() {
        return "LocalidadeFacade{" + "em=" + em + '}';
    }
    
    public String toString(Localidade localidade) {
        return localidade.toString();
    }
}
