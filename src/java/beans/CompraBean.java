/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.Veiculo;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author ed
 */
@Named(value = "compraBean")
@ViewScoped
public class CompraBean implements Serializable {

    /**
     * Creates a new instance of CompraBean
     */
    @Inject
    private MontraCarrinhoTrackBean carrinhoTrackBean;
    private List<Veiculo> productosParaSerComprados;
    
    public CompraBean() {
    }
    
    @PostConstruct
    public void init() {
        this.productosParaSerComprados = carrinhoTrackBean.getProductoList();
    }
    
}
