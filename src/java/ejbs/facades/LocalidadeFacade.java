/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.Localidade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author benvxavier
 */
@Stateless
public class LocalidadeFacade extends AbstractFacade<Localidade>
{

    @PersistenceContext(unitName = "BilheteIdentidadePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public LocalidadeFacade()
    {
        super(Localidade.class);
    }

    public List<Localidade> findAllOrderedByNome(Integer pkPai)
    {
        Query q = em.createQuery("SELECT l FROM Localidade l WHERE l.fkLocalidadePai = :pkPai ORDER BY l.nome");
        q.setParameter("pkPai", pkPai);
        return q.getResultList();
    }

    public List<Localidade> findAllOrderedByNome()
    {
        Query q = em.createQuery("SELECT l FROM Localidade l WHERE l.fkLocalidadePai IS NULL ORDER BY l.nome");
        return q.getResultList();
    }
    
    public String toString(Localidade l)
    {
        return l.getNome();
    }
}
