/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.Montra;
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
public class MontraFacade extends AbstractFacade<Montra> {

    @PersistenceContext(unitName = "UcanPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MontraFacade() {
        super(Montra.class);
    }
    
    public List<Veiculo> findAllProductsNotInMontra() {
        Query query = em.createQuery("SELECT v FROM Veiculo v WHERE v.pkVeiculo NOT IN (SELECT m.fkProducto.pkVeiculo FROM Montra m)");
        
        return query.getResultList();
    }
    
}
