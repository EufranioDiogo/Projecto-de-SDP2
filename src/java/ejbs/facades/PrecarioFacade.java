/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.Portfolio;
import ejbs.entities.Precario;
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
public class PrecarioFacade extends AbstractFacade<Precario> {

    @PersistenceContext(unitName = "UcanPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrecarioFacade() {
        super(Precario.class);
    }
    
    public List<Precario> findPrecarioByStockPK(int veiculoId) {
        Query query = em.createQuery("SELECT p FROM Precario p WHERE p.fkProducto.pkVeiculo = :fk_veiculo ORDER BY p.dataCompra DESC");
        query.setParameter("fk_veiculo", veiculoId);
        
        return query.getResultList();
    }
}
