/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ed
 */
@Entity
@Table(name = "compra_veiculo", catalog = "ucandb", schema = "POSTGRES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompraVeiculo.findAll", query = "SELECT c FROM CompraVeiculo c"),
    @NamedQuery(name = "CompraVeiculo.findByPkCompraVeiculo", query = "SELECT c FROM CompraVeiculo c WHERE c.pkCompraVeiculo = :pkCompraVeiculo"),
    @NamedQuery(name = "CompraVeiculo.findByValorCompra", query = "SELECT c FROM CompraVeiculo c WHERE c.valorCompra = :valorCompra"),
    @NamedQuery(name = "CompraVeiculo.findByQuantidadeComprada", query = "SELECT c FROM CompraVeiculo c WHERE c.quantidadeComprada = :quantidadeComprada"),
    @NamedQuery(name = "CompraVeiculo.findByDataCompra", query = "SELECT c FROM CompraVeiculo c WHERE c.dataCompra = :dataCompra")})
public class CompraVeiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_compra_veiculo", nullable = false)
    private Integer pkCompraVeiculo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_compra", nullable = false)
    private BigInteger valorCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade_comprada", nullable = false)
    private int quantidadeComprada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_compra", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCompra;
    @JoinColumn(name = "fk_portfolio", referencedColumnName = "pk_portfolio", nullable = false)
    @ManyToOne(optional = false)
    private Portfolio fkPortfolio;

    public CompraVeiculo() {
    }

    public CompraVeiculo(Integer pkCompraVeiculo) {
        this.pkCompraVeiculo = pkCompraVeiculo;
    }

    public CompraVeiculo(Integer pkCompraVeiculo, BigInteger valorCompra, int quantidadeComprada, Date dataCompra) {
        this.pkCompraVeiculo = pkCompraVeiculo;
        this.valorCompra = valorCompra;
        this.quantidadeComprada = quantidadeComprada;
        this.dataCompra = dataCompra;
    }

    public Integer getPkCompraVeiculo() {
        return pkCompraVeiculo;
    }

    public void setPkCompraVeiculo(Integer pkCompraVeiculo) {
        this.pkCompraVeiculo = pkCompraVeiculo;
    }

    public BigInteger getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(BigInteger valorCompra) {
        this.valorCompra = valorCompra;
    }

    public int getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(int quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
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
        hash += (pkCompraVeiculo != null ? pkCompraVeiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompraVeiculo)) {
            return false;
        }
        CompraVeiculo other = (CompraVeiculo) object;
        if ((this.pkCompraVeiculo == null && other.pkCompraVeiculo != null) || (this.pkCompraVeiculo != null && !this.pkCompraVeiculo.equals(other.pkCompraVeiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.CompraVeiculo[ pkCompraVeiculo=" + pkCompraVeiculo + " ]";
    }
    
}
