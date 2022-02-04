/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.Conta;
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
public class ContaFacade extends AbstractFacade<Conta> {

    @PersistenceContext(unitName = "UcanPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContaFacade() {
        super(Conta.class);
    }
    
    public Conta isValidUser(String username, String password) {
        Query q = this.em.createQuery("SELECT fk_tipo_conta FROM BilheteIdentidade bi "
            + "WHERE username = :username "
            + "AND password LIKE :password");
        q.setParameter("nomeFiltro", username);
        q.setParameter("numeroIdentificacaoFiltro", password);
        
        List<Conta> listaDeContas = q.getResultList();
        
        if (listaDeContas.size() != 0) {
            return listaDeContas.get(0);
        }
        
        return null;
    }
}
