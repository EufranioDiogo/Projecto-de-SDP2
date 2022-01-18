/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ed
 */
@Entity
@Table(catalog = "ucandb", schema = "POSTGRES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e"),
    @NamedQuery(name = "Endereco.findByPkEndereco", query = "SELECT e FROM Endereco e WHERE e.pkEndereco = :pkEndereco"),
    @NamedQuery(name = "Endereco.findByNumeroCasa", query = "SELECT e FROM Endereco e WHERE e.numeroCasa = :numeroCasa"),
    @NamedQuery(name = "Endereco.findByRua", query = "SELECT e FROM Endereco e WHERE e.rua = :rua")})
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_endereco", nullable = false)
    private Integer pkEndereco;
    @Size(max = 2147483647)
    @Column(name = "numero_casa", length = 2147483647)
    private String numeroCasa;
    @Size(max = 200)
    @Column(length = 200)
    private String rua;
    @JoinColumn(name = "fk_localidade", referencedColumnName = "pk_localidade", nullable = false)
    @ManyToOne(optional = false)
    private Localidade fkLocalidade;
    @JoinColumn(name = "fk_pessoa", referencedColumnName = "pk_pessoa", nullable = false)
    @ManyToOne(optional = false)
    private Pessoa fkPessoa;

    public Endereco() {
    }

    public Endereco(Integer pkEndereco) {
        this.pkEndereco = pkEndereco;
    }

    public Integer getPkEndereco() {
        return pkEndereco;
    }

    public void setPkEndereco(Integer pkEndereco) {
        this.pkEndereco = pkEndereco;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Localidade getFkLocalidade() {
        return fkLocalidade;
    }

    public void setFkLocalidade(Localidade fkLocalidade) {
        this.fkLocalidade = fkLocalidade;
    }

    public Pessoa getFkPessoa() {
        return fkPessoa;
    }

    public void setFkPessoa(Pessoa fkPessoa) {
        this.fkPessoa = fkPessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkEndereco != null ? pkEndereco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) object;
        if ((this.pkEndereco == null && other.pkEndereco != null) || (this.pkEndereco != null && !this.pkEndereco.equals(other.pkEndereco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.Endereco[ pkEndereco=" + pkEndereco + " ]";
    }
    
}
