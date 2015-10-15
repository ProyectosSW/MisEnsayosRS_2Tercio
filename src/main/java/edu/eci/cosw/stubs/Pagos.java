/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.stubs;

import edu.eci.cosw.persistencia.Alquiler;

/**
 *
 * @author camiloandres
 */
public interface Pagos {
    
    /**
     * @obj se realiza el pago de un alquiler especificado
     * @param numTarjeta numero de tarjeta de credito a verificar
     */
    public boolean realizarPago( String numTarjeta);
}
