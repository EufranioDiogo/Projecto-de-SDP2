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
    @NamedQuery(name = "Localidade.findAll", query = "SELECT l FROM Localidade l"),
    @NamedQuery(name = "Localidade.findByPkLocalidade", query = "SELECT l FROM Localidade l WHERE l.pkLocalidade = :pkLocalidade"),
    @NamedQuery(name = "Localidade.findByDesignacao", query = "SELECT l FROM Localidade l WHERE l.designacao = :designacao"),
    @NamedQuery(name = "Localidade.findByFkLocalidade", query = "SELECT l FROM Localidade l WHERE l.fkLocalidade = :fkLocalidade")})
public class Localidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "pk_localidade", nullable = false, length = 250)
    private String pkLocalidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(nullable = false, length = 200)
    private String designacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fk_localidade", nullable = false)
    private int fkLocalidade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkLocalidade")
    private List<Endereco> enderecoList;

    public Localidade() {
    }

    public Localidade(String pkLocalidade) {
        this.pkLocalidade = pkLocalidade;
    }

    public Localidade(String pkLocalidade, String designacao, int fkLocalidade) {
        this.pkLocalidade = pkLocalidade;
        this.designacao = designacao;
        this.fkLocalidade = fkLocalidade;
    }

    public String getPkLocalidade() {
        return pkLocalidade;
    }

    public void setPkLocalidade(String pkLocalidade) {
        this.pkLocalidade = pkLocalidade;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public int getFkLocalidade() {
        return fkLocalidade;
    }

    public void setFkLocalidade(int fkLocalidade) {
        this.fkLocalidade = fkLocalidade;
    }

    @XmlTransient
    public List<Endereco> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(List<Endereco> enderecoList) {
        this.enderecoList = enderecoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkLocalidade != null ? pkLocalidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Localidade)) {
            return false;
        }
        Localidade other = (Localidade) object;
        if ((this.pkLocalidade == null && other.pkLocalidade != null) || (this.pkLocalidade != null && !this.pkLocalidade.equals(other.pkLocalidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.Localidade[ pkLocalidade=" + pkLocalidade + " ]";
    }
    
}
