/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.Stock;
import ejbs.facades.StockFacade;
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
@Named(value = "armazemConsultarBean")
@ViewScoped
public class ArmazemConsultarBean implements Serializable {
    
    private List<Stock> listaProductosStock;
    @EJB
    private StockFacade stockFacade;
    /**
     * Creates a new instance of ArmazemConsultarBean
     */
    public ArmazemConsultarBean() {
    }
    
    @PostConstruct
    public void init() {
        initStockList();
    }
    
    public void initStockList() {
        this.listaProductosStock = stockFacade.findAll();
        
    }

    public List<Stock> getListaProductosStock() {
        return listaProductosStock;
    }

    public void setListaProductosStock(List<Stock> listaProductosStock) {
        this.listaProductosStock = listaProductosStock;
    }
}
