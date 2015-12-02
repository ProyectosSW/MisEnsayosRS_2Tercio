/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.stubs;

/**
 *
 * @author camiloandres
 */
public class PagosStub implements Pagos{

    @Override
    public boolean realizarPago(String numTarjeta) {
        boolean r=true;
        if(numTarjeta.length()<15 || numTarjeta.length()>16){
            r=false;
        }
        return r;
    }

    @Override
    public boolean realizarPagoExterno(Transaccion tarjeta) {
        return (tarjeta.getMontoTransaccion()>0 && tarjeta.getCodigoSeguridad().length()==3 && tarjeta.getCuentaDestino().length()==16 && tarjeta.getNumeroTarjeta().length()==16);
    }
    
}
