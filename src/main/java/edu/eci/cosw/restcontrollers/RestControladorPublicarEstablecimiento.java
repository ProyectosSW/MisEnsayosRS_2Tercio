/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.restcontrollers;

import edu.eci.cosw.persistencia.Establecimiento;
import edu.eci.cosw.logica.Logica;
import edu.eci.cosw.persistencia.Sala;
import java.util.ArrayList;
import java.util.Arrays;
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
     * @param nombrex
     * @return 
     */
    @RequestMapping(value="/nombre/{nombrex}",method = RequestMethod.GET)        
    public List<Establecimiento> consultarEstablecimientosNombre(@PathVariable String nombrex) {  
        return logica.consultarEstablecimientosNombre(nombrex);
    }    
    
    /**
     * 
     * @param localidadx
     * @return 
     */
    @RequestMapping(value="/localidad/{localidadx}",method = RequestMethod.GET)        
    public List<Establecimiento> consultarEstablecimientosLocalidad(@PathVariable String localidadx) {  
        return logica.consultarEstablecimientosLocalidad(localidadx);
    }
    
    /**
     * 
     * @return Lista de localidades reconocidas en la Base de datos
     */
    @RequestMapping(value="/localidad/nombres",method = RequestMethod.GET)
    public List<String> consultarLocalidades(){
        return Arrays.asList("Usaquen", "Chapinero", "Santa Fe", "San Cristobal", "Usme", "Tunjuelito", "Bosa", "Kennedy", "Fontibon", "Engativa", "Suba", "Barrios Unidos", "Teusaquillo", "Los Martires", "Antonio NariÃ±o", "Puente Aranda", "La Candelaria", "Rafael Uribe Uribe", "Ciudad Bolivar", "Sumapaz");
    }    
    
    /**
     * 
     * @param nitx
     * @return 
     */
    @RequestMapping(value="/nit/{nitx}",method = RequestMethod.GET)        
    public Establecimiento consultarEstablecimientosporNit(@PathVariable String nitx){
        Establecimiento es=null;
        if(nitx.length()>13) es=logica.consultarEstablecimientosporNit(nitx.substring(0, 13));
        return es;
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
    @RequestMapping(value="/inhabilitados/{id}",method = RequestMethod.GET)        
    public ResponseEntity<?> habilitarEstablecimiento(@PathVariable int id) {  
        HttpStatus hs;
        String mens = "";
        try {
            logica.habilitarEstablecimiento(id);
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
    @RequestMapping(value="/inhabilitados",method = RequestMethod.GET)        
    public List<Establecimiento> consultarTodosEstablecimientosSinHabilitar() {  
        return logica.consultarEstablecimientosSinHabilitar();
    }  
    
    /**
     * 
     * @param id
     * @return 
     */
    @RequestMapping(value="/sala/grupo/{id}",method = RequestMethod.GET)        
    public List<Sala> consultarSalaPorEstablecimiento(@PathVariable int id) {  
        return logica.consultarSalaPorEstablecimiento(id);
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
            e.setNit(e.getNit()+"revisar");
            logica.registrarEstablecimiento(e);
            hs=HttpStatus.CREATED;
        } catch (Exception ex) {
            mens=ex.getMessage();
            hs=HttpStatus.BAD_REQUEST;
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
            hs=HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(mens,hs);
    }
    
    /**
     * 
     * @param idsala
     * @return 
     */
    @RequestMapping(value="/sala/consulta/{idsala}",method = RequestMethod.GET)
    public Sala consultarSala(@PathVariable int idsala){
        return logica.consultarSala(idsala);
    }
    
    /**
     * 
     * @return 
     */
    @RequestMapping(value="/prueba",method = RequestMethod.GET)
    public int consultaPrueba(){
        //Establecimiento e = new Establecimiento(1, "Establecimiento 1", "101.101.101-1", "Fundado en el año 2000","Calle 100 # 19-102", 700, 1900, 2.4, "Usaquen", "1010101",new HashSet<Instrumento>(), new HashSet<Sala>());
        //Sala a = new Sala(10, e, "30000", "Sala VIP", "Sala 10", new HashSet<Reservacion>());
        return logica.consultarCantidadSalas();
    }
    
    /**
     * 
     * @return 
     */
    @RequestMapping(value="/cantidad",method = RequestMethod.GET)
    public int consultarCantidadEstablcimientos(){
        return logica.consultarCantidadEstablcimientos();
    }
    
    /**
     * 
     * @return 
     */
    @RequestMapping(value="/sala/cantidad",method = RequestMethod.GET)
    public int consultarCantidadSalas(){
        return logica.consultarCantidadSalas();
    }
    
}