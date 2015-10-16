/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.persistencia.componentes;

import edu.eci.cosw.persistencia.Ensayo;
import edu.eci.cosw.persistencia.Establecimiento;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author estudiante
 */
public interface RepositorioEnsayo extends CrudRepository<Ensayo, Integer>{
    @Query("select t from Establecimiento e join e.instrumentos r join r.ensayo t where t.cliente.idCliente=:clientex AND (select c from Calificacion c where c.calificacionEstablecimiento>0) IS NULL")
    public List<Ensayo> EstablecimientosEnsayados(@Param("clientex") int idCliente);
    
    @Query("select t from Establecimiento e join e.instrumentos r join r.ensayo t where t.idEnsayo=:ensayox AND t.cliente.idCliente=:clientex AND (select c from Calificacion c where c.calificacionEstablecimiento>0) IS NULL")
    public Ensayo ConsultarEnsayosDeCliente(@Param("clientex") int idCliente, @Param("ensayox") int idEnsayo);
}