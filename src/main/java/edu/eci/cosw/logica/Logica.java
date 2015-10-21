/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.logica;

import edu.eci.cosw.persistencia.Alquiler;
import edu.eci.cosw.persistencia.Calificacion;
import edu.eci.cosw.persistencia.Cliente;
import edu.eci.cosw.persistencia.DetalleInstrumento;
import edu.eci.cosw.persistencia.Ensayo;
import edu.eci.cosw.persistencia.Establecimiento;
import edu.eci.cosw.persistencia.Instrumento;
import edu.eci.cosw.persistencia.Reservacion;
import edu.eci.cosw.persistencia.Sala;
import edu.eci.cosw.persistencia.componentes.RepositorioAlquiler;
import edu.eci.cosw.persistencia.componentes.RepositorioCalificacion;
import edu.eci.cosw.persistencia.componentes.RepositorioCliente;
import edu.eci.cosw.persistencia.componentes.RepositorioDetalleInstrumento;
import edu.eci.cosw.persistencia.componentes.RepositorioEnsayo;
import org.springframework.stereotype.Service;
import edu.eci.cosw.persistencia.componentes.RepositorioEstablecimiento;
import edu.eci.cosw.persistencia.componentes.RepositorioInstrumento;
import edu.eci.cosw.persistencia.componentes.RepositorioSala;
import edu.eci.cosw.persistencia.componentes.RepositorioReservacion;
import edu.eci.cosw.restcontrollers.OperationFailedException;
import edu.eci.cosw.stubs.CamaraComercioStub;
import edu.eci.cosw.stubs.PagosStub;
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
    @Autowired
    private RepositorioAlquiler ra;
    
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
     * @return lista de todos los establecimientos registrados
     */
    public List<Establecimiento> consultarEstablecimientos(){
        return re.consultarTodos();
    }
    
    /**
     * 
     * @return lista de establecimientos registrados y habilitados
     */
    public List<Establecimiento> consultarEstablecimientosHabilitados(){
        return re.consultarTodosEstablecimientoshabilitados(CamaraComercioStub.size);
    }
    
    /**
     * 
     * @return lista de establecimientos registrados y sin habilitar
     */
    public List<Establecimiento> consultarEstablecimientosSinHabilitar(){
        return re.conultarEstablecimientosSinHabilitar(CamaraComercioStub.size);
    }
    
    /**
     * 
     * @param id del establecimiento seleccionado
     * @return el establecimiento seleccionado
     */
    public Establecimiento consultarEstablecimientoHabilitado(int id){
        return re.findId(id, CamaraComercioStub.size);
    }
    
    /**
     * 
     * @param name nombre del establecimiento seleccionado
     * @return el establecimiento seleccionado
     */
    public Establecimiento consultarEstablecimientoHabilitado(String name){
        return re.findByName(name, CamaraComercioStub.size);
    }
    
    /**
     * 
     * @param s sala a registrar
     */
    public void registrarSala(Sala s) throws OperationFailedException {
        if(re.findByNameX(s.getEstablecimiento().getNombre())!=null) rs.save(s);
        else throw new OperationFailedException("no existe el establecimiento");
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
     * @param nombre
     * @return lista de las salas pertenecientes a determinado establecimiento
     */
    public List<Sala> consultarSalaPorEstablecimiento(String nombre){
        return rs.salaPorEstablecimiento(nombre);
    }
    
    /**
     * 
     * @param nombre
     * @return 
     */
    public List<Sala> consultarSalaPorEstablecimientoHabilitado(String nombre){
        return rs.salaPorEstablecimiento(nombre);
    }
    
    /**
     * 
     * @param idEnsayo
     * @return 
     */
    public Ensayo consultarEnsayo(int idEnsayo){
        return es.findOne(idEnsayo);
    }
    
    /**
     * 
     * @param nombre del establecimiento a 
     * @throws OperationFailedException encaso de que el NIT no seha valido para habilitar el establecimiento seleccionado 
     */
    public void habilitarEstablecimiento(String nombre) throws OperationFailedException{
        Establecimiento e = re.findByNameX(nombre);
        if(e.getNit().length()>=13){
            e.setNit(e.getNit().substring(0, 13));
            re.save(e);
        }else throw new OperationFailedException("NIT invalido para habilitar establecimiento");
    }    
    
    /**
     * 
     * @param e establecimiento a registrar
     * @throws OperationFailedException en caso de que el nombre del establecimiento a registrar ya exista en la base de datos
     */
    public void registrarEstablecimiento(Establecimiento e) throws OperationFailedException {
        if(re.findByNameX(e.getNombre())==null) re.save(e);
        else throw new OperationFailedException("ya existe un establecimiento con el mismo nombre");

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
                }else if((fecha.before(((Reservacion)r[i]).getFecha())) && ((Reservacion)r[i]).getTiempo()-fecha.getHours()>0){
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
        //(Reservacion)r[i]).getFecha().before(fecha)
             
               
        return res;
    }
    
    /**
     * 
     * @param idEstablecimiento identificacion del establecimiento al que pertenece la sala donde se hara la reserva
     * @param idSala identificacion de la sala en la que se hara la reserva
     * @param fecha fecha, con hora incluida, de la reserva
     * @param duracion duracion, en numero de horas, de la reserva, y, por consiguiente, del ensayo
     */
    public void registrarReserva(int idEstablecimiento, int idSala, Date fecha, int duracion){
        Object[] salas = re.findOne(idEstablecimiento).getSalas().toArray();
        Sala s=null;
        for(int i=0;i<salas.length;i++){
            if(((Sala)salas[i]).getIdSala()==idSala){
                s=(Sala)salas[i];                
            }            
        }       
        
        Reservacion re = new Reservacion(s.getReservacions().size(),s,fecha,duracion);
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
        Calificacion califi=consultarCalificacionDeEnsayo(encal.getIdEnsayo());
        if(califi!=null){
            List<Calificacion> ti = new ArrayList<>(encal.getCalificacions());
            for(Calificacion r:ti){
                if(r.getCalificacionEstablecimiento()==0){
                    r.setCalificacionEstablecimiento(calificacion);
                    r.setDescripcion(r.getDescripcion()+"\n\n"+descripcion);
                }
            }
        }else if(califi==null){
            Calificacion calificaE= new Calificacion(1, encal, 0, calificacion, descripcion);
            registrarCalificacion(calificaE);
        }
    }
    
    /**
     * @obj crea el ensayo asociado a un cliente especifico, posteriormente crea el alquiler
     * @param idCliente identificador del cliente al que se asocia el ensayo
     * @param idReserva identificador de la reserva a la que se asocia el ensayo
     * @param descripcion descripcion del ensayo
     */
    public void crearEnsayoAlquiler(int idCliente, int idReserva, String descripcion){
       Cliente c = cl.findOne(idCliente);
       Ensayo e = new Ensayo((int)es.count(),c,descripcion);
       es.save(e);
       Reservacion r = rr.findOne(idReserva);
       Alquiler a = new Alquiler((int)ra.count(), e, r, "no pagado", 0, "5%");
       ra.save(a);
    }
    
    /**
     * @obj realizar el pago de un alquiler especificado
     * @param idAlquiler identificador del alquiler al que se hace el pago
     * @param monto dinero que se pagara del alquiler
     * @param numTarjeta numero de la tarjeta
     * @param tipoP tipo de pago, "credito" o "debito"
     * @return true si se realiza el pago de forma exitosa, false de lo contrario.
     */
    public boolean realizarPago(int idAlquiler, int monto, String numTarjeta, String tipoP){
        boolean b=true;
        PagosStub ps = new PagosStub();
        Alquiler a = ra.findOne(idAlquiler);
        int deuda = Integer.parseInt(a.getReservacion().getSala().getPrecio());
        if(deuda>monto){
            b=false;
        }else if(ps.realizarPago(numTarjeta)){
            a.setTipoDePago(tipoP);
            ra.save(a);
        }
        return b;
    }
    
    /**
     * @param idCliente identificador del cliente
     * @return regresa una lista con las reservaciones de un cliente especifico
     */
    public List<Reservacion> consultarReservasPorCliente(int idCliente){
        List l = cl.reservasDeCliente(idCliente);        
        return l;
    }
    
    /**
     * 
     * @param idCliente identificador del cliente a retornar
     * @return un cliente
     */
    public Cliente consultarCliente(int idCliente){
        return cl.findOne(idCliente);
    }
    
    /**
     * 
     * @param idReserva identificador de la reservacion a retornar
     * @return una reservacion
     */
    public Reservacion consultarReservacion(int idReserva){
        return rr.findOne(idReserva);
    }
    
    public Calificacion consultarCalificacionDeEnsayo(int reserva){
        return ca.consultarCalificacionDeEnsayo(reserva);
    }
}
