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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ed
 */
@Entity
@Table(catalog = "ucandb", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"}),
    @UniqueConstraint(columnNames = {"username"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conta.findAll", query = "SELECT c FROM Conta c"),
    @NamedQuery(name = "Conta.findByPkConta", query = "SELECT c FROM Conta c WHERE c.pkConta = :pkConta"),
    @NamedQuery(name = "Conta.findByUsername", query = "SELECT c FROM Conta c WHERE c.username = :username"),
    @NamedQuery(name = "Conta.findByPassword", query = "SELECT c FROM Conta c WHERE c.password = :password"),
    @NamedQuery(name = "Conta.findByEmail", query = "SELECT c FROM Conta c WHERE c.email = :email")})
public class Conta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_conta", nullable = false)
    private Integer pkConta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(nullable = false, length = 200)
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(nullable = false, length = 250)
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(nullable = false, length = 200)
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conta")
    private List<Compra> compraList;
    @JoinColumn(name = "fk_pessoa", referencedColumnName = "pk_pessoa", nullable = false)
    @ManyToOne(optional = false)
    private Pessoa fkPessoa;
    @JoinColumn(name = "fk_tipo_conta", referencedColumnName = "pk_tipo_conta", nullable = false)
    @ManyToOne(optional = false)
    private TipoConta fkTipoConta;

    public Conta() {
    }

    public Conta(Integer pkConta) {
        this.pkConta = pkConta;
    }

    public Conta(Integer pkConta, String username, String password, String email) {
        this.pkConta = pkConta;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Integer getPkConta() {
        return pkConta;
    }

    public void setPkConta(Integer pkConta) {
        this.pkConta = pkConta;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }

    public Pessoa getFkPessoa() {
        return fkPessoa;
    }

    public void setFkPessoa(Pessoa fkPessoa) {
        this.fkPessoa = fkPessoa;
    }

    public TipoConta getFkTipoConta() {
        return fkTipoConta;
    }

    public void setFkTipoConta(TipoConta fkTipoConta) {
        this.fkTipoConta = fkTipoConta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkConta != null ? pkConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conta)) {
            return false;
        }
        Conta other = (Conta) object;
        if ((this.pkConta == null && other.pkConta != null) || (this.pkConta != null && !this.pkConta.equals(other.pkConta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejbs.entities.Conta[ pkConta=" + pkConta + " ]";
    }
    
}
