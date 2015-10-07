/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.logica;

import edu.eci.cosw.persistencia.Establecimiento;
import edu.eci.cosw.persistencia.Sala;
import org.springframework.stereotype.Service;
import edu.eci.cosw.persistencia.componentes.RepositorioEstablecimiento;
import edu.eci.cosw.persistencia.componentes.RepositorioSala;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author usuario
 */
@Service
public class Logica {
    
    @Autowired
    private RepositorioEstablecimiento re;
    @Autowired
    private RepositorioSala rs;
    
    /**
     * 
     * @param id del establecimiento seleccionado
     * @return el establecimiento seleccionado
     */
    public Establecimiento consultarEstablecimiento(int id){
        return re.findOne(id);
    }    
    
    /**
     * 
     * @param nombre del instrumento que debe tener el instrumento  
     * @param localidad en la que se debe encontrar los establecimientos 
     * @return el establecimiento que posee determinado instrumento y esta ubicado en determinada localidad ordenados por precio
     */
    public List<Establecimiento> consultarEstablecimientoPrecio(String nombre, String localidad){
        return re.establecimientoporprecio(nombre, localidad);
    }    
    
    /**
     * 
     * @param nombre del instrumento que debe tener el instrumento  
     * @param localidad en la que se debe encontrar los establecimientos 
     * @return el establecimiento que posee determinado instrumento y esta ubicado en determinada localidad ordenados por las calificaciones de los mismos
     */
    public List<Establecimiento> consultarEstablecimientoCalificacion(String nombre, String localidad){
        List<Object[]> establecimientosCalificaciones = re.establecimientoporcalificacion(nombre, localidad);
        List<Establecimiento> establecimientos = new ArrayList<>();
        for(Object[] establecimiento: establecimientosCalificaciones){
            establecimientos.add((Establecimiento)establecimiento[0]);
        }
        return establecimientos;
    }
    
    /**
     * 
     * @param s sala a registrar
     */
    public void registrarSala(Sala s) {
        rs.save(s);
    }
    
    /**
     * 
     * @param idSala
     * @return la sala seleccionada segun su identificacion
     */
    public Sala consultarSala(int idSala){
        return rs.findOne(idSala);
    }
    
    /**
     * 
     * @param e 
     */
    public void registrarEstablecimiento(Establecimiento e) {
        re.save(e);
    }
    
}
