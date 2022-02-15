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
@Table(catalog = "ucandb", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Veiculo.findAll", query = "SELECT v FROM Veiculo v"),
    @NamedQuery(name = "Veiculo.findByPkVeiculo", query = "SELECT v FROM Veiculo v WHERE v.pkVeiculo = :pkVeiculo"),
    @NamedQuery(name = "Veiculo.findByUrlImagem", query = "SELECT v FROM Veiculo v WHERE v.urlImagem = :urlImagem"),
    @NamedQuery(name = "Veiculo.findByDescricao", query = "SELECT v FROM Veiculo v WHERE v.descricao = :descricao")})
public class Veiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_veiculo", nullable = false)
    private Integer pkVeiculo;
    @Size(max = 250)
    @Column(name = "url_imagem", length = 250)
    private String urlImagem;
    @Size(max = 200)
    @Column(length = 200)
    private String descricao;
    @JoinColumn(name = "fk_tipo_veiculo", referencedColumnName = "pk_portfolio", nullable = false)
    @ManyToOne(optional = false)
    private Portfolio fkTipoVeiculo;
    @JoinColumn(name = "fk_stock", referencedColumnName = "pk_stock", nullable = false)
    @ManyToOne(optional = false)
    private Stock fkStock;

    public Veiculo() {
    }

    public Veiculo(Integer pkVeiculo) {
        this.pkVeiculo = pkVeiculo;
    }

    public Integer getPkVeiculo() {
        return pkVeiculo;
    }

    public void setPkVeiculo(Integer pkVeiculo) {
        this.pkVeiculo = pkVeiculo;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Portfolio getFkTipoVeiculo() {
        return fkTipoVeiculo;
    }

    public void setFkTipoVeiculo(Portfolio fkTipoVeiculo) {
        this.fkTipoVeiculo = fkTipoVeiculo;
    }

    public Stock getFkStock() {
        return fkStock;
    }

    public void setFkStock(Stock fkStock) {
        this.fkStock = fkStock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkVeiculo != null ? pkVeiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Veiculo)) {
            return false;
        }
        Veiculo other = (Veiculo) object;
        if ((this.pkVeiculo == null && other.pkVeiculo != null) || (this.pkVeiculo != null && !this.pkVeiculo.equals(other.pkVeiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.Veiculo[ pkVeiculo=" + pkVeiculo + " ]";
    }
    
}
