/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.persistencia.componentes;

import edu.eci.cosw.persistencia.Sala;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author 2089978
 */
public interface RepositorioSala  extends CrudRepository<Sala, Integer>{

    @Query("select s from Establecimiento e join e.salas s where e.nombre=:nombrex and LENGTH(e.nit)=:longitudx")
    public List<Sala> salaPorEstablecimientoHabilitado(@Param("nombrex") String nombre, @Param("longitudx") int longitud);    
    
    @Query("select s from Establecimiento e join e.salas s where e.idEstablecimiento=:idx")
    public List<Sala> salaPorEstablecimiento(@Param("idx") int id);    
    
    @Query("select count(s.nombre) from Sala s")
    public int consultarCantidadSalas();
    
    @Query("select s from Reservacion r join r.sala s where r.idReservacion=:id")
    public Sala salaPorReservacion(@Param("id") int id);

}
