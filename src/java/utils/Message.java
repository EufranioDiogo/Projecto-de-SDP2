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
 * @author hermann
 */
public class Message {

    public static void setMessageError(String message) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, "");
        FacesContext.getCurrentInstance().addMessage("mensagem", facesMessage);

    }

    public static void setMessageInfo(String message) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, message, "");
        FacesContext.getCurrentInstance().addMessage("mensagem", facesMessage);
    }

    public static void setMessageWar(String message) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, " Mensagem de Warning ", message);
        FacesContext.getCurrentInstance().addMessage("mensagem", facesMessage);
    }

}
