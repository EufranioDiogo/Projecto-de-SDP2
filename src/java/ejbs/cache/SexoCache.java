package ejbs.cache;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejbs.entities.Sexo;
import ejbs.facades.SexoFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author ed
 */
@Named(value = "sexoCache")
@ApplicationScoped
public class SexoCache {

    /**
     * Creates a new instance of SexoCache
     */
    private List<Sexo> listaSexo;
    
    @EJB
    private SexoFacade sexoFacade;
    
    public SexoCache() {
    }
    
    @PostConstruct
    public void init() {
        this.listaSexo = this.sexoFacade.findAll();
    }

    public List<Sexo> getListaSexo() {
        return listaSexo;
    }

    public void setListaSexo(List<Sexo> listaSexo) {
        this.listaSexo = listaSexo;
    }
    
}
