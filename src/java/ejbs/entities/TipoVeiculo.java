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
@Table(name = "tipo_veiculo", catalog = "ucandb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoVeiculo.findAll", query = "SELECT t FROM TipoVeiculo t"),
    @NamedQuery(name = "TipoVeiculo.findByPkTipoVeiculo", query = "SELECT t FROM TipoVeiculo t WHERE t.pkTipoVeiculo = :pkTipoVeiculo"),
    @NamedQuery(name = "TipoVeiculo.findByDesignacao", query = "SELECT t FROM TipoVeiculo t WHERE t.designacao = :designacao")})
public class TipoVeiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_tipo_veiculo", nullable = false)
    private Integer pkTipoVeiculo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(nullable = false, length = 200)
    private String designacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkTipoVeiculo")
    private List<Veiculo> veiculoList;

    public TipoVeiculo() {
    }

    public TipoVeiculo(Integer pkTipoVeiculo) {
        this.pkTipoVeiculo = pkTipoVeiculo;
    }

    public TipoVeiculo(Integer pkTipoVeiculo, String designacao) {
        this.pkTipoVeiculo = pkTipoVeiculo;
        this.designacao = designacao;
    }

    public Integer getPkTipoVeiculo() {
        return pkTipoVeiculo;
    }

    public void setPkTipoVeiculo(Integer pkTipoVeiculo) {
        this.pkTipoVeiculo = pkTipoVeiculo;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    @XmlTransient
    public List<Veiculo> getVeiculoList() {
        return veiculoList;
    }

    public void setVeiculoList(List<Veiculo> veiculoList) {
        this.veiculoList = veiculoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkTipoVeiculo != null ? pkTipoVeiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoVeiculo)) {
            return false;
        }
        TipoVeiculo other = (TipoVeiculo) object;
        if ((this.pkTipoVeiculo == null && other.pkTipoVeiculo != null) || (this.pkTipoVeiculo != null && !this.pkTipoVeiculo.equals(other.pkTipoVeiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.TipoVeiculo[ pkTipoVeiculo=" + pkTipoVeiculo + " ]";
    }
    
}
