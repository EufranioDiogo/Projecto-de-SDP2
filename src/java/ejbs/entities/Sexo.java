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
@Table(catalog = "ucandb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sexo.findAll", query = "SELECT s FROM Sexo s"),
    @NamedQuery(name = "Sexo.findByPkSexo", query = "SELECT s FROM Sexo s WHERE s.pkSexo = :pkSexo"),
    @NamedQuery(name = "Sexo.findByDesignacao", query = "SELECT s FROM Sexo s WHERE s.designacao = :designacao")})
public class Sexo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_sexo", nullable = false)
    private Integer pkSexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(nullable = false, length = 2)
    private String designacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSexo")
    private Collection<Pessoa> pessoaCollection;

    public Sexo() {
    }

    public Sexo(Integer pkSexo) {
        this.pkSexo = pkSexo;
    }

    public Sexo(Integer pkSexo, String designacao) {
        this.pkSexo = pkSexo;
        this.designacao = designacao;
    }

    public Integer getPkSexo() {
        return pkSexo;
    }

    public void setPkSexo(Integer pkSexo) {
        this.pkSexo = pkSexo;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    @XmlTransient
    public Collection<Pessoa> getPessoaCollection() {
        return pessoaCollection;
    }

    public void setPessoaCollection(Collection<Pessoa> pessoaCollection) {
        this.pessoaCollection = pessoaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkSexo != null ? pkSexo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sexo)) {
            return false;
        }
        Sexo other = (Sexo) object;
        if ((this.pkSexo == null && other.pkSexo != null) || (this.pkSexo != null && !this.pkSexo.equals(other.pkSexo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.Sexo[ pkSexo=" + pkSexo + " ]";
    }
    
}
