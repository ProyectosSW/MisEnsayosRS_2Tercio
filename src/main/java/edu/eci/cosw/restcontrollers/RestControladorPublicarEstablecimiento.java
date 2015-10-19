/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.restcontrollers;

import edu.eci.cosw.persistencia.Establecimiento;
import edu.eci.cosw.logica.Logica;
import edu.eci.cosw.persistencia.Sala;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author usuario
 */
@RestController
@RequestMapping("/establecimientos")
public class RestControladorPublicarEstablecimiento {

    @Autowired
    Logica logica;     
    
    /**
     * 
     * @param id identificacion del establecimiento solicitado
     * @return establecimiento solicitado
     */
    @RequestMapping(value="/{id}",method = RequestMethod.GET)        
    public Establecimiento consultaEstablecimientoId(@PathVariable int id) {  
        return logica.consultarEstablecimiento(id);
    }    
    
    /**
     * 
     * @return lista con todos los establecimientos registrados
     */
    @RequestMapping(value="/todos",method = RequestMethod.GET)        
    public List<Establecimiento> consultarTodosEstablecimientos() {  
        return logica.consultarEstablecimientos();
    }
    
    /**
     * 
     * @return lista con todos los establecimientos registrados y habilitados
     */
    @RequestMapping(value="/habilitados",method = RequestMethod.GET)        
    public List<Establecimiento> consultarTodosEstablecimientosHabilitados() {  
        return logica.consultarEstablecimientosHabilitados();
    }    
    
    /**
     * 
     * @param nombre del establecimiento a habilitar
     * @return respuesta a la operacion realizada de habilitacion de establecimiento
     */
    @RequestMapping(value="/habilitar/{nombre}",method = RequestMethod.GET)        
    public ResponseEntity<?> habilitarEstablecimiento(@PathVariable String nombre) {  
        HttpStatus hs;
        String mens = "";
        try {
            logica.habilitarEstablecimiento(nombre);
            hs=HttpStatus.CREATED;
        } catch (Exception ex) {
            mens=ex.getMessage();
            hs=HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(mens,hs);
    }
    
    /**
     * 
     * @return lista de todos los establecimientos registrados y sin habilitar
     */
    @RequestMapping(value="/sinhabilitar",method = RequestMethod.GET)        
    public List<Establecimiento> consultarTodosEstablecimientosSinHabilitar() {  
        return logica.consultarEstablecimientosSinHabilitar();
    }        
    
    /**
     * 
     * @param e establecimiento a registrar
     * @return respuesta a la operacion realizada de registro de establecimiento
     */
    @RequestMapping(value="/",method = RequestMethod.POST)
    public ResponseEntity<?> registrarEstablecimiento(@RequestBody Establecimiento e){
        HttpStatus hs;
        String mens = "";
        try {
            logica.registrarEstablecimiento(e);
            hs=HttpStatus.CREATED;
        } catch (Exception ex) {
            mens=ex.getMessage();
            hs=HttpStatus.ALREADY_REPORTED;
        }
        return new ResponseEntity<>(mens,hs);
    }    
    
    /**
     * 
     * @param s sala a registrar
     * @return respuesta a la operacion realizada de registro de sala
     */
    @RequestMapping(value="/sala/",method = RequestMethod.POST)
    public ResponseEntity<?> registrarSala(@RequestBody Sala s){
        HttpStatus hs;
        String mens = "";
        try {
            logica.registrarSala(s);
            hs=HttpStatus.CREATED;
        } catch (OperationFailedException ex) {
            mens=ex.getMessage();
            hs=HttpStatus.ALREADY_REPORTED;
        }
        return new ResponseEntity<>(mens,hs);
    }
    
    @RequestMapping(value="/sala/{idsala}",method = RequestMethod.GET)
    public Sala registrarSala(@RequestBody int idsala){
        return logica.consultarSala(idsala);
    }
    
}