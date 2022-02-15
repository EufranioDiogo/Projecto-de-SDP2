/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.Montra;
import ejbs.entities.Precario;
import ejbs.entities.Veiculo;
import ejbs.facades.MontraFacade;
import ejbs.facades.PrecarioFacade;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.ApplicationException;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

/**
 *
 * @author ed
 */
@Named(value = "montraCarrinhoTrackBean")
@SessionScoped
public class MontraCarrinhoTrackBean implements Serializable {

    private HashMap<String, List<Veiculo>> veiculosToBuy;
    @Inject
    VerMontraBean verMontraBean;
    @Inject
    AuthBean authBean;
    
    public MontraCarrinhoTrackBean() {
    }
    
    @PostConstruct
    public void init() {
        this.veiculosToBuy = new HashMap<>();
    }

    public void adicionarVeiculoCarrinho(int pkVeiculo) {
        //System.out.println("PK: " + verMontraBean.productosInMontra.get(0).getPkMontra());
        boolean isInserted = false;
        String username = authBean.getConta().getUsername();
        
        if (veiculosToBuy.get(username) == null) {
            this.veiculosToBuy.put(username, new ArrayList<>());
        }
        
        for (Veiculo veiculo : this.veiculosToBuy.get(username)) {
            if (veiculo.getPkVeiculo() == pkVeiculo) {
                isInserted = true;
                break;
            }
        }
        if (!isInserted) {
            
            this.veiculosToBuy.get(username).add(new Veiculo(pkVeiculo));
        }
        
        
    }

    public HashMap<String, List<Veiculo>> getVeiculosToBuy() {
        if (veiculosToBuy.get(authBean.getConta().getUsername()) == null) {
            this.veiculosToBuy.put(authBean.getConta().getUsername(), new ArrayList<>());
        }
        return veiculosToBuy;
    }
    
    public int quantItensCarrinho() {
        if (veiculosToBuy.get(authBean.getConta().getUsername()) == null) {
            this.veiculosToBuy.put(authBean.getConta().getUsername(), new ArrayList<>());
        }
        return veiculosToBuy.get(authBean.getConta().getUsername()).size();
    }
    
    public List<Veiculo> getProductoList() {
        if (veiculosToBuy.get(authBean.getConta().getUsername()) == null) {
            this.veiculosToBuy.put(authBean.getConta().getUsername(), new ArrayList<>());
        }
        return veiculosToBuy.get(authBean.getConta().getUsername());
    }

    public void setVeiculosToBuy(List<Veiculo> veiculosToBuy) {
        this.veiculosToBuy = (HashMap<String, List<Veiculo>>) veiculosToBuy;
    }
    
    /**
     * Creates a new instance of MontraCarrinhoTrackBean
     */
    
    
}
