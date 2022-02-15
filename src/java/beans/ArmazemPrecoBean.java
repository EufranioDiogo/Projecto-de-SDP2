/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.Portfolio;
import ejbs.entities.Precario;
import ejbs.entities.Stock;
import ejbs.entities.Veiculo;
import ejbs.facades.PortfolioFacade;
import ejbs.facades.PrecarioFacade;
import ejbs.facades.StockFacade;
import ejbs.facades.VeiculoFacade;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
public class ArmazemPrecoBean implements Serializable {

    private List<Portfolio> listTipoProductos;
    private List<Portfolio> listaModelosProductos;
    private List<Veiculo> listProductosPortfolio;
    private Precario precarioElement;
    private Portfolio portfolio;
    private Veiculo veiculo;
    private Portfolio portfolioElementTipo;
    private Portfolio portfolioElementModelo;
    private Stock portfolioElementAutomovel;
    private int productId;

    @EJB
    private StockFacade stockFacade;
    @EJB
    private VeiculoFacade veiculoFacade;
    @EJB
    private PrecarioFacade precarioFacade;
    @EJB
    private PortfolioFacade portfolioFacade;

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
        this.portfolio = new Portfolio();
        this.portfolioElementTipo = new Portfolio();
        this.portfolioElementModelo = new Portfolio();
        this.portfolioElementAutomovel = new Stock();
        this.precarioElement = new Precario();
        this.veiculo = new Veiculo();
        this.precarioElement.setValorCompra(BigInteger.ZERO);
        this.veiculo.setFkStock(portfolioElementAutomovel);
        this.precarioElement.setFkProducto(this.veiculo);
    }

    public void initListaProductos() {
        try {
            this.listProductosPortfolio = veiculoFacade.findAll();
            this.listaModelosProductos = this.portfolioFacade.findPortfolioProductIsInStock(this.listProductosPortfolio.get(0).getFkStock().getFkPortfolio().getPkPortfolio());
            this.listTipoProductos = this.portfolioFacade.findPortfolioProductIsInStock(this.listaModelosProductos.get(0).getFkPortfolio());
            try {
                this.productId = this.listProductosPortfolio.get(0).getPkVeiculo();
                this.updateQuantList();
            } catch (Exception e) {
            }

        } catch (Exception e) {
            Mensagem.erroMsg(e.toString());
        }
    }

    public List<Veiculo> getListaProductosStock() {
        return listProductosPortfolio;
    }

    public void setListaProductosStock(List<Veiculo> listaProductosStock) {
        this.listProductosPortfolio = listaProductosStock;
    }

    public StockFacade getStockFacade() {
        return stockFacade;
    }

    public void setStockFacade(StockFacade stockFacade) {
        this.stockFacade = stockFacade;
    }

    public Precario getPrecarioElement() {
        return precarioElement;
    }

    public void setPrecarioElement(Precario precarioElement) {
        this.precarioElement = precarioElement;
    }

    public void salvar() {
        try {
            System.out.println("Ola");
            this.precarioElement.setFkProducto(new Veiculo(productId));
            System.out.println("Valor Compra: " + this.precarioElement.getValorCompra());
            System.out.println("FK Compra: " + this.precarioElement.getFkProducto());
            this.precarioElement.setDataCompra(new Date());
            Mensagem.sucessoMsg("Preço definido com sucesso");
            
            if (precarioFacade.findPrecarioByStockPK(productId).isEmpty()) {
                precarioFacade.create(precarioElement);
            } else {
                precarioFacade.edit(precarioElement);
            }
            
        } catch (Exception e) {
            System.err.println(e.toString());
            Mensagem.erroMsg(e.toString());

        }
    }

    public void limpar() {

    }

    public List<Portfolio> getListTipoProductos() {
        return listTipoProductos;
    }

    public void setListTipoProductos(List<Portfolio> listTipoProductos) {
        this.listTipoProductos = listTipoProductos;
    }

    public List<Portfolio> getListaModelosProductos() {
        return listaModelosProductos;
    }

    public void setListaModelosProductos(List<Portfolio> listaModelosProductos) {
        this.listaModelosProductos = listaModelosProductos;
    }

    public List<Veiculo> getListProductosPortfolio() {
        return listProductosPortfolio;
    }

    public void setListProductosPortfolio(List<Veiculo> listProductosPortfolio) {
        this.listProductosPortfolio = listProductosPortfolio;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void updateModeloList() {
        this.listaModelosProductos = this.portfolioFacade.findPortfolioProductIsInStock(this.portfolioElementTipo.getPkPortfolio());
        this.portfolioElementModelo.setPkPortfolio(this.listaModelosProductos.get(0).getPkPortfolio());

        //this.listProductosPortfolio = this.stockFacade.findProdutosStockFromFKPortfolio(this.portfolioElementModelo.getPkPortfolio());
        this.portfolioElementAutomovel.setFkPortfolio(this.listProductosPortfolio.get(0).getFkStock().getFkPortfolio());

        try {
            this.precarioElement = this.precarioFacade.findPrecarioByStockPK(this.portfolioElementAutomovel.getPkStock()).get(0);
        } catch (Exception e) {
            this.precarioElement = new Precario();
            this.precarioElement.setValorCompra(BigInteger.ZERO);
        }

    }

    public void updateQuantList() {
        try {
            this.precarioElement = this.precarioFacade.findPrecarioByStockPK(this.productId).get(0);
            System.out.println("Preçario: " + this.precarioElement.getValorCompra());
        } catch (Exception e) {
            this.precarioElement = new Precario();
            this.precarioElement.setValorCompra(BigInteger.ZERO);
        }
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

    public Stock getPortfolioElementAutomovel() {
        return portfolioElementAutomovel;
    }

    public void setPortfolioElementAutomovel(Stock portfolioElementAutomovel) {
        this.portfolioElementAutomovel = portfolioElementAutomovel;
    }

    public int getStockId() {
        return productId;
    }

    public void setStockId(int stockId) {
        this.productId = stockId;
    }

}
