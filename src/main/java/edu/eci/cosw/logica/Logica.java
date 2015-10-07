/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.logica;

import edu.eci.cosw.persistencia.Calificacion;
import edu.eci.cosw.persistencia.Cliente;
import edu.eci.cosw.persistencia.DetalleInstrumento;
import edu.eci.cosw.persistencia.Ensayo;
import edu.eci.cosw.persistencia.Establecimiento;
import edu.eci.cosw.persistencia.Instrumento;
import edu.eci.cosw.persistencia.Reservacion;
import edu.eci.cosw.persistencia.Sala;
import edu.eci.cosw.persistencia.componentes.RepositorioCalificacion;
import edu.eci.cosw.persistencia.componentes.RepositorioCliente;
import edu.eci.cosw.persistencia.componentes.RepositorioDetalleInstrumento;
import edu.eci.cosw.persistencia.componentes.RepositorioEnsayo;
import org.springframework.stereotype.Service;
import edu.eci.cosw.persistencia.componentes.RepositorioEstablecimiento;
import edu.eci.cosw.persistencia.componentes.RepositorioInstrumento;
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
    @Autowired
    private RepositorioEnsayo es;
    @Autowired
    private RepositorioCliente cl;
    @Autowired
    private RepositorioDetalleInstrumento di;
    @Autowired
    private RepositorioInstrumento ri;
    @Autowired
    private RepositorioCalificacion ca;
    
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
    
    public Ensayo consultarEnsayo(int idEnsayo){
        return es.findOne(idEnsayo);
    }
    
    /**
     * 
     * @param e 
     */
    public void registrarEstablecimiento(Establecimiento e) {
        re.save(e);
    }
    
    /**
     * 
     * @param c
     */
    public void registrarCliente(Cliente c){
        cl.save(c);
    }
    /**
     * 
     * @param e 
     */
    public void registrarEnsayo(Ensayo e){
        es.save(e);
    }
    /**
     * 
    * @param d
     */
    public void registrarDetalleInstrumento(DetalleInstrumento d){
        di.save(d);
    }
    /**
     * 
     * @param i
     */
    public void registrarInstrumento(Instrumento i){
        ri.save(i);
    }
    /**
     * 
     * @param cal
     */
     public void registrarCalificacion(Calificacion cal){
        ca.save(cal);
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

    /**
     * 
     * @param idCliente identificacion del cliente, en este caso el musico
     * @param idEnsayo identificacion del ensayo asociado al cliente
     * @param calificacion calificacion a registrar por parte del cliente
     * @param descripcion decripcion por parte del cliente asociada al ensayo
     */
    public void calificarEstablecimiento(int idCliente,int idEnsayo, int calificacion, String descripcion){
        Ensayo encal= es.ConsultarEnsayosDeCliente(idCliente, idEnsayo);
        int i=encal.getCalificacions().size();
        if(i==1){
            List<Calificacion> ti = new ArrayList<>(encal.getCalificacions());
            for(Calificacion r:ti){
                if(r.getCalificacionEstablecimiento()==0){
                    r.setCalificacionEstablecimiento(calificacion);
                    r.setDescripcion(r.getDescripcion()+"\n\n"+descripcion);
                }
            }
        }else if(i==0){
            Calificacion calificaE= new Calificacion(1, encal, 0, calificacion, descripcion);
            registrarCalificacion(calificaE);
            encal.getCalificacions().add(calificaE);
            registrarEnsayo(encal);                
        }
        System.out.println("\n\n\n\n\n"+encal.getCalificacions()+"\n\n\n\n\n\n\n\n\n\n");
    }
    
    
}
