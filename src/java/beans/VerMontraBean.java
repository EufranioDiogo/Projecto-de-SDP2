/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.Montra;
import ejbs.entities.Precario;
import ejbs.facades.MontraFacade;
import ejbs.facades.PrecarioFacade;
import java.math.BigInteger;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author ed
 */
@Named(value = "verMontraBean")
@RequestScoped
public class VerMontraBean {

    /**
     * Creates a new instance of VerMontraBean
     */
    List<Montra> productosInMontra;
    
    @EJB
    private MontraFacade montraFacade;
    @EJB
    private PrecarioFacade precarioFacade;
    
    public VerMontraBean() {
    }
    
    @PostConstruct
    public void init() {
        this.initListaMontra();
    }
    
    public void initListaMontra() {
        this.productosInMontra = this.montraFacade.findAll();
    }

    public List<Montra> getProductosInMontra() {
        return productosInMontra;
    }

    public void setProductosInMontra(List<Montra> productosInMontra) {
        this.productosInMontra = productosInMontra;
    }
    
    public BigInteger getProductoPrice(int pkVeiculo) {
        Precario auxPrecario = new Precario();
        try {
            auxPrecario = this.precarioFacade.findPrecarioByStockPK(pkVeiculo).get(0);
        } catch (Exception e) {
            auxPrecario.setValorCompra(BigInteger.ZERO);
        }
        return auxPrecario.getValorCompra();
    }
    
}
