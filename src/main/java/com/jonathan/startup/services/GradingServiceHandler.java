package com.jonathan.startup.services;

/**
 *
 * @author Jonathan Kok
 */
public abstract class GradingServiceHandler {
    public GradingServiceHandler successor;
    
    public void setSuccessor(GradingServiceHandler successor){
        this.successor = successor;
    }
    
    public abstract String handleRequest(int request);
}
