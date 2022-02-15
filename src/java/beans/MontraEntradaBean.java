/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.Montra;
import ejbs.entities.Veiculo;
import ejbs.facades.MontraFacade;
import java.io.Serializable;
import java.util.ArrayList;
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
@Named(value = "montraEntradaBean")
@ViewScoped
public class MontraEntradaBean implements Serializable {

    /**
     * Creates a new instance of MontraMostrarBean
     */
    private List<Montra> productOnMontra;
    private List<Veiculo> productNotInMontra;
    private List<Integer> montraSelectedItemsToInsert = new ArrayList<>();
    private Montra montraElement;
    
    @EJB
    private MontraFacade montraFacade;
    
    public MontraEntradaBean() {
    }
    
    
    @PostConstruct
    public void init() {
        this.montraElement = new Montra();
        this.productOnMontra = this.montraFacade.findAll();
        this.productNotInMontra = this.montraFacade.findAllProductsNotInMontra();
    }

    public List<Veiculo> getProductNotInMontra() {
        return productNotInMontra;
    }

    public void setProductNotInMontra(List<Veiculo> productNotInMontra) {
        this.productNotInMontra = productNotInMontra;
    }

    public List<Montra> getProductOnMontra() {
        return productOnMontra;
    }

    public void setProductOnMontra(List<Montra> productOnMontra) {
        this.productOnMontra = productOnMontra;
    }

    public Montra getMontraElement() {
        return montraElement;
    }

    public void setMontraElement(Montra montraElement) {
        this.montraElement = montraElement;
    }

    public List<Integer> getMontraSelectedItemsToInsert() {
        return montraSelectedItemsToInsert;
    }

    public void setMontraSelectedItemsToInsert(List<Integer> montraSelectedItemsToInsert) {
        this.montraSelectedItemsToInsert = montraSelectedItemsToInsert;
    }
    
    
    
    public void salvar() {
        try {
            for (int pkVeiculo : montraSelectedItemsToInsert) {
                Montra auxMontra = new Montra();
                auxMontra.setFkProducto(new Veiculo(pkVeiculo));
                montraFacade.create(auxMontra);
            }
            Mensagem.sucessoMsg("Productos adicionados a montra");
        } catch (Exception e) {
        }
    }
    
    public void limpar() {
        
    }
    
    public void delete(int montraPk) {
        try {
            this.montraFacade.remove(new Montra(montraPk));
            Mensagem.sucessoMsg("Producto Deletado");
        } catch (Exception e) {
            Mensagem.erroMsg(e.toString());
        }
    }
    
    
}
