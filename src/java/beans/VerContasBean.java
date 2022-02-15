/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.Conta;
import ejbs.facades.ContaFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ed
 */
@Named(value = "verContasBean")
@ViewScoped
public class VerContasBean implements Serializable {

    /**
     * Creates a new instance of VerContasBean
     */
    
    private List<Conta> listaConta;
    
    @EJB
    private ContaFacade contaFacade;
    
    public VerContasBean() {
    }
    
    @PostConstruct
    public void init() {
        this.listaConta = this.contaFacade.findAll();
    }

    public List<Conta> getListaConta() {
        return listaConta;
    }

    public void setListaConta(List<Conta> listaConta) {
        this.listaConta = listaConta;
    }
    
    public void actualizar(int pkConta) {
        
    }
    public void deletar(int pkConta) {
        this.contaFacade.remove(new Conta(pkConta));
    }
}
