/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.Stock;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ed
 */
@Stateless
public class StockFacade extends AbstractFacade<Stock> {

    @PersistenceContext(unitName = "UcanPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StockFacade() {
        super(Stock.class);
    }
    
    public List<Stock> findAllRuptura() {
        return em.createQuery("SELECT s FROM Stock s WHERE s.quantVeiculoActual = 0").getResultList();
    }
    
    public List<Stock> findAllQuaseRuptura() {
        return em.createQuery("SELECT s FROM Stock s WHERE s.quantVeiculoActual <= ((s.quantProductoMaxima * 25) / 100)").getResultList();
    }
    
    public List<Stock> findAllPreRuptura() {
        return em.createQuery("SELECT s FROM Stock s WHERE s.quantVeiculoActual <= ((s.quantProductoMaxima * 50) / 100)").getResultList();
    }
}
