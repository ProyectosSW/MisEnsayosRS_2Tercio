/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.eci.cosw.logica.Logica;
import edu.eci.cosw.persistencia.Establecimiento;
import edu.eci.cosw.persistencia.Sala;
import edu.eci.cosw.restcontrollers.OperationFailedException;
import edu.eci.cosw.stubs.CamaraComercioStub;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author 2089978
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextH2.xml"})
public class TestPublicarEstablecimiento {
    
    @Autowired
    private Logica logica;
    
    @Test
    public void testRegistrarEstablecimiento() throws OperationFailedException{
        int limite=500;
        ArrayList<Establecimiento> listaEsta = new ArrayList<>();
        for(int i=400; i<limite; i++){
            Establecimiento a = new Establecimiento(i, "nombre"+i, "123.123.123-1", "Autonorte"+i, 700, 1800, 2.0,"Puente Aranda", "1234567890");
            listaEsta.add(a);
            logica.registrarEstablecimiento(a);
        }
        
        Establecimiento fd;
        for(int i=400; i<limite; i++){
             fd= logica.consultarEstablecimiento(i);
             assertEquals(listaEsta.get(i-400).getIdEstablecimiento(), fd.getIdEstablecimiento());
             assertEquals(listaEsta.get(i-400).getNombre(), fd.getNombre());
             assertEquals(listaEsta.get(i-400).getNit(), fd.getNit());
             assertEquals(listaEsta.get(i-400).getDireccion(), fd.getDireccion());
             assertEquals(listaEsta.get(i-400).getHoraInicio(), fd.getHoraInicio());
             assertEquals(listaEsta.get(i-400).getHoraCierre(), fd.getHoraCierre());
             assertEquals(listaEsta.get(i-400).getLocalidad(), fd.getLocalidad());
             assertEquals(listaEsta.get(i-400).getTelefono(), fd.getTelefono());
        }
    }

    @Test
    public void testRegistrarHabilitacionEstablecimiento() throws OperationFailedException{
        int limite=100;
        ArrayList<Establecimiento> listaEsta = new ArrayList<>();
        for(int i=50; i<limite; i++){
            Establecimiento a = new Establecimiento(i, "nombre"+i, "123.123.123-1 sin revisar", "Autonorte"+i, 700, 1800, 2.0,"Puente Aranda", "1234567890");
            listaEsta.add(a);
            logica.registrarEstablecimiento(a);
        }
        
        Establecimiento fd;
        for(int i=50; i<limite; i++){
             fd= logica.consultarEstablecimientoHabilitado(i);
             assertEquals(null, fd);
        }
        for(int i=50; i<limite; i++){
             logica.habilitarEstablecimiento("nombre"+i);
             fd= logica.consultarEstablecimientoHabilitado(i);
             assertEquals(listaEsta.get(i-50).getIdEstablecimiento(), fd.getIdEstablecimiento());
             assertEquals(listaEsta.get(i-50).getNombre(), fd.getNombre());
             assertTrue(listaEsta.get(i-50).getNit()!=fd.getNit());
             assertEquals(listaEsta.get(i-50).getDireccion(), fd.getDireccion());
             assertEquals(listaEsta.get(i-50).getHoraInicio(), fd.getHoraInicio());
             assertEquals(listaEsta.get(i-50).getHoraCierre(), fd.getHoraCierre());
             assertEquals(listaEsta.get(i-50).getLocalidad(), fd.getLocalidad());
             assertEquals(listaEsta.get(i-50).getTelefono(), fd.getTelefono());
        }
    }
    
    @Test
    public void testRegistrarSala() throws OperationFailedException {
        int limite=5;
        
        Establecimiento a = new Establecimiento(1, "nombre"+1, "123.123.123-1", "Autonorte"+1, 700, 1800, 2.0,"Puente Aranda", "1234567890");
        logica.registrarEstablecimiento(a);

        ArrayList<Sala> listaSala = new ArrayList<>();
        for(int i=0; i<limite; i++){
            Sala b= new Sala(i, a, ""+(100+(i*i)), "sala"+i);
            listaSala.add(b);
            logica.registrarSala(b);
        }
        
        Sala sala;
        for(int i=0; i<limite; i++){
             sala = logica.consultarSala(i);
             assertEquals(listaSala.get(i).getIdSala(), sala.getIdSala());
             assertEquals(listaSala.get(i).getNombre(), sala.getNombre());
             assertEquals(listaSala.get(i).getPrecio(), sala.getPrecio());
        }
    }
    
    @Test
    public void testRegistrarSalaVerificacion() throws OperationFailedException {
        int limite=5;
        
        Establecimiento a = new Establecimiento(1, "nombre"+1, "123.123.123-1", "Autonorte"+1, 700, 1800, 2.0,"Puente Aranda", "1234567890");
        logica.registrarEstablecimiento(a);

        ArrayList<Sala> listaSala = new ArrayList<>();
        for(int i=0; i<limite; i++){
            Sala b= new Sala(i, a, ""+(100+(i*i)), "sala"+i);
            listaSala.add(b);
            logica.registrarSala(b);
        }
    }    
    
    @Test
    public void testStubCamarayComercio1(){
        int limite = 20;
        ArrayList<String> nits = new ArrayList<String>();
        for (int i=0; i<limite; i++){
            int n1=(int) (Math.random()*1000);
            int n2=(int) (Math.random()*1000);
            int n3=(int) (Math.random()*1000);
            int n4=(int) (Math.random()*10);
            nits.add(n1+"."+n2+"."+n3+"-"+n4);
        }
        CamaraComercioStub ccs = new CamaraComercioStub();
        for(String a:nits)
            assertEquals(a, (a.length()==13 && Integer.parseInt(a.substring(0, 3))%2==0), ccs.verificarEmpresa(a));
    }
}
