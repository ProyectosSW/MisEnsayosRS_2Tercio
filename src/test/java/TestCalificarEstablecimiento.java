
import edu.eci.cosw.logica.Logica;
import edu.eci.cosw.persistencia.Calificacion;
import edu.eci.cosw.persistencia.Cliente;
import edu.eci.cosw.persistencia.DetalleInstrumento;
import edu.eci.cosw.persistencia.Ensayo;
import edu.eci.cosw.persistencia.Establecimiento;
import edu.eci.cosw.persistencia.Instrumento;
import edu.eci.cosw.restcontrollers.OperationFailedException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2088940
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextH2.xml"})
public class TestCalificarEstablecimiento {
    
    @Autowired
    private Logica logica;
    @Test
    public void testCalificarEstablecimientoPorMusico() throws OperationFailedException{
        
        Cliente c1= new Cliente(123, "Zlatan Ibrahimovic");
        logica.registrarCliente(c1);
        Ensayo es1=new Ensayo(555,c1,"Ensayo en la sala VIP");
        es1.setCalificacions(new HashSet<Calificacion>());
        logica.registrarEnsayo(es1);
        DetalleInstrumento det=new DetalleInstrumento(1, "Guitarra");
        logica.registrarDetalleInstrumento(det);
        Establecimiento est=new Establecimiento(777,"La casa de judas","123.456.789-1","calle falsa #nunca existio", 700,1900, 90.2,"Usaquen", "00000000");
        logica.registrarEstablecimiento(est);
        Instrumento ins=new Instrumento(333,  det, es1, est, "1000");
        logica.registrarInstrumento(ins);
        
        
        logica.calificarEstablecimiento(c1.getIdCliente(), es1.getIdEnsayo(), 4, "Fue muy bueno por que regalaron te");
        Ensayo ens = logica.consultarEnsayo(555);
        Set<Calificacion> cals=ens.getCalificacions();
        Object[] cali=cals.toArray();
        System.out.println(cali.length);
        assertEquals(((Calificacion)cali[0]).getCalificacionEstablecimiento(),4);
        
    }   
}