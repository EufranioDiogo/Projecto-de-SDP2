/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Canga
 */
public class Mensagem
{

    public static void sucessoMsg(String mensagem)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, "Operação Realizada Com Sucesso!"));
    }

    public static void erroMsg(String mensagem)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "Erro! Operação Não Realizada!"));
    }

    public static void warnMsg(String mensagem)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, "Erro! Operação Não Realizada!"));
    }

    public static void fatalMsg(String mensagem)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, mensagem, "Operação Realizada Com Sucesso!"));
    }

    public void addMessage(String summary, String detail)
    {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
