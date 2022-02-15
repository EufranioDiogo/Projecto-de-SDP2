/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    @Column(name = "quant_veiculo_actual")
    private Integer quantVeiculoActual;
    @Column(name = "quant_producto_maxima")
    private Integer quantProductoMaxima;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkStock")
    private List<Veiculo> veiculoList;
    @JoinColumn(name = "fk_portfolio", referencedColumnName = "pk_portfolio")
    @ManyToOne
    private Portfolio fkPortfolio;

    public Stock() {
    }

    public Stock(Integer pkStock) {
        this.pkStock = pkStock;
    }

    public Integer getPkStock() {
        return pkStock;
    }

    public void setPkStock(Integer pkStock) {
        this.pkStock = pkStock;
    }

    public Integer getQuantVeiculoActual() {
        return quantVeiculoActual;
    }

    public void setQuantVeiculoActual(Integer quantVeiculoActual) {
        this.quantVeiculoActual = quantVeiculoActual;
    }

    public Integer getQuantProductoMaxima() {
        return quantProductoMaxima;
    }

    public void setQuantProductoMaxima(Integer quantProductoMaxima) {
        this.quantProductoMaxima = quantProductoMaxima;
    }

    @XmlTransient
    public List<Veiculo> getVeiculoList() {
        return veiculoList;
    }

    public void setVeiculoList(List<Veiculo> veiculoList) {
        this.veiculoList = veiculoList;
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
