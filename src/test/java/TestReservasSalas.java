/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.eci.cosw.logica.Logica;
import edu.eci.cosw.persistencia.Alquiler;
import edu.eci.cosw.persistencia.Cliente;
import edu.eci.cosw.persistencia.Establecimiento;
import edu.eci.cosw.persistencia.Reservacion;
import edu.eci.cosw.persistencia.Sala;
import edu.eci.cosw.restcontrollers.OperationFailedException;
import java.sql.Time;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author camiloandres
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextH2.xml"})
public class TestReservasSalas {
    
    @Autowired
    private Logica logica;
    
    public TestReservasSalas() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Test
    public void testDisponibilidadSala() throws OperationFailedException{
        Establecimiento e = new Establecimiento(1,"El toque", "123.456.789-1", "calle falsa 123",700,1900,0,"Usaquen", "1234567");
        logica.registrarEstablecimiento(e);
        Sala s = new Sala(1,logica.consultarEstablecimiento(1),"1000","Sala de Orcas");
        logica.registrarSala(s);
        Date d=new Date();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(d);
        Time t = new Time(gc.getTime().getTime());
        
        assertTrue(logica.verificarDisponibilidadSala(d,t, s.getIdSala(), 1));
        
        Reservacion r= new Reservacion(1,s, d,t, 1);
        //s.getReservacions().add(r);
        boolean resp=logica.registrarReserva(e.getIdEstablecimiento(), s.getIdSala(), d,t, 2);
        
        assertFalse(logica.verificarDisponibilidadSala(d,t, s.getIdSala(), 1));
        List<Reservacion>l=logica.consultarReservacionesPorSala(s.getIdSala());
        System.out.println(l.get(0).getFecha().toString());
        
        Cliente c = new Cliente(1016040342, "ORCA");
        logica.registrarCliente(c);
        
        for(Reservacion rs:l){
            if(rs.getFecha().equals(r.getFecha()))r=rs;
        }
        
        if(resp)logica.crearEnsayoAlquiler(c.getIdCliente(),r, "ensayaremos mucho");

    }
       
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
