/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NamedQuery(name = "Portfolio.findAll", query = "SELECT p FROM Portfolio p"),
    @NamedQuery(name = "Portfolio.findByPkPortfolio", query = "SELECT p FROM Portfolio p WHERE p.pkPortfolio = :pkPortfolio"),
    @NamedQuery(name = "Portfolio.findByDesignacao", query = "SELECT p FROM Portfolio p WHERE p.designacao = :designacao"),
    @NamedQuery(name = "Portfolio.findByFkPortfolio", query = "SELECT p FROM Portfolio p WHERE p.fkPortfolio = :fkPortfolio")})
public class Portfolio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "pk_portfolio", nullable = false, length = 200)
    private String pkPortfolio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(nullable = false, length = 200)
    private String designacao;
    @Size(max = 200)
    @Column(name = "fk_portfolio", length = 200)
    private String fkPortfolio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPortfolio")
    private Collection<Veiculo> veiculoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPortfolio")
    private Collection<CompraVeiculo> compraVeiculoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPortfolio")
    private Collection<Stock> stockCollection;

    public Portfolio() {
    }

    public Portfolio(String pkPortfolio) {
        this.pkPortfolio = pkPortfolio;
    }

    public Portfolio(String pkPortfolio, String designacao) {
        this.pkPortfolio = pkPortfolio;
        this.designacao = designacao;
    }

    public String getPkPortfolio() {
        return pkPortfolio;
    }

    public void setPkPortfolio(String pkPortfolio) {
        this.pkPortfolio = pkPortfolio;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public String getFkPortfolio() {
        return fkPortfolio;
    }

    public void setFkPortfolio(String fkPortfolio) {
        this.fkPortfolio = fkPortfolio;
    }

    @XmlTransient
    public Collection<Veiculo> getVeiculoCollection() {
        return veiculoCollection;
    }

    public void setVeiculoCollection(Collection<Veiculo> veiculoCollection) {
        this.veiculoCollection = veiculoCollection;
    }

    @XmlTransient
    public Collection<CompraVeiculo> getCompraVeiculoCollection() {
        return compraVeiculoCollection;
    }

    public void setCompraVeiculoCollection(Collection<CompraVeiculo> compraVeiculoCollection) {
        this.compraVeiculoCollection = compraVeiculoCollection;
    }

    @XmlTransient
    public Collection<Stock> getStockCollection() {
        return stockCollection;
    }

    public void setStockCollection(Collection<Stock> stockCollection) {
        this.stockCollection = stockCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPortfolio != null ? pkPortfolio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Portfolio)) {
            return false;
        }
        Portfolio other = (Portfolio) object;
        if ((this.pkPortfolio == null && other.pkPortfolio != null) || (this.pkPortfolio != null && !this.pkPortfolio.equals(other.pkPortfolio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.Portfolio[ pkPortfolio=" + pkPortfolio + " ]";
    }
    
}
