/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.persistencia.componentes;

import edu.eci.cosw.persistencia.Cliente;
import edu.eci.cosw.persistencia.Reservacion;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author LuisCarlos
 */
public interface RepositorioCliente extends CrudRepository<Cliente, Integer> {
     @Query("SELECT a.reservacion FROM Cliente c JOIN c.ensayos e JOIN e.alquilers a JOIN a.reservacion WHERE c.idCliente=:idCliente")                        
    public List<Reservacion> reservasDeCliente(@Param("idCliente") int longitud);
    
}
