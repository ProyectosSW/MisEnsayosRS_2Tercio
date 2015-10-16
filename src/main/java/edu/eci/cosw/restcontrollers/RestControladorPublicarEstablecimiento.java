/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.restcontrollers;

import edu.eci.cosw.persistencia.Establecimiento;
import edu.eci.cosw.logica.Logica;
import edu.eci.cosw.persistencia.Sala;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @RequestMapping(value="/{id}",method = RequestMethod.GET)        
    public ResponseEntity<?> consultaEstablecimientoId(@PathVariable int id) {  
        return new ResponseEntity<>("REST API working. Echo:"+id+"\n\n"+logica.consultarEstablecimiento(id),HttpStatus.ACCEPTED);
    }    
    
    @RequestMapping(value="/precio/{localidad}/{nombre}",method = RequestMethod.GET)        
    public ResponseEntity<?> consultaEstablecimientoPrecio(@PathVariable String nombre, @PathVariable String localidad) {  
        return new ResponseEntity<>("REST API working. Echo:"+nombre+" : "+localidad+"\n\n"+logica.consultarEstablecimientoPrecio(nombre, localidad),HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="/calificacion/{localidad}/{nombre}",method = RequestMethod.GET)        
    public ResponseEntity<?> consultaEstablecimientoCalficacion(@PathVariable String nombre, @PathVariable String localidad) {  
        return new ResponseEntity<>("REST API working. Echo:"+nombre+" : "+localidad+"\n\n"+logica.consultarEstablecimientoCalificacion(nombre, localidad),HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value="/",method = RequestMethod.GET)        
    public ResponseEntity<?> consultaPrueba() {  
        return new ResponseEntity<>("REST API working. Echo:",HttpStatus.ACCEPTED);
    }
    
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
        //retorna el estado 201 en caso de que la operación haya sido exitosa
        return new ResponseEntity<>(mens,hs);
    }    

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
        //retorna el estado 201 en caso de que la operación haya sido exitosa
        return new ResponseEntity<>(mens,hs);
    }        
    
}