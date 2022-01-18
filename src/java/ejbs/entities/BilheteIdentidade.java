/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dm
 */
@Entity
@Table(name = "bilhete_identidade", catalog = "bilhete_identidade", schema = "public")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "BilheteIdentidade.findAll", query = "SELECT b FROM BilheteIdentidade b"),
    @NamedQuery(name = "BilheteIdentidade.findByNome", query = "SELECT b FROM BilheteIdentidade b WHERE b.nome = :nome"),
    @NamedQuery(name = "BilheteIdentidade.findByDataNascimento", query = "SELECT b FROM BilheteIdentidade b WHERE b.dataNascimento = :dataNascimento"),
    @NamedQuery(name = "BilheteIdentidade.findByPkBilheteIdentidade", query = "SELECT b FROM BilheteIdentidade b WHERE b.pkBilheteIdentidade = :pkBilheteIdentidade"),
    @NamedQuery(name = "BilheteIdentidade.findByNumeroIdentificacao", query = "SELECT b FROM BilheteIdentidade b WHERE b.numeroIdentificacao = :numeroIdentificacao")
})
public class BilheteIdentidade implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String nome;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNascimento;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_bilhete_identidade", nullable = false)
    private Integer pkBilheteIdentidade;
    @Size(max = 2147483647)
    @Column(name = "numero_identificacao", length = 2147483647)
    private String numeroIdentificacao;
    @JoinColumn(name = "fk_localidade", referencedColumnName = "pk_localidade")
    @ManyToOne
    private Localidade fkLocalidade;

    public BilheteIdentidade ()
    {
    }

    public BilheteIdentidade (Integer pkBilheteIdentidade)
    {
        this.pkBilheteIdentidade = pkBilheteIdentidade;
    }

    public String getNome ()
    {
        return nome;
    }

    public void setNome (String nome)
    {
        this.nome = nome;
    }

    public Date getDataNascimento ()
    {
        return dataNascimento;
    }

    public void setDataNascimento (Date dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }

    public Integer getPkBilheteIdentidade ()
    {
        return pkBilheteIdentidade;
    }

    public void setPkBilheteIdentidade (Integer pkBilheteIdentidade)
    {
        this.pkBilheteIdentidade = pkBilheteIdentidade;
    }

    public String getNumeroIdentificacao ()
    {
        return numeroIdentificacao;
    }

    public void setNumeroIdentificacao (String numeroIdentificacao)
    {
        this.numeroIdentificacao = numeroIdentificacao;
    }

    public Localidade getFkLocalidade ()
    {
        return fkLocalidade;
    }

    public void setFkLocalidade (Localidade fkLocalidade)
    {
        this.fkLocalidade = fkLocalidade;
    }

    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (pkBilheteIdentidade != null ? pkBilheteIdentidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BilheteIdentidade))
        {
            return false;
        }
        BilheteIdentidade other = (BilheteIdentidade) object;
        if ((this.pkBilheteIdentidade == null && other.pkBilheteIdentidade != null) || (this.pkBilheteIdentidade != null && !this.pkBilheteIdentidade.equals(other.pkBilheteIdentidade)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString ()
    {
        return "ejbs.entities.BilheteIdentidade[ pkBilheteIdentidade=" + pkBilheteIdentidade + " ]";
    }
    
}
