package com.mercadolibre.ejerciciomutante.domain;

/**
 * 
 * @author john.ramirez
 *
 * @param <T>
 */
public class Response {

    private int status;
    private String userMessage;

    public Response(int status, String userMessage) {
        super();
        this.status = status;
        this.userMessage = userMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

}
