/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.Veiculo;
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
public class VeiculoFacade extends AbstractFacade<Veiculo> {

    @PersistenceContext(unitName = "UcanPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VeiculoFacade() {
        super(Veiculo.class);
    }
    
    public List<Veiculo> findVeiculoBasedOnPKStock(int parentPK) {
        Query query = em.createQuery("SELECT p FROM Veiculo p WHERE p.fkStock.pkStock = :fk_stock");
        query.setParameter("fk_stock", parentPK);
        
        return query.getResultList();
    }
}
