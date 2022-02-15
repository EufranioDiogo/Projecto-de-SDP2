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
import javax.persistence.Query;

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

    public List<Portfolio> findAllVeiculosTypes() {
        return em.createQuery("SELECT p FROM Portfolio p WHERE p.pkPortfolio LIKE '_'").getResultList();
    }
    public List<Portfolio> findAllVeiculosModelos() {
        return em.createQuery("SELECT p FROM Portfolio p WHERE p.pkPortfolio LIKE '_._'").getResultList();
    }
    public List<Portfolio> findPortfolioProductFromParent(String parentPK) {
        Query query = em.createQuery("SELECT p FROM Portfolio p WHERE p.fkPortfolio = :fk_portfolio");
        query.setParameter("fk_portfolio", parentPK);
        
        return query.getResultList();
    }
    
    public List<Portfolio> findPortfolioProductIsInStock(String parentPK) {
        Query query = em.createQuery("SELECT p FROM Portfolio p, Stock s WHERE p.pkPortfolio = s.fkPortfolio.pkPortfolio AND p.pkPortfolio = :fk_portfolio");
        query.setParameter("fk_portfolio", parentPK);
        
        return query.getResultList();
    }
}
