/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.BilheteIdentidade;
import ejbs.facades.BilheteIdentidadeFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author dm
 */
@Named(value = "bilheteIdentidadeConsultarBean")
@ViewScoped
public class BilheteIdentidadeConsultarBean implements Serializable
{

    @EJB
    private BilheteIdentidadeFacade bilheteIdentidadeFacade;

    private String nomeFiltro, numeroIdentificacaoFiltro;

    private List<BilheteIdentidade> bis;
    private BilheteIdentidade bi;
    private int pkBilheteIdentidade;

    public BilheteIdentidadeConsultarBean ()
    {
    }

    @PostConstruct
    public void init ()
    {
        this.nomeFiltro = this.numeroIdentificacaoFiltro = "";
        initBi();
        initBilhetes();
    }

    private void initBilhetes ()
    {
        this.bis = this.bilheteIdentidadeFacade.findAll();
    }
    
    private void initBi()
    {
        bi = new BilheteIdentidade();
    }

    // Business Methods
    public void consultar ()
    {
        this.bis
            = this.bilheteIdentidadeFacade.findAllByNomeByNumeroIdentificacao(
                this.nomeFiltro, this.numeroIdentificacaoFiltro);
    }
    
    // Getters and Setters
    public String getNomeFiltro ()
    {
        return nomeFiltro;
    }

    public void setNomeFiltro (String nomeFiltro)
    {
        this.nomeFiltro = nomeFiltro;
    }

    public String getNumeroIdentificacaoFiltro ()
    {
        return numeroIdentificacaoFiltro;
    }

    public void setNumeroIdentificacaoFiltro (String numeroIdentificacaoFiltro)
    {
        this.numeroIdentificacaoFiltro = numeroIdentificacaoFiltro;
    }

    public List<BilheteIdentidade> getBis ()
    {
        return bis;
    }

    public void setBis (List<BilheteIdentidade> bis)
    {
        this.bis = bis;
    }

    public BilheteIdentidade getBi ()
    {
        return bi;
    }

    public void setBi (BilheteIdentidade bi)
    {
        this.bi = bi;
    }

    public int getPkBilheteIdentidade ()
    {
        return pkBilheteIdentidade;
    }

    public void setPkBilheteIdentidade (int pkBilheteIdentidade)
    {
        this.pkBilheteIdentidade = pkBilheteIdentidade;
    }
    
    
    

}
