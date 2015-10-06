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

    @Override
    public boolean verificarEmpresa(int nit) {
        return (nit%2==0 && nit>0 && ((nit+"").length()==9 || (nit+"").length()==10));
    }

}
