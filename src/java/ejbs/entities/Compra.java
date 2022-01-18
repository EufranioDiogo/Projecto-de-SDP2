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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(catalog = "ucandb", schema = "POSTGRES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findByPkCompra", query = "SELECT c FROM Compra c WHERE c.compraPK.pkCompra = :pkCompra"),
    @NamedQuery(name = "Compra.findByFkConta", query = "SELECT c FROM Compra c WHERE c.compraPK.fkConta = :fkConta"),
    @NamedQuery(name = "Compra.findByFkVeiculo", query = "SELECT c FROM Compra c WHERE c.compraPK.fkVeiculo = :fkVeiculo"),
    @NamedQuery(name = "Compra.findByQuantVeiculos", query = "SELECT c FROM Compra c WHERE c.quantVeiculos = :quantVeiculos"),
    @NamedQuery(name = "Compra.findByPrecoVeiculos", query = "SELECT c FROM Compra c WHERE c.precoVeiculos = :precoVeiculos"),
    @NamedQuery(name = "Compra.findByPrecoTotalCompra", query = "SELECT c FROM Compra c WHERE c.precoTotalCompra = :precoTotalCompra"),
    @NamedQuery(name = "Compra.findByDesconto", query = "SELECT c FROM Compra c WHERE c.desconto = :desconto"),
    @NamedQuery(name = "Compra.findByImposto", query = "SELECT c FROM Compra c WHERE c.imposto = :imposto"),
    @NamedQuery(name = "Compra.findByDataCompra", query = "SELECT c FROM Compra c WHERE c.dataCompra = :dataCompra")})
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompraPK compraPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quant_veiculos", nullable = false)
    private int quantVeiculos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preco_veiculos", nullable = false)
    private BigInteger precoVeiculos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preco_total_compra", nullable = false)
    private BigInteger precoTotalCompra;
    private BigInteger desconto;
    private BigInteger imposto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_compra", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCompra;
    @JoinColumn(name = "fk_conta", referencedColumnName = "pk_conta", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Conta conta;
    @JoinColumn(name = "fk_veiculo", referencedColumnName = "pk_veiculo", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Veiculo veiculo;

    public Compra() {
    }

    public Compra(CompraPK compraPK) {
        this.compraPK = compraPK;
    }

    public Compra(CompraPK compraPK, int quantVeiculos, BigInteger precoVeiculos, BigInteger precoTotalCompra, Date dataCompra) {
        this.compraPK = compraPK;
        this.quantVeiculos = quantVeiculos;
        this.precoVeiculos = precoVeiculos;
        this.precoTotalCompra = precoTotalCompra;
        this.dataCompra = dataCompra;
    }

    public Compra(int pkCompra, int fkConta, int fkVeiculo) {
        this.compraPK = new CompraPK(pkCompra, fkConta, fkVeiculo);
    }

    public CompraPK getCompraPK() {
        return compraPK;
    }

    public void setCompraPK(CompraPK compraPK) {
        this.compraPK = compraPK;
    }

    public int getQuantVeiculos() {
        return quantVeiculos;
    }

    public void setQuantVeiculos(int quantVeiculos) {
        this.quantVeiculos = quantVeiculos;
    }

    public BigInteger getPrecoVeiculos() {
        return precoVeiculos;
    }

    public void setPrecoVeiculos(BigInteger precoVeiculos) {
        this.precoVeiculos = precoVeiculos;
    }

    public BigInteger getPrecoTotalCompra() {
        return precoTotalCompra;
    }

    public void setPrecoTotalCompra(BigInteger precoTotalCompra) {
        this.precoTotalCompra = precoTotalCompra;
    }

    public BigInteger getDesconto() {
        return desconto;
    }

    public void setDesconto(BigInteger desconto) {
        this.desconto = desconto;
    }

    public BigInteger getImposto() {
        return imposto;
    }

    public void setImposto(BigInteger imposto) {
        this.imposto = imposto;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compraPK != null ? compraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.compraPK == null && other.compraPK != null) || (this.compraPK != null && !this.compraPK.equals(other.compraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.Compra[ compraPK=" + compraPK + " ]";
    }
    
}
