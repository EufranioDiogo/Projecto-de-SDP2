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
@Table(catalog = "ucandb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Precario.findAll", query = "SELECT p FROM Precario p"),
    @NamedQuery(name = "Precario.findByPkCompraVeiculo", query = "SELECT p FROM Precario p WHERE p.pkCompraVeiculo = :pkCompraVeiculo"),
    @NamedQuery(name = "Precario.findByValorCompra", query = "SELECT p FROM Precario p WHERE p.valorCompra = :valorCompra"),
    @NamedQuery(name = "Precario.findByDataCompra", query = "SELECT p FROM Precario p WHERE p.dataCompra = :dataCompra")})
public class Precario implements Serializable {

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
    @Column(name = "data_compra", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCompra;
    @JoinColumn(name = "fk_producto", referencedColumnName = "pk_veiculo", nullable = false)
    @ManyToOne(optional = false)
    private Veiculo fkProducto;

    public Precario() {
    }

    public Precario(Integer pkCompraVeiculo) {
        this.pkCompraVeiculo = pkCompraVeiculo;
    }

    public Precario(Integer pkCompraVeiculo, BigInteger valorCompra, Date dataCompra) {
        this.pkCompraVeiculo = pkCompraVeiculo;
        this.valorCompra = valorCompra;
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

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Veiculo getFkProducto() {
        return fkProducto;
    }

    public void setFkProducto(Veiculo fkProducto) {
        this.fkProducto = fkProducto;
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
        if (!(object instanceof Precario)) {
            return false;
        }
        Precario other = (Precario) object;
        if ((this.pkCompraVeiculo == null && other.pkCompraVeiculo != null) || (this.pkCompraVeiculo != null && !this.pkCompraVeiculo.equals(other.pkCompraVeiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.Precario[ pkCompraVeiculo=" + pkCompraVeiculo + " ]";
    }
    
}
