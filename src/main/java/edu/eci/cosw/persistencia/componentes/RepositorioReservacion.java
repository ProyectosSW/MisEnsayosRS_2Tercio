/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.persistencia.componentes;


import edu.eci.cosw.persistencia.Reservacion;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author camiloandres
 */
public interface RepositorioReservacion extends CrudRepository<Reservacion, Integer>{
    
    @Query("select r from Sala s join s.reservacions r where s.idSala=:id")
    public List<Reservacion> reservacionesPorSala(@Param("id") int salaid); 
    
}
