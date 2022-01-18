/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.BilheteIdentidade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utils.StringUtils;

/**
 *
 * @author benvxavier
 */
@Stateless
public class BilheteIdentidadeFacade extends AbstractFacade<BilheteIdentidade>
{

    @PersistenceContext(unitName = "BilheteIdentidadePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager ()
    {
        return em;
    }

    public BilheteIdentidadeFacade ()
    {
        super(BilheteIdentidade.class);
    }

    public void limpar (BilheteIdentidade bi)
    {
        bi.setNome("");
        bi.setNumeroIdentificacao("");
    }

    public List<BilheteIdentidade> findAllByNomeAndByNumeroIdentificacao (
        String nomeFiltro, String numeroIdentificacaoFiltro)
    {
        nomeFiltro = "%" + nomeFiltro + "%";
        numeroIdentificacaoFiltro = "%" + numeroIdentificacaoFiltro + "%";
        Query q = this.em.createQuery("SELECT bi FROM BilheteIdentidade bi "
            + "WHERE bi.nome LIKE :nomeFiltro "
            + "AND bi.numeroIdentificacao LIKE :numeroIdentificacaoFiltro");
        q.setParameter("nomeFiltro", nomeFiltro);
        q.setParameter("numeroIdentificacaoFiltro", numeroIdentificacaoFiltro);
        return q.getResultList();
    }

    public List<BilheteIdentidade> findAllByNome (String nomeFiltro)
    {
        nomeFiltro = "%" + nomeFiltro + "%";
        Query q = this.em.createQuery("SELECT bi FROM BilheteIdentidade bi "
            + "WHERE bi.nome LIKE :nomeFiltro");
        q.setParameter("nomeFiltro", nomeFiltro);
        return q.getResultList();
    }

    public List<BilheteIdentidade> findAllByNumeroIdentificacao (String numeroIdentificacaoFiltro)
    {
        numeroIdentificacaoFiltro = "%" + numeroIdentificacaoFiltro + "%";
        Query q = this.em.createQuery("SELECT bi FROM BilheteIdentidade bi "
            + "WHERE bi.numeroIdentificacao LIKE :numeroIdentificacaoFiltro");
        q.setParameter("numeroIdentificacaoFiltro", numeroIdentificacaoFiltro);
        return q.getResultList();

    }

    public List<BilheteIdentidade> findAllByNomeByNumeroIdentificacao (
        String nomeFiltro, String numeroIdentificacaoFiltro)
    {
        /* 
        se nomeFiltro e numeroIdentificacaoFiltro forem nao nulos
            retornar  findAllByNomeAndByNumeroIdentificacao(nomeFiltro, numeroIdentificacaoFiltro)
        
        mas se nomeFiltro  for nao nulos
            retornar  findAllByNome(nomeFiltro)
        mas se numeroIdentificacaoFiltro  for nao nulos
            retornar  findAllByNumeroIdentificacao(numeroIdentificacaoFiltro)
        senao 
            retornar  findAll()
         */
        if (!StringUtils.isNull(nomeFiltro) && !StringUtils.isNull(numeroIdentificacaoFiltro))
        {
            return findAllByNomeAndByNumeroIdentificacao(nomeFiltro, numeroIdentificacaoFiltro);
        }
        else if (!StringUtils.isNull(nomeFiltro))
        {
            return findAllByNome(nomeFiltro);
        }
        else if (!StringUtils.isNull(numeroIdentificacaoFiltro))
        {
            return findAllByNumeroIdentificacao(numeroIdentificacaoFiltro);
        }
        else
        {
            return findAll();
        }

    }

}
