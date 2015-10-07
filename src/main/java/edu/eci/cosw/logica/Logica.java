/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.logica;

import edu.eci.cosw.persistencia.Establecimiento;
import edu.eci.cosw.persistencia.Reservacion;
import edu.eci.cosw.persistencia.Sala;
import org.springframework.stereotype.Service;
import edu.eci.cosw.persistencia.componentes.RepositorioEstablecimiento;
import edu.eci.cosw.persistencia.componentes.RepositorioSala;
import edu.eci.cosw.persistencia.componentes.RepositorioReservacion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
    @Autowired
    private RepositorioReservacion rr;  
    
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
    
    /**
     * @obj verificar si una sala esta disponible e la fecha, con hora, especificada
     * @param fecha la fecha a revisar si hay disponibilidad
     * @param idSala identificador de la sala en donde se quiere verificar la disponiblidad
     * @param idEstablecimiento identificador del establecimeinto que que tiene la sala
     * @return true si la sala esta disponible en la fecha establecida, false de lo contrario
     */
    public boolean verificarDisponibilidadSala(Date fecha, int idSala, int idEstablecimiento){
        boolean res=true;
        Establecimiento e = re.findOne(idEstablecimiento);
        
        Sala s=null;
        Object[] obs=e.getSalas().toArray();
        
        for(int i=0;i<obs.length;i++){
            if(((Sala)obs[i]).getIdSala()==idSala){
                s=(Sala) obs[i];
            }
        }
        
        try{
            Set<Reservacion>rs=s.getReservacions();
        
        if(s.getReservacions()!=null){
            Object[] r= s.getReservacions().toArray();
            boolean n=true;
            for(int i=0;i<r.length && n;i++){
                if(((Reservacion)r[i]).getFecha().after(fecha) && fecha.getHours()-((Reservacion)r[i]).getTiempo()>0){
                    n=true;
                }else if(((Reservacion)r[i]).getFecha().before(fecha) && ((Reservacion)r[i]).getTiempo()-fecha.getHours()>0){
                    n=true;
                }else{
                    n=false;
                }
            }
            if(n==false)res=false;
        }else{
            res=true;
        }
        }catch(NullPointerException npe){
            res=true;
        }
        
             
               
        return res;
    }
    
    /**
     * 
     * @param idEstablecimiento identificacion del establecimiento al que pertenece la sala donde se hara la reserva
     * @param idSala identificacion de la sala en la que se hara la reserva
     * @param idMusico identificacion del musico que quiere reservar una sala
     * @param fecha fecha, con hora incluida, de la reserva
     * @param duracion duracion, en numero de horas, de la reserva, y, por consiguiente, del ensayo
     */
    public void registrarReserva(int idEstablecimiento, int idSala, int idMusico, Date fecha, int duracion){
        Object[] salas = re.findOne(idEstablecimiento).getSalas().toArray();
        Sala s=null;
        for(int i=0;i<salas.length;i++){
            if(((Sala)salas[i]).getIdSala()==idSala){
                s=(Sala)salas[i];
            }            
        }        
        Reservacion re = new Reservacion(salas.length,s,fecha,duracion);
        rr.save(re);
    }  
    
}
