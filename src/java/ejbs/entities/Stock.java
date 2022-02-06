/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ed
 */
@Entity
@Table(catalog = "ucandb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stock.findAll", query = "SELECT s FROM Stock s"),
    @NamedQuery(name = "Stock.findByPkStock", query = "SELECT s FROM Stock s WHERE s.pkStock = :pkStock"),
    @NamedQuery(name = "Stock.findByQuantVeiculoActual", query = "SELECT s FROM Stock s WHERE s.quantVeiculoActual = :quantVeiculoActual"),
    @NamedQuery(name = "Stock.findByQuantProductoMaxima", query = "SELECT s FROM Stock s WHERE s.quantProductoMaxima = :quantProductoMaxima")})
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_stock", nullable = false)
    private Integer pkStock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quant_veiculo_actual", nullable = false)
    private int quantVeiculoActual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quant_producto_maxima", nullable = false)
    private int quantProductoMaxima;
    @JoinColumn(name = "fk_portfolio", referencedColumnName = "pk_portfolio", nullable = false)
    @ManyToOne(optional = false)
    private Portfolio fkPortfolio;

    public Stock() {
    }

    public Stock(Integer pkStock) {
        this.pkStock = pkStock;
    }

    public Stock(Integer pkStock, int quantVeiculoActual, int quantProductoMaxima) {
        this.pkStock = pkStock;
        this.quantVeiculoActual = quantVeiculoActual;
        this.quantProductoMaxima = quantProductoMaxima;
    }

    public Integer getPkStock() {
        return pkStock;
    }

    public void setPkStock(Integer pkStock) {
        this.pkStock = pkStock;
    }

    public int getQuantVeiculoActual() {
        return quantVeiculoActual;
    }

    public void setQuantVeiculoActual(int quantVeiculoActual) {
        this.quantVeiculoActual = quantVeiculoActual;
    }

    public int getQuantProductoMaxima() {
        return quantProductoMaxima;
    }

    public void setQuantProductoMaxima(int quantProductoMaxima) {
        this.quantProductoMaxima = quantProductoMaxima;
    }

    public Portfolio getFkPortfolio() {
        return fkPortfolio;
    }

    public void setFkPortfolio(Portfolio fkPortfolio) {
        this.fkPortfolio = fkPortfolio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkStock != null ? pkStock.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.pkStock == null && other.pkStock != null) || (this.pkStock != null && !this.pkStock.equals(other.pkStock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.Stock[ pkStock=" + pkStock + " ]";
    }
    
}
