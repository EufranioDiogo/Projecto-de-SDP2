/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ed
 */
@Embeddable
public class CompraPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_compra", nullable = false)
    private int pkCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fk_conta", nullable = false)
    private int fkConta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fk_veiculo", nullable = false)
    private int fkVeiculo;

    public CompraPK() {
    }

    public CompraPK(int pkCompra, int fkConta, int fkVeiculo) {
        this.pkCompra = pkCompra;
        this.fkConta = fkConta;
        this.fkVeiculo = fkVeiculo;
    }

    public int getPkCompra() {
        return pkCompra;
    }

    public void setPkCompra(int pkCompra) {
        this.pkCompra = pkCompra;
    }

    public int getFkConta() {
        return fkConta;
    }

    public void setFkConta(int fkConta) {
        this.fkConta = fkConta;
    }

    public int getFkVeiculo() {
        return fkVeiculo;
    }

    public void setFkVeiculo(int fkVeiculo) {
        this.fkVeiculo = fkVeiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pkCompra;
        hash += (int) fkConta;
        hash += (int) fkVeiculo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompraPK)) {
            return false;
        }
        CompraPK other = (CompraPK) object;
        if (this.pkCompra != other.pkCompra) {
            return false;
        }
        if (this.fkConta != other.fkConta) {
            return false;
        }
        if (this.fkVeiculo != other.fkVeiculo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.CompraPK[ pkCompra=" + pkCompra + ", fkConta=" + fkConta + ", fkVeiculo=" + fkVeiculo + " ]";
    }
    
}
