/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.Portfolio;
import ejbs.entities.Stock;
import ejbs.entities.Veiculo;
import ejbs.facades.PortfolioFacade;
import ejbs.facades.StockFacade;
import ejbs.facades.VeiculoFacade;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import utils.Mensagem;

/**
 *
 * @author milzio
 */
@Named()
@ViewScoped
public class ArmazemEntradaBean implements Serializable {

    private List<Portfolio> listProductosPortfolio;
    private List<Portfolio> listTipoProductos;
    private List<Portfolio> listModeloProductos;
    private Stock elementoStock;
    private Veiculo veiculoElement;
    private Portfolio portfolioElementTipo;
    private Portfolio portfolioElementModelo;
    private Portfolio portfolioElementAutomovel;
    private Stock stockElement;
    private Part file;

    @EJB
    private PortfolioFacade portfolioFacade;
    @EJB
    private StockFacade stockFacade;
    @EJB
    private VeiculoFacade veiculoFacade;

    public ArmazemEntradaBean() {

    }

    @PostConstruct
    public void init() {
        portfolioElementTipo = new Portfolio();
        portfolioElementModelo = new Portfolio();
        portfolioElementAutomovel = new Portfolio();
        stockElement = new Stock();
        veiculoElement = new Veiculo();
        stockElement.setQuantProductoMaxima(0);
        stockElement.setQuantVeiculoActual(0);

        this.initPortfolio();
    }

    private void initPortfolio() {
        this.listTipoProductos = this.portfolioFacade.findAllVeiculosTypes();
        this.portfolioElementTipo.setPkPortfolio(this.listTipoProductos.get(0).getPkPortfolio());

        this.listModeloProductos = this.portfolioFacade.findPortfolioProductFromParent(this.portfolioElementTipo.getPkPortfolio());
        this.portfolioElementModelo.setPkPortfolio(this.listModeloProductos.get(0).getPkPortfolio());

        this.listProductosPortfolio = this.portfolioFacade.findPortfolioProductFromParent(this.portfolioElementModelo.getPkPortfolio());
        this.portfolioElementAutomovel.setPkPortfolio(this.listProductosPortfolio.get(0).getPkPortfolio());

        updateQuantList();
    }

    public List<Portfolio> getListProductosPortfolio() {
        return listProductosPortfolio;
    }

    public void setListProductosPortfolio(List<Portfolio> listProductosPortfolio) {
        this.listProductosPortfolio = listProductosPortfolio;
    }

    public void limpar() {
        System.out.println("Limpar");
    }

