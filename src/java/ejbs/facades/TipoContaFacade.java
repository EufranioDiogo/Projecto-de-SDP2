/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.TipoConta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ed
 */
@Stateless
public class TipoContaFacade extends AbstractFacade<TipoConta> {

    @PersistenceContext(unitName = "UcanPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoContaFacade() {
        super(TipoConta.class);
    }
    
}
