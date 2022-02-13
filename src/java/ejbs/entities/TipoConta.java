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
@Table(name = "tipo_conta", catalog = "ucandb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoConta.findAll", query = "SELECT t FROM TipoConta t"),
    @NamedQuery(name = "TipoConta.findByPkTipoConta", query = "SELECT t FROM TipoConta t WHERE t.pkTipoConta = :pkTipoConta"),
    @NamedQuery(name = "TipoConta.findByDesignacao", query = "SELECT t FROM TipoConta t WHERE t.designacao = :designacao")})
public class TipoConta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_tipo_conta", nullable = false)
    private Integer pkTipoConta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(nullable = false, length = 200)
    private String designacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkTipoConta")
    private List<Conta> contaList;

    public TipoConta() {
    }

    public TipoConta(Integer pkTipoConta) {
        this.pkTipoConta = pkTipoConta;
    }

    public TipoConta(Integer pkTipoConta, String designacao) {
        this.pkTipoConta = pkTipoConta;
        this.designacao = designacao;
    }

    public Integer getPkTipoConta() {
        return pkTipoConta;
    }

    public void setPkTipoConta(Integer pkTipoConta) {
        this.pkTipoConta = pkTipoConta;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    @XmlTransient
    public List<Conta> getContaList() {
        return contaList;
    }

    public void setContaList(List<Conta> contaList) {
        this.contaList = contaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkTipoConta != null ? pkTipoConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoConta)) {
            return false;
        }
        TipoConta other = (TipoConta) object;
        if ((this.pkTipoConta == null && other.pkTipoConta != null) || (this.pkTipoConta != null && !this.pkTipoConta.equals(other.pkTipoConta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.TipoConta[ pkTipoConta=" + pkTipoConta + " ]";
    }
    
}
