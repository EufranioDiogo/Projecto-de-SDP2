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
@Named(value = "armazemRupturaBean")
@ViewScoped
public class ArmazemRupturaBean implements Serializable {
private List<Stock> listaProductosRupturaStock;
private List<Stock> listaProductosQuaseRupturaStock;
private List<Stock> listaProductosPreRupturaStock;

    @EJB
    private StockFacade stockFacade;
    /**
     * Creates a new instance of ArmazemConsultarBean
     */
    public ArmazemRupturaBean() {
    }
    
    @PostConstruct
    public void init() {
        initRupturaList();
    }
    
    public void initRupturaList() {
        this.listaProductosRupturaStock = stockFacade.findAllRuptura();
        this.listaProductosQuaseRupturaStock = stockFacade.findAllQuaseRuptura();
        this.listaProductosPreRupturaStock = stockFacade.findAllPreRuptura();
    }

    public List<Stock> getListaProductosRupturaStock() {
        return listaProductosRupturaStock;
    }

    public List<Stock> getListaProductosQuaseRupturaStock() {
        return listaProductosQuaseRupturaStock;
    }

    public List<Stock> getListaProductosPreRupturaStock() {
        return listaProductosPreRupturaStock;
    }
}

    