    public void salvar() {

        try {
            List<Stock> auxList = this.stockFacade.findProdutosStockFromFKPortfolio(this.portfolioElementAutomovel.getPkPortfolio());
            boolean isStockElementNotCreated = auxList.isEmpty();

            System.out.println("Size1: " + auxList.size());
            if (auxList.size() != 0) {
                System.out.println("Editar 1");
                elementoStock.setFkPortfolio(this.portfolioElementAutomovel);

                stockFacade.edit(elementoStock);

                elementoStock = stockFacade.findProdutosStockFromFKPortfolio(this.portfolioElementAutomovel.getPkPortfolio()).get(0);
            } else {
                System.out.println("Criar 1");
                elementoStock.setFkPortfolio(this.portfolioElementAutomovel);

                stockFacade.create(elementoStock);

                elementoStock = stockFacade.findProdutosStockFromFKPortfolio(this.portfolioElementAutomovel.getPkPortfolio()).get(0);
            }

            try {
                List<Veiculo> auxList2 = veiculoFacade.findVeiculoBasedOnPKStock(this.elementoStock.getPkStock());

                veiculoElement.setFkStock(elementoStock);
                veiculoElement.setFkTipoVeiculo(portfolioElementTipo);
                System.out.println("Size2: " + auxList2.size());

                if (auxList2.size() != 0) {
                    System.out.println("Editar 2");
                    veiculoElement.setPkVeiculo(auxList2.get(0).getPkVeiculo());

                    veiculoFacade.edit(veiculoElement);
                } else {
                    System.out.println("Criar 2");

                    veiculoFacade.create(veiculoElement);
                }
                Mensagem.sucessoMsg("Entrada Feita com Sucesso");
            } catch (Exception e) {
            }

            init();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public List<Portfolio> getListTipoProductos() {
        return listTipoProductos;
    }

    public void setListTipoProductos(List<Portfolio> listTipoProductos) {
        this.listTipoProductos = listTipoProductos;
    }

    public List<Portfolio> getListModeloProductos() {
        return listModeloProductos;
    }

    public void setListModeloProductos(List<Portfolio> listModeloProductos) {
        this.listModeloProductos = listModeloProductos;
    }

    public Stock getElementoStock() {
        return elementoStock;
    }

    public void setElementoStock(Stock elementoStock) {
        this.elementoStock = elementoStock;
    }

    public Portfolio getPortfolioElementTipo() {
        return portfolioElementTipo;
    }

    public void setPortfolioElementTipo(Portfolio portfolioElementTipo) {
        this.portfolioElementTipo = portfolioElementTipo;
    }

    public Portfolio getPortfolioElementModelo() {
        return portfolioElementModelo;
    }

    public void setPortfolioElementModelo(Portfolio portfolioElementModelo) {
        this.portfolioElementModelo = portfolioElementModelo;
    }

    public Portfolio getPortfolioElementAutomovel() {
        return portfolioElementAutomovel;
    }

    public void setPortfolioElementAutomovel(Portfolio portfolioElementAutomovel) {
        this.portfolioElementAutomovel = portfolioElementAutomovel;
    }

    public Stock getStockElement() {
        return stockElement;
    }

    public void setStockElement(Stock stockElement) {
        this.stockElement = stockElement;
    }

    public void updateModeloList() {

        try {
            this.listModeloProductos = this.portfolioFacade.findPortfolioProductFromParent(this.portfolioElementTipo.getPkPortfolio());
            this.portfolioElementModelo.setPkPortfolio(this.listModeloProductos.get(0).getPkPortfolio());

            this.listProductosPortfolio = this.portfolioFacade.findPortfolioProductFromParent(this.portfolioElementModelo.getPkPortfolio());
            this.portfolioElementAutomovel.setPkPortfolio(this.listProductosPortfolio.get(0).getPkPortfolio());
            this.elementoStock = this.stockFacade.findProdutosStockFromFKPortfolio(this.portfolioElementAutomovel.getPkPortfolio()).get(0);
        } catch (Exception e) {
            this.elementoStock = new Stock();
            this.elementoStock.setQuantVeiculoActual(0);
            this.elementoStock.setQuantProductoMaxima(0);
        }

    }

    public void updateProductsList() {

        try {
            this.listProductosPortfolio = this.portfolioFacade.findPortfolioProductFromParent(this.portfolioElementModelo.getPkPortfolio());
            this.portfolioElementAutomovel.setPkPortfolio(this.listProductosPortfolio.get(0).getPkPortfolio());
            this.elementoStock = this.stockFacade.findProdutosStockFromFKPortfolio(this.portfolioElementAutomovel.getPkPortfolio()).get(0);
        } catch (Exception e) {
            this.elementoStock = new Stock();
            this.elementoStock.setQuantVeiculoActual(0);
            this.elementoStock.setQuantProductoMaxima(0);
        }
    }

    public void updateQuantList() {
        try {
            this.elementoStock = this.stockFacade.findProdutosStockFromFKPortfolio(this.portfolioElementAutomovel.getPkPortfolio()).get(0);
        } catch (Exception e) {
            this.elementoStock = new Stock();
            this.elementoStock.setQuantVeiculoActual(0);
            this.elementoStock.setQuantProductoMaxima(0);
        }
    }

    public Veiculo getVeiculoElement() {
        return veiculoElement;
    }

    public void setVeiculoElement(Veiculo veiculoElement) {
        this.veiculoElement = veiculoElement;
    }

    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile auxFile = event.getFile();
        this.veiculoElement.setUrlImagem(auxFile.getFileName());
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

}
