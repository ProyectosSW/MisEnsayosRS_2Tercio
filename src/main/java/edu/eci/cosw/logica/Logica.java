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
import edu.eci.cosw.stubs.DetalleCalificacion;
import edu.eci.cosw.stubs.PagosStub;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

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
    private CamaraComercioStub ccs=new CamaraComercioStub();
    
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
     * @param nit
     * @return 
     */
    public boolean verificarEmpresa(String nit) {
        return ccs.verificarEmpresa(nit);
    }
    
    /**
     * 
     * @param nit
     * @return 
     */
    public Establecimiento consultarEstablecimientosporNit(String nit){
        return re.consultarEstablecimientosporNit(nit);
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
     * @param idx
     * @return 
     */
    public Establecimiento consultarEstablecimientoHabilitado(int idx){
        return re.findId(idx, CamaraComercioStub.size);
    }
    /**
     * 
     * @param name nombre del establecimiento seleccionado
     * @return el o los establecimientos seleccionados
     */
    public List<Establecimiento> consultarEstablecimientosNombre(String name){
        return re.findByName(name, CamaraComercioStub.size);
    }
    
    /**
     * 
     * @param localidad
     * @return el o los establecimientos seleccionados
     */
    public List<Establecimiento> consultarEstablecimientosLocalidad(String localidad){
        return re.findByLocation(localidad, CamaraComercioStub.size);
    }
    
    /**
     * 
     * @param s sala a registrar
     */
    public void registrarSala(Sala s) throws OperationFailedException {
        if (consultarEstablecimientosporNit(s.getEstablecimiento().getNit())!=null) rs.save(s);
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
     * @param id
     * @return lista de las salas pertenecientes a determinado establecimiento
     */
    public List<Sala> consultarSalaPorEstablecimiento(int id){
        return rs.salaPorEstablecimiento(id);
    }
    
    /**
     * 
     * @return 
     */
    public int consultarCantidadEstablcimientos(){
        return re.consultarCantidadEstablcimientos();
    }
    
    /**
     * 
     * @return 
     */
    public int consultarCantidadSalas(){
        return rs.consultarCantidadSalas();
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
    public void habilitarEstablecimiento(int id) throws OperationFailedException{
        Establecimiento e = re.findOne(id);
        if(e.getNit().length()>=13){
            e.setNit(e.getNit().substring(0, 13));
            re.save(e);
        }else throw new OperationFailedException("NIT invalido para habilitar establecimiento");
    }    
    
    /**
     * 
     * @param e establecimiento a registrar
     * @throws edu.eci.cosw.restcontrollers.OperationFailedException
     */
    public void registrarEstablecimiento(Establecimiento e) throws OperationFailedException{
        if (e.getNit().length()>=13 && consultarEstablecimientosporNit(e.getNit().substring(0, 13))==null && consultarEstablecimientosporNit(e.getNit())==null){
            re.save(e);
        } else throw new OperationFailedException("No se puede registrar el establecimiento dado");
        
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
    public boolean verificarDisponibilidadSala(Date fecha, Time hora, int idSala, int idEstablecimiento){
        boolean res=true;
        Establecimiento e = re.findOne(idEstablecimiento);
        List <Sala> salas=consultarSalaPorEstablecimiento(e.getIdEstablecimiento());
        Sala s=null;
        
        for(int i=0;i<salas.size();i++){
            if((salas.get(i)).getIdSala()==idSala){
                s=salas.get(i);
            }
        }        
        try{
            List<Reservacion>reservas=consultarReservacionesPorSala(s.getIdSala());
        
        if(reservas!=null){
            Calendar c1=new GregorianCalendar();
            Calendar c2=new GregorianCalendar();
            c1.setTime(fecha);
            Time h1, h2;
            h1=hora;
            boolean n=true;
            for(int i=0;i<reservas.size() && n;i++){
                h2=reservas.get(i).getHora();
                c2.setTime(reservas.get(i).getFecha());
                
                if((c1.after(c2) && h2.getHours()-h1.getHours()>0)){
                    n=true;
                }else if(c1.before(c2) && h1.getHours()-h2.getHours()>0){
                    n=true;
                }else{
                    n=false;
                    System.out.println(h1+"                                      "+h2);
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
     * @param fecha fecha, con hora incluida, de la reserva
     * @param duracion duracion, en numero de horas, de la reserva, y, por consiguiente, del ensayo
     * @return true si se registro la reserva, false de lo contrario.
     */
    public String registrarReserva(int idEstablecimiento, int idSala, Date fecha, Time hora, int duracion){
        boolean resp = verificarDisponibilidadSala(fecha, hora, idSala, idEstablecimiento);
        //if(resp){
            Establecimiento e = consultarEstablecimiento(idEstablecimiento);
            List <Sala> salas=consultarSalaPorEstablecimiento(e.getIdEstablecimiento());
            Sala s=null;
            for(int i=0;i<salas.size();i++){
                if((salas.get(i)).getIdSala()==idSala){
                    s=salas.get(i);                
                }            
            }       
        
            Reservacion res = new Reservacion((int)rr.count(),s,fecha,hora,duracion);
            rr.save(res);
            List<Reservacion> r = consultarReservacionesPorSala(idSala);
            r.add(res);
            Set<Reservacion> rss = new HashSet<>(r);
            s.setReservacions(rss);
            rs.save(s);
        //}
        
        return res.getIdReservacion()+"";
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
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n"+idCliente+""+idEnsayo);
        Calificacion califi=consultarCalificacionDeEnsayo(encal.getIdEnsayo());
        if(califi!=null){
                    califi.setCalificacionEstablecimiento(calificacion);
                    califi.setDescripcion(califi.getDescripcion()+"\n\n"+descripcion);
                    ca.save(califi);
        }else if(califi==null){
            Calificacion calificaE= new Calificacion(idEnsayo, encal, 0, calificacion, descripcion);
            registrarCalificacion(calificaE);
        }
    }
    
    /**
     * @obj crea el ensayo asociado a un cliente especifico, posteriormente crea el alquiler
     * @param idCliente identificador del cliente al que se asocia el ensayo
     * @param r Reservacion que sera asociada al alquiler
     * @param descripcion descripcion del ensayo
     */
    public void crearEnsayoAlquiler(int idCliente, Reservacion r, String descripcion){
        Cliente c = cl.findOne(idCliente);
        Ensayo e = new Ensayo((int)es.count(),c,descripcion);
        //GregorianCalendar gc = new GregorianCalendar(2000, 12, 3);
        //e.setFechaCancelacion(gc.getGregorianChange());
        es.save(e);
        //Reservacion r = consultarReservacion(idReservacion);       
        List<Reservacion> reservs = rr.reservacionesPorSala(r.getSala().getIdSala());
        for (Reservacion res : reservs) {
            if(res.getFecha().equals(r.getFecha()) && res.getHora().getHours()==r.getHora().getHours() && res.getHora().getMinutes()==r.getHora().getMinutes())r=res;
            
        }
        rr.delete(r.getIdReservacion());
        rr.save(r);
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
        
        Sala s = rs.salaPorReservacion(a.getReservacion().getIdReservacion());
        int deuda = Integer.parseInt(s.getPrecio());
        if(deuda>monto){
            b=false;
        }else if(ps.realizarPago(numTarjeta)){
            a.setTipoDePago(tipoP);
            ra.delete(a.getIdAlquiler());
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
        return rr.reservacionByID(idReserva);
    }
    /**
     * 
     * @param reserva
     * @return 
     */
    public Calificacion consultarCalificacionDeEnsayo(int reserva){
        return ca.consultarCalificacionDeEnsayo(reserva);
    }
    /**
     * 
     * @param idSala
     * @return 
     */
    public List<Reservacion> consultarReservacionesPorSala(int idSala){
        return rr.reservacionesPorSala(idSala);        
    }
    /**
     * 
     * @param idAlquiler
     * @return 
     */
    public Alquiler consultarAlquiler(int idAlquiler){
        return ra.findOne(idAlquiler);
    }
    /**
     * 
     * @param idCliente
     * @return 
     */
    public List<Ensayo> EstablecimientosEnsayados(int idCliente){
        return es.EstablecimientosEnsayados(idCliente);
    }
    public List<Ensayo> ClientesEstablecimiento(DetalleCalificacion dc){
        return es.EnsayoConInstrumento(dc.getIdInstrumento(), dc.getNombreInstrumento());
    }
}
