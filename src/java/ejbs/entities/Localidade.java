/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dm
 */
@Entity
@Table(catalog = "bilhete_identidade", schema = "public")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Localidade.findAll", query = "SELECT l FROM Localidade l"),
    @NamedQuery(name = "Localidade.findByPkLocalidade", query = "SELECT l FROM Localidade l WHERE l.pkLocalidade = :pkLocalidade"),
    @NamedQuery(name = "Localidade.findByNome", query = "SELECT l FROM Localidade l WHERE l.nome = :nome")
})
public class Localidade implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_localidade", nullable = false)
    private Integer pkLocalidade;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String nome;
    @OneToMany(mappedBy = "fkLocalidadePai")
    private List<Localidade> localidadeList;
    @JoinColumn(name = "fk_localidade_pai", referencedColumnName = "pk_localidade")
    @ManyToOne
    private Localidade fkLocalidadePai;
    @OneToMany(mappedBy = "fkLocalidade")
    private List<BilheteIdentidade> bilheteIdentidadeList;

    public Localidade ()
    {
    }

    public Localidade (Integer pkLocalidade)
    {
        this.pkLocalidade = pkLocalidade;
    }

    public Integer getPkLocalidade ()
    {
        return pkLocalidade;
    }

    public void setPkLocalidade (Integer pkLocalidade)
    {
        this.pkLocalidade = pkLocalidade;
    }

    public String getNome ()
    {
        return nome;
    }

    public void setNome (String nome)
    {
        this.nome = nome;
    }

    @XmlTransient
    public List<Localidade> getLocalidadeList ()
    {
        return localidadeList;
    }

    public void setLocalidadeList (List<Localidade> localidadeList)
    {
        this.localidadeList = localidadeList;
    }

    public Localidade getFkLocalidadePai ()
    {
        return fkLocalidadePai;
    }

    public void setFkLocalidadePai (Localidade fkLocalidadePai)
    {
        this.fkLocalidadePai = fkLocalidadePai;
    }

    @XmlTransient
    public List<BilheteIdentidade> getBilheteIdentidadeList ()
    {
        return bilheteIdentidadeList;
    }

    public void setBilheteIdentidadeList (List<BilheteIdentidade> bilheteIdentidadeList)
    {
        this.bilheteIdentidadeList = bilheteIdentidadeList;
    }

    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (pkLocalidade != null ? pkLocalidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Localidade))
        {
            return false;
        }
        Localidade other = (Localidade) object;
        if ((this.pkLocalidade == null && other.pkLocalidade != null) || (this.pkLocalidade != null && !this.pkLocalidade.equals(other.pkLocalidade)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString ()
    {
        return "ejbs.entities.Localidade[ pkLocalidade=" + pkLocalidade + " ]";
    }
    
}
