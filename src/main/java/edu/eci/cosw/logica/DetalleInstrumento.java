package edu.eci.cosw.logica;
// Generated 20/09/2015 08:58:00 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * DetalleInstrumento generated by hbm2java
 */
@Entity
@Table(name="DetalleInstrumento")
public class DetalleInstrumento  implements java.io.Serializable {


     private int idDetalleInstrumento;
     private String nombre;
     private String descrpcion;
     private Set<Instrumento> instrumentos = new HashSet(0);

    public DetalleInstrumento() {
    }

	
    public DetalleInstrumento(int idDetalleInstrumento, String nombre) {
        this.idDetalleInstrumento = idDetalleInstrumento;
        this.nombre = nombre;
    }
    public DetalleInstrumento(int idDetalleInstrumento, String nombre, String descrpcion, Set<Instrumento> instrumentos) {
       this.idDetalleInstrumento = idDetalleInstrumento;
       this.nombre = nombre;
       this.descrpcion = descrpcion;
       this.instrumentos = instrumentos;
    }
   
     @Id 

    
    @Column(name="idDetalleInstrumento", unique=true, nullable=false)
    public int getIdDetalleInstrumento() {
        return this.idDetalleInstrumento;
    }
    
    public void setIdDetalleInstrumento(int idDetalleInstrumento) {
        this.idDetalleInstrumento = idDetalleInstrumento;
    }

    
    @Column(name="Nombre", nullable=false, length=45)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="Descrpcion", length=45)
    public String getDescrpcion() {
        return this.descrpcion;
    }
    
    public void setDescrpcion(String descrpcion) {
        this.descrpcion = descrpcion;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="detalleInstrumento")
    public Set<Instrumento> getInstrumentos() {
        return this.instrumentos;
    }
    
    public void setInstrumentos(Set<Instrumento> instrumentos) {
        this.instrumentos = instrumentos;
    }




}


