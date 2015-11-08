/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.restcontrollers;

import edu.eci.cosw.logica.Logica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author usuario
 */
@RestController
@RequestMapping("/stubs")
public class RestControladorStubs {

    @Autowired
    Logica logica;     

    @RequestMapping(value="/camaracomercio/{nit}",method = RequestMethod.GET)        
    public boolean verificarlegalidad(@PathVariable String nit) {  
        return (nit.length()>13)?logica.verificarEmpresa(nit.substring(0, 13)):logica.verificarEmpresa(nit);
    }    
}
