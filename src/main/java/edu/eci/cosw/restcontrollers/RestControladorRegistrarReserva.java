/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.restcontrollers;

import edu.eci.cosw.logica.Logica;
import edu.eci.cosw.persistencia.Alquiler;
import edu.eci.cosw.persistencia.Cliente;
import edu.eci.cosw.persistencia.Establecimiento;
import edu.eci.cosw.persistencia.Instrumento;
import edu.eci.cosw.persistencia.Reservacion;
import edu.eci.cosw.persistencia.Sala;
import edu.eci.cosw.stubs.Pago;
import java.sql.Time;
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
 * @author camiloandres
 */
@RestController
@RequestMapping("/reservacion")
public class RestControladorRegistrarReserva {
    @Autowired
    Logica logica;
        
    
    @RequestMapping(value="/registroreserv",method = RequestMethod.POST)
    public ResponseEntity<?> registrarReserva(@RequestBody Reservacion r){
        HttpStatus hs;
        String mens = "";
        try {
            logica.registrarReserva(r.getSala().getEstablecimiento().getIdEstablecimiento(),r.getSala().getIdSala(),r.getFecha(), r.getHora(),r.getTiempo());
            hs=HttpStatus.CREATED;
        } catch (Exception ex) {
            mens=ex.getMessage();
            hs=HttpStatus.ALREADY_REPORTED;
        }
        //retorna el estado 201 en caso de que la operación haya sido exitosa
        return new ResponseEntity<>(mens,hs);
    }
    /*
    @RequestMapping(value="/registroReserv",method = RequestMethod.POST)
    public ResponseEntity<?> registrarReserva(@RequestBody Reservacion r){
        logica.registrarReserva(idEstablecimiento,idSala,fecha,duracion);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }*/
    
    @RequestMapping(value="/alquilercliente",method = RequestMethod.POST)        
    public ResponseEntity<?> registrarAlquilerCliente(@RequestBody Alquiler a) {
        //Reservacion r=(Reservacion)consultarReservacion(a.getReservacion().getIdReservacion()).getBody();
        logica.crearEnsayoAlquiler(a.getEnsayo().getCliente().getIdCliente(), a.getReservacion(), a.getEnsayo().getDescripcion());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/clientereserva/{idCliente}",method = RequestMethod.GET)
    public List<Reservacion> consultarReservasCliente(@PathVariable int idCliente) { 
        return logica.consultarReservasPorCliente(idCliente);
    }
    
    @RequestMapping(value="/prueba",method = RequestMethod.GET)
    public Reservacion prueba() { 
        Establecimiento tds = new Establecimiento(1, "nombnre", "123.123.123-1", "dsfdssdhfsfd", "dfdff-- -- - -", 123, 2345, 32.4, "dsfdssdffsdfsd", "35535345", "28947323473283984724",new HashSet<Instrumento> (), new HashSet<Sala> ());
        Sala s =new Sala(2, tds, "dsfdsfds", "sadsaasdasdas", "dffsdfdss", new HashSet<Reservacion>());
        Reservacion d = new Reservacion(1, s, new Date(234), new Time(5), 43, new HashSet<Alquiler>());
        return d;
    }
    
    @RequestMapping(value="/pagoalquiler",method = RequestMethod.POST)        
    public ResponseEntity<?> pagarAlquiler(@RequestBody Pago p) {  
        HttpStatus status=HttpStatus.NOT_MODIFIED;
        String message="";
        //logica.realizarPago(p.getIdAlquiler(), p.getMonto(), p.getNumtarjeta(), p.getTipoP());
        if(logica.realizarPago(p.getIdAlquiler(), p.getMonto(), p.getNumtarjeta(), p.getTipoP())){
            status=HttpStatus.ACCEPTED;
            message="Solicitud de pago aceptada";
        }else{
            status=HttpStatus.CONFLICT;
            message="Solicitud de pago rechazada, error de tarjeta o insuficiencia de pago";
        }       
        return new ResponseEntity<>(message,status);
    }
    
    @RequestMapping(value="/cliente/{idCliente}",method = RequestMethod.GET)
    public ResponseEntity<?> consultarCliente(@PathVariable int idCliente) { 
        return new ResponseEntity<>(logica.consultarCliente(idCliente),HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value="/{idReserva}",method = RequestMethod.GET)
    public ResponseEntity<?> consultarReservacion(@PathVariable int idReserva) { 
        return new ResponseEntity<>(logica.consultarReservacion(idReserva),HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value="/cliente",method = RequestMethod.POST)        
    public ResponseEntity<?> registrarCliente(@RequestBody Cliente c) {  
        logica.registrarCliente(c);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/alquiler/{idAlquiler}",method = RequestMethod.GET)        
    public ResponseEntity<?> registrarCliente(@PathVariable int idAlquiler) {  
        return new ResponseEntity<>(logica.consultarAlquiler(idAlquiler),HttpStatus.CREATED);
    }
    
}
