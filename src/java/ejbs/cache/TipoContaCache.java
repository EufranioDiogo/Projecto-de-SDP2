/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

import ejbs.entities.TipoConta;
import ejbs.facades.TipoContaFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author ed
 */
@Named(value = "tipoContaCache")
@ApplicationScoped
public class TipoContaCache implements Serializable {

    /**
     * Creates a new instance of TipoContaCache
     */
    private List<TipoConta> listaTipoConta;
    
    @EJB
    private TipoContaFacade tipoContaFacade;
    
    public TipoContaCache() {
    }
    
    @PostConstruct
    public void init() {
        this.listaTipoConta = this.tipoContaFacade.findAll();
    }

    public List<TipoConta> getListaTipoConta() {
        return listaTipoConta;
    }

    public void setListaTipoConta(List<TipoConta> listaTipoConta) {
        this.listaTipoConta = listaTipoConta;
    }
    
    
}
