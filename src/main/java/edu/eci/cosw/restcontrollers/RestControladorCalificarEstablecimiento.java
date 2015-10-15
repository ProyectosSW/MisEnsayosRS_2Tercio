/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.restcontrollers;

import edu.eci.cosw.logica.Logica;
import edu.eci.cosw.persistencia.Establecimiento;
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
    public ResponseEntity<?> registrarCalificacionEstablecimiento(@RequestBody int idCliente , @RequestBody int idEnsayo, @RequestBody int calificacion, @RequestBody String descripcion){
        logica.calificarEstablecimiento(idCliente, idEnsayo, calificacion, descripcion);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
