/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.restcontrollers;

import edu.eci.cosw.logica.Logica;
import edu.eci.cosw.stubs.Transaccion;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author usuario
 */
@RestController
@RequestMapping("/stubs")
public class RestControladorStubs {
    
    @Autowired
    Logica logica;     
    
    /**
     * 
     * @param nit
     * @return true en caso de que el establecimiento se encuentre registrado ante la Cámara de comercio y false en caso contrario.
     */
    @RequestMapping(value="/camaracomercio/{nit}",method = RequestMethod.GET)        
    public boolean verificarlegalidad(@PathVariable String nit) {  
        RestTemplate restTemplate = new RestTemplate();
        String nie = (nit.length()>13)?nit.substring(0, 13):nit;
        Map<String, String> vars = new HashMap<>();
        vars.put("nit", nie+nie);
        return restTemplate.getForObject("https://damp-mesa-1375.herokuapp.com/rest/stubs/camaracomercio/externo/{nit}", Boolean.class, vars);
    }
    
    /**
     * 
     * @param nit
     * @return true en caso de que el establecimiento se encuentre registrado ante la Cámara de comercio y false en caso contrario.
     */
    @RequestMapping(value="/camaracomercio/externo/{nit}",method = RequestMethod.GET)        
    public boolean verificarlegalidadexterno(@PathVariable String nit) {  
        return (nit.length()>13)?logica.verificarEmpresa(nit.substring(0, 13)):logica.verificarEmpresa(nit);
    } 
    
    /**
     * 
     * @param tarjeta
     * @return true en caso de que el pago se haya logrado realizar y false en caso contrario.
     */
    @RequestMapping(value="/pasarelapagos",method = RequestMethod.POST)        
    public boolean realizarPago(@RequestBody Transaccion tarjeta) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("https://damp-mesa-1375.herokuapp.com/rest/stubs/pasarelapagos/externo", tarjeta, Boolean.class);
    }
    
    /**
     * 
     * @param tarjeta
     * @return true en caso de que el pago se haya logrado realizar y false en caso contrario.
     */
    @RequestMapping(value="/pasarelapagos/externo",method = RequestMethod.POST)        
    public boolean realizarPagoExterno(@RequestBody Transaccion trs) {
        return logica.realizarPagoExterno(trs);
    }
    
}
