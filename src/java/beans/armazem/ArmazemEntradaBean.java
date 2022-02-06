/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.armazem;

import ejbs.entities.Portfolio;
import ejbs.entities.Stock;
import ejbs.facades.PortfolioFacade;
import ejbs.facades.StockFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author ed
 */
@Named(value = "armazemEntradaBean")
@ViewScoped
public class ArmazemEntradaBean implements Serializable {

    private List<Portfolio> listProductosPortfolio = new ArrayList<>();
    private String automovelSelecionado;
    private int quantAutomoveis;
    private int quantMaximaAutomoveis;
    private Stock elementoStock;
    
    public ArmazemEntradaBean() {
        elementoStock = new Stock();
    }
    public List<Portfolio> getListProductosPortfolio() {
        return listProductosPortfolio;
    }

    public void setListProductosPortfolio(List<Portfolio> listProductosPortfolio) {
        this.listProductosPortfolio = listProductosPortfolio;
    }

    public String getAutomovelSelecionado() {
        return automovelSelecionado;
    }

    public void setAutomovelSelecionado(String automovelSelecionado) {
        this.automovelSelecionado = automovelSelecionado;
    }

    public int getQuantAutomoveis() {
        return quantAutomoveis;
    }

    public void setQuantAutomoveis(int quantAutomoveis) {
        this.quantAutomoveis = quantAutomoveis;
    }

    public int getQuantMaximaAutomoveis() {
        return quantMaximaAutomoveis;
    }

    public void setQuantMaximaAutomoveis(int quantMaximaAutomoveis) {
        this.quantMaximaAutomoveis = quantMaximaAutomoveis;
    }

    
}
