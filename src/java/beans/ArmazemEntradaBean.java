/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.Portfolio;
import ejbs.entities.Stock;
import ejbs.facades.PortfolioFacade;
import ejbs.facades.StockFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author milzio
 */
@Named()
@ViewScoped
public class ArmazemEntradaBean implements Serializable {

    private List<Portfolio> listProductosPortfolio;
    private String automovelSelecionado;
    private int quantAutomoveis;
    private int quantMaximaAutomoveis;
    private Stock elementoStock;

    @EJB
    private PortfolioFacade portfolioFacade;
    @EJB
    private StockFacade stockFacade;

    public ArmazemEntradaBean() {
        
    }

    @PostConstruct
    public void init() {
        elementoStock = new Stock();
        this.initPortfolio();
    }
    

    private void initPortfolio() {
        this.listProductosPortfolio = this.portfolioFacade.findAllVeiculos();
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

    public Stock getElementoStock() {
        return elementoStock;
    }

    public void setElementoStock(Stock elementoStock) {
        this.elementoStock = elementoStock;
    }

    public void ola() {
        System.out.println("Automovel: " + automovelSelecionado + " Quant Actual: " + quantAutomoveis + " Quant Max: " + quantMaximaAutomoveis);
    }

    public void limpar() {
        System.out.println("Limpar");
    }

    public void salvar() {
        try {
            elementoStock.setFkPortfolio(new Portfolio(this.automovelSelecionado));
            elementoStock.setQuantVeiculoActual(quantAutomoveis);
            elementoStock.setQuantProductoMaxima(quantMaximaAutomoveis);

            stockFacade.create(elementoStock);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
