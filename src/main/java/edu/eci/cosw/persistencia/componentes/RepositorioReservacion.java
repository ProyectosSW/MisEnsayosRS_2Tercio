/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.persistencia.componentes;


import edu.eci.cosw.persistencia.Reservacion;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author camiloandres
 */
public interface RepositorioReservacion extends CrudRepository<Reservacion, Integer>{
    
}
