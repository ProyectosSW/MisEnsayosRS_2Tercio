/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.stubs;

/**
 *
 * @author 2090903
 */
public class Pago implements java.io.Serializable{
    
    private int idAlquiler;
    private int monto;
    private String numtarjeta;
    private String tipoP;

    public Pago(){
        
    }
    
    public Pago(int idAlquiler, int monto, String numtarjeta, String tipoP){
        this.idAlquiler=idAlquiler;
        this.monto=monto;
        this.numtarjeta=numtarjeta;
        this.tipoP=tipoP;
    }
    
    /**
     * @return the idAlquiler
     */
    public int getIdAlquiler(){
        return idAlquiler;
    }

    /**
     * @param idAlquiler the idAlquiler to set
     */
    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    /**
     * @return the monto
     */
    public int getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(int monto) {
        this.monto = monto;
    }

    /**
     * @return the numtarjeta
     */
    public String getNumtarjeta() {
        return numtarjeta;
    }

    /**
     * @param numtarjeta the numtarjeta to set
     */
    public void setNumtarjeta(String numtarjeta) {
        this.numtarjeta = numtarjeta;
    }

    /**
     * @return the tipoP
     */
    public String getTipoP() {
        return tipoP;
    }

    /**
     * @param tipoP the tipoP to set
     */
    public void setTipoP(String tipoP) {
        this.tipoP = tipoP;
    }
    
    
}
