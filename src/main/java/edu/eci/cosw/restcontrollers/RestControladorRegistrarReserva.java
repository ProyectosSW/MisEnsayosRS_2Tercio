/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.restcontrollers;

import edu.eci.cosw.logica.Logica;
import edu.eci.cosw.persistencia.Reservacion;
import java.util.Date;
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
 * @author camiloandres
 */
@RestController
@RequestMapping("/reservacion")
public class RestControladorRegistrarReserva {
    @Autowired
    Logica logica;
        
    /*
    @RequestMapping(value="/",method = RequestMethod.POST)
    public ResponseEntity<?> registrarReserva(@RequestBody Reservacion r){
        HttpStatus hs;
        String mens = "";
        try {
            logica.registrarReserva(r.getSala().getEstablecimiento().getIdEstablecimiento(),r.getSala().getIdSala(),r.getFecha(),r.getTiempo());
            hs=HttpStatus.CREATED;
        } catch (Exception ex) {
            mens=ex.getMessage();
            hs=HttpStatus.ALREADY_REPORTED;
        }
        //retorna el estado 201 en caso de que la operación haya sido exitosa
        return new ResponseEntity<>(mens,hs);
    }*/
    
    @RequestMapping(value="/",method = RequestMethod.POST)
    public ResponseEntity<?> registrarCalificacionEstablecimiento(@RequestBody int idEstablecimiento , @RequestBody int idSala, @RequestBody Date fecha, @RequestBody int duracion){
        logica.registrarReserva(idEstablecimiento,idSala,fecha,duracion);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/",method = RequestMethod.POST)        
    public ResponseEntity<?> registrarAlquilerCliente(@PathVariable int idCliente,@PathVariable int idReserva,@PathVariable String descripcion) {  
        logica.crearEnsayoAlquiler(idCliente, idReserva, descripcion);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/cliente",method = RequestMethod.GET)
    public ResponseEntity<?> consultarReservasCliente(@PathVariable int idCliente) { 
        List l=logica.consultarReservasPorCliente(idCliente);
        return new ResponseEntity<>(l,HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value="/",method = RequestMethod.POST)        
    public ResponseEntity<?> realizarPago(@PathVariable int idAlquiler,@PathVariable int monto,@PathVariable String numtarjeta, @PathVariable String tipoP) {  
        HttpStatus status;
        String message;
        if(logica.realizarPago(idAlquiler, monto, numtarjeta, tipoP)){
            status=HttpStatus.ACCEPTED;
            message="Solicitud de pago aceptada";
        }else{
            status=HttpStatus.CONFLICT;
            message="Solicitud de pago rechazada, error de tarjeta de credito o insuficiencia de pago";
        }        
        return new ResponseEntity<>(message,status);
    }
    
}
