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
public interface CamaraComercio {

    /**
     * Verificar el registro de una empresa ante Cámara de Comercio
     * @param nit número de identificación tributaria de la empresa
     * @return true si la empresa esta resgistrada, false si no
     */
    public boolean verificarEmpresa(String nit);
}
