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
@Table(catalog = "ucandb", schema = "POSTGRES")
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
    private List<Veiculo> veiculoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPortfolio")
    private List<CompraVeiculo> compraVeiculoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPortfolio")
    private List<Stock> stockList;

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
    public List<Veiculo> getVeiculoList() {
        return veiculoList;
    }

    public void setVeiculoList(List<Veiculo> veiculoList) {
        this.veiculoList = veiculoList;
    }

    @XmlTransient
    public List<CompraVeiculo> getCompraVeiculoList() {
        return compraVeiculoList;
    }

    public void setCompraVeiculoList(List<CompraVeiculo> compraVeiculoList) {
        this.compraVeiculoList = compraVeiculoList;
    }

    @XmlTransient
    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
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
