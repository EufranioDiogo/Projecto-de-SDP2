/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.Portfolio;
import ejbs.entities.Precario;
import ejbs.entities.Stock;
import ejbs.facades.PrecarioFacade;
import ejbs.facades.StockFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import utils.Mensagem;

/**
 *
 * @author ed
 */
@Named(value = "armazemPrecoBean")
@ViewScoped
public class ArmazemPrecoBean implements Serializable{
    private List<Stock> listaProductosStock;
    private Precario precario;
    private Portfolio portfolio;
    
    @EJB
    private StockFacade stockFacade;
    @EJB
    private PrecarioFacade precarioFacade;
    private Stock stock;
    
    /**
     * Creates a new instance of ArmazemPrecoBean
     */
    public ArmazemPrecoBean() {
    }
    
    @PostConstruct
    public void init() {
        initPrecario();
        initListaProductos();
    }
    
    public void initPrecario() {
        this.precario = new Precario();
        this.portfolio = new Portfolio();
        this.stock = new Stock();
        this.stock.setFkPortfolio(portfolio);
        this.precario.setFkStock(this.stock);
    }
    
    public void initListaProductos() {
        this.listaProductosStock = stockFacade.findAll();
    }

    public List<Stock> getListaProductosStock() {
        return listaProductosStock;
    }

    public void setListaProductosStock(List<Stock> listaProductosStock) {
        this.listaProductosStock = listaProductosStock;
    }

    public StockFacade getStockFacade() {
        return stockFacade;
    }

    public void setStockFacade(StockFacade stockFacade) {
        this.stockFacade = stockFacade;
    }

    public Precario getPrecario() {
        return precario;
    }

    public void setPrecario(Precario precario) {
        this.precario = precario;
    }
    
    
    public void salvar() {
        try {
            System.out.println("Valor Compra: " + this.precario.getValorCompra());
            System.out.println("FK Compra: " + this.precario.getFkStock());
            
            Mensagem.sucessoMsg("Preço definido com sucesso");
            precarioFacade.create(precario);
        } catch (Exception e) {
        }
    }
    
    public void limpar() {
        
    }
}
