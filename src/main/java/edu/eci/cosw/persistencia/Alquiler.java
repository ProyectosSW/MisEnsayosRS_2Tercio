package edu.eci.cosw.persistencia;
// Generated 20/09/2015 08:58:00 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Alquiler generated by hbm2java
 */
@Entity
@Table(name="Alquiler")
public class Alquiler  implements java.io.Serializable {


     private int idAlquiler;
     private Ensayo ensayo;
     private Reservacion reservacion;
     private String tipoDePago;
     private int valorMulta;
     private String captacion;

    public Alquiler() {
    }

    public Alquiler(int idAlquiler, Ensayo ensayo, Reservacion reservacion, String tipoDePago, int valorMulta, String captacion) {
       this.idAlquiler = idAlquiler;
       this.ensayo = ensayo;
       this.reservacion = reservacion;
       this.tipoDePago = tipoDePago;
       this.valorMulta = valorMulta;
       this.captacion = captacion;
    }
   
     @Id 

    
    @Column(name="idAlquiler", unique=true, nullable=false)
    public int getIdAlquiler() {
        return this.idAlquiler;
    }
    
    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Ensayo_idEnsayo", nullable=false)
    public Ensayo getEnsayo() {
        return this.ensayo;
    }
    
    public void setEnsayo(Ensayo ensayo) {
        this.ensayo = ensayo;
    }

@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="Reservacion_idReservacion", nullable=false)
    public Reservacion getReservacion() {
        return this.reservacion;
    }
    
    public void setReservacion(Reservacion reservacion) {
        this.reservacion = reservacion;
    }

    
    @Column(name="TipoDePago", nullable=false, length=100)
    public String getTipoDePago() {
        return this.tipoDePago;
    }
    
    public void setTipoDePago(String tipoDePago) {
        this.tipoDePago = tipoDePago;
    }

    
    @Column(name="ValorMulta", nullable=false)
    public int getValorMulta() {
        return this.valorMulta;
    }
    
    public void setValorMulta(int valorMulta) {
        this.valorMulta = valorMulta;
    }

    
    @Column(name="Captacion", nullable=false, length=45)
    public String getCaptacion() {
        return this.captacion;
    }
    
    public void setCaptacion(String captacion) {
        this.captacion = captacion;
    }




}


