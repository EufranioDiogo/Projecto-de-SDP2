/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.cache.LocalidadeCache;
import ejbs.entities.BilheteIdentidade;
import ejbs.entities.Localidade;
import ejbs.facades.BilheteIdentidadeFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author benvxavier
 */
@Named(value = "bilheteIdentidadeNovoBean")
@ViewScoped
public class BilheteIdentidadeNovoBean implements Serializable
{

    @EJB
    private BilheteIdentidadeFacade bilheteIdentidadeFacade;

    @Inject
    private LocalidadeCache localidadeBean;

//    private String nome;
//    private Date dataNascimento;
    private BilheteIdentidade bi;
    private int pkPaisNascimento, pkProvinciaNascimento;
    private List<Localidade> provinciaNascimentoList;
    private Localidade localNascimento;

    /**
     * Creates a new instance of BilheteIdentidadeNovoBean
     */
    public BilheteIdentidadeNovoBean()
    {
    }

    @PostConstruct
    public void init()
    {
        bi = new BilheteIdentidade();

        initLocalNascimento();
    }

    // Business Methods
    private void initLocalNascimento()
    {
        initPaisNascimento();
        initProvinciaNascimento();
    }
    
    private void initPaisNascimento()
    {
        this.pkPaisNascimento = this.localidadeBean.findByPaisPadrao().getPkLocalidade();
    }

    private void initProvinciaNascimento()
    {
        this.provinciaNascimentoList = this.localidadeBean.findAllOrderedByNome(pkPaisNascimento);
        this.localNascimento = this.provinciaNascimentoList.get(0);   
        this.pkProvinciaNascimento = localNascimento.getPkLocalidade();
    }
    
    public void updateProvinciaNascimentoList()
    {
        this.provinciaNascimentoList = this.localidadeBean.findAllOrderedByNome(pkPaisNascimento);
        this.localNascimento = this.provinciaNascimentoList.get(0);   
        this.pkProvinciaNascimento = this.localNascimento.getPkLocalidade();
    }
    
    public void updateLocalNascimento()
    {
        this.localNascimento = this.localidadeBean.find(this.pkProvinciaNascimento);
    }
    

    public void salvar()
    {
        bi.setFkLocalidade(this.localNascimento);
        this.bilheteIdentidadeFacade.create(bi);
    }

    public void limpar()
    {
        this.bilheteIdentidadeFacade.limpar(bi);
        initLocalNascimento();
    }

    // Getters and Setters
//    public String getNome()
//    {
//        return nome;
//    }
//
//    public void setNome(String nome)
//    {
//        this.nome = nome;
//    }
//
//    public Date getDataNascimento()
//    {
//        return dataNascimento;
//    }
//
//    public void setDataNascimento(Date dataNascimento)
//    {
//        this.dataNascimento = dataNascimento;
//    }

    public int getPkPaisNascimento()
    {
        return pkPaisNascimento;
    }

    public void setPkPaisNascimento(int pkPaisNascimento)
    {
        this.pkPaisNascimento = pkPaisNascimento;
    }

    public List<Localidade> getProvinciaNascimentoList()
    {
        return provinciaNascimentoList;
    }

    public Localidade getLocalNascimento()
    {
        return localNascimento;
    }

    public void setLocalNascimento(Localidade localNascimento)
    {
        this.localNascimento = localNascimento;
    }

    public int getPkProvinciaNascimento()
    {
        return pkProvinciaNascimento;
    }

    public void setPkProvinciaNascimento(int pkProvinciaNascimento)
    {
        this.pkProvinciaNascimento = pkProvinciaNascimento;
    }

    public BilheteIdentidade getBi ()
    {
        return bi;
    }

    public void setBi (BilheteIdentidade bi)
    {
        this.bi = bi;
    }

    
}
