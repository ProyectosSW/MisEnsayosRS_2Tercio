/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.restcontrollers;

import edu.eci.cosw.logica.Logica;
import edu.eci.cosw.persistencia.Alquiler;
import edu.eci.cosw.persistencia.Calificacion;
import edu.eci.cosw.persistencia.Cliente;
import edu.eci.cosw.persistencia.Ensayo;
import edu.eci.cosw.persistencia.Establecimiento;
import edu.eci.cosw.persistencia.Instrumento;
import edu.eci.cosw.stubs.DetalleCalificacion;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LuisCarlos
 */ 
@RestController
@RequestMapping("/calificacion")
public class RestControladorCalificarEstablecimiento {
    
    @Autowired
    Logica logica;

    @RequestMapping(value="/",method = RequestMethod.POST)
    public ResponseEntity<?> registrarCalificacionEstablecimiento(@RequestBody Calificacion calificacion ){
        logica.calificarEstablecimiento(calificacion.getEnsayo().getCliente().getIdCliente(),calificacion.getEnsayo().getIdEnsayo(), calificacion.getCalificacionEstablecimiento(), calificacion.getDescripcion());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/ensayo/{idensayo}",method = RequestMethod.GET)        
    public Ensayo consultarTodosEstablecimientos(@PathVariable int idensayo) {  
        return logica.consultarEnsayo(idensayo);
    }
    
    
    @RequestMapping(value="/prueba/ensayo",method = RequestMethod.GET)        
    public Calificacion prueba() {  
        Cliente c1=new Cliente(123, "pepe", "hola soy prueba", new HashSet<Ensayo>());
        Ensayo ensay=new Ensayo(1, c1,"prueba1", new Date(5), new HashSet<Calificacion>(), new HashSet<Instrumento>(), new HashSet<Alquiler>());
        Calificacion cali= new Calificacion(1, ensay, 2, 5, "Suenan mal y cagan el establecimiento");
        return cali;
    }
    
     @RequestMapping(value="/ensayo/cliente/{idcliente}",method = RequestMethod.GET)
     public List<Ensayo> EstablecimientosEnsayados(@PathVariable int idcliente){
        return logica.EstablecimientosEnsayados(idcliente);
    }
      @RequestMapping(value="/ensayo/establecimiento/{idEstablecimiento}",method = RequestMethod.GET)
     public List<Ensayo> EnsayosEstablecimientos(@PathVariable int idEstablecimiento ){
        return logica.ClientesEstablecimiento(idEstablecimiento);
    }
     
}
