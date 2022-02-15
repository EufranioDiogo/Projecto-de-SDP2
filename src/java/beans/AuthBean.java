/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.Conta;
import ejbs.facades.ContaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import utils.Mensagem;

/**
 *
 * @author ed
 */
@Named(value = "authBean")
@SessionScoped
public class AuthBean implements Serializable {

    /**
     * Creates a new instance of AuthBean
     */
    private String username;
    private String password;
    private Conta conta;

    @EJB
    private ContaFacade contaFacade;

    public AuthBean() {
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

    public String verifyAuthenticity() {
        try {
            this.conta = this.contaFacade.findAccount(username, password).get(0);
            return "home.xhtml?faces-redirect=true";
        } catch (Exception e) {
            Mensagem.erroMsg("Login Errado");
        }
        return "login.xhtml?faces-redirect=true";
    }
    
    public void autoSignin() {
        try {
            if (this.conta != null) {
                FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "home.xhtml?faces-redirect=true");
            }
        } catch (Exception e) {
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        this.username = "";
        this.password = "";
        this.conta = new Conta();
        return "login.xhtml?faces-redirect=true";
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    
}
