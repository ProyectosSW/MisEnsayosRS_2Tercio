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
    
    @Query("select i.establecimiento from Instrumento i join i.detalleInstrumento di where di.nombre in :nombrex and i.establecimiento.idEstablecimiento in "
                        +"("+"select e.idEstablecimiento from Establecimiento e join e.salas s where e.localidad=:localidadx order by s.precio DESC"+")")
    public List<Establecimiento> establecimientoporprecio(@Param("nombrex") String nombre, @Param("localidadx") String localidad);    

    @Query("select i.establecimiento, avg(c.calificacionEstablecimiento) as p from Instrumento i join i.ensayo e join e.calificacions c where i.establecimiento.idEstablecimiento in"
                        +"("+"select i.establecimiento.idEstablecimiento from Instrumento i join i.detalleInstrumento di where di.nombre in :nombrex and i.establecimiento.idEstablecimiento in "
                        +"("+"select e.idEstablecimiento from Establecimiento e where e.localidad = :localidadx"+")"+")"+"order by p DESC")
    public List<Object[]> establecimientoporcalificacion(@Param("nombrex") String nombre, @Param("localidadx") String localidad);    
    
}
