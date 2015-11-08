/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.stubs;


/**
 *
 * @author usuario
 */
public class CamaraComercioStub implements CamaraComercio{
    public static final int size=13;
    @Override
    public boolean verificarEmpresa(String nit) {
        return (nit.length()>=13 && Integer.parseInt(nit.substring(0, 3))%2==0);
    }

}
