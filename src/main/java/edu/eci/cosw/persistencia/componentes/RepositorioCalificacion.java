/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.persistencia.componentes;

import edu.eci.cosw.persistencia.Calificacion;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author LuisCarlos
 */
public interface RepositorioCalificacion extends CrudRepository<Calificacion, Integer>  {
    @Query("select e from Calificacion e where e.ensayo.idEnsayo=:idx")
    public Calificacion consultarCalificacionDeEnsayo(@Param("idx") int idx);   
}
