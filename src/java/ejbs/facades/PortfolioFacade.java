/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.Portfolio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ed
 */
@Stateless
public class PortfolioFacade extends AbstractFacade<Portfolio> {

    @PersistenceContext(unitName = "UcanPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PortfolioFacade() {
        super(Portfolio.class);
    }

    public List<Portfolio> findAllVeiculos() {
        return em.createQuery("SELECT p FROM Portfolio p WHERE p.pkPortfolio LIKE '_._._'").getResultList();
    }

}
