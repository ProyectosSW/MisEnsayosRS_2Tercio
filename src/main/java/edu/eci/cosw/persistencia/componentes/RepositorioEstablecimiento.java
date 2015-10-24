/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.persistencia.componentes;

import edu.eci.cosw.persistencia.Establecimiento;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author usuario
 */
public interface RepositorioEstablecimiento extends CrudRepository<Establecimiento, Integer> {
    
    @Query("select e from Establecimiento e where e.nombre=:nombrex and LENGTH(e.nit)=:longitudx")
    public Establecimiento findByName(@Param("nombrex") String name, @Param("longitudx") int longitud);    
    
    @Query("select e from Establecimiento e where e.nombre=:nombrex")
    public Establecimiento findByNameX(@Param("nombrex") String name);
    
    @Query("select e from Establecimiento e where e.idEstablecimiento=:idx and LENGTH(e.nit)=:longitudx")
    public Establecimiento findId(@Param("idx") int id, @Param("longitudx") int longitud);    
    
    @Query("select e from Establecimiento e where LENGTH(e.nit)=:longitudx")
    public List<Establecimiento> consultarTodosEstablecimientoshabilitados(@Param("longitudx") int longitud);        
    
    @Query("select e from Establecimiento e")
    public List<Establecimiento> consultarTodos();            
    
    @Query("select e from Establecimiento e where LENGTH(e.nit)>:longitudx")
    public List<Establecimiento> conultarEstablecimientosSinHabilitar(@Param("longitudx") int longitud);
    
    @Query("select e from Establecimiento e where e.nit=:nitx")
    public Establecimiento consultarEstablecimientosporNit(@Param("nitx") String nit);
    
    @Query("select count(e) from Establecimiento e")
    public int consultarCantidadEstablcimientos();
        
}
