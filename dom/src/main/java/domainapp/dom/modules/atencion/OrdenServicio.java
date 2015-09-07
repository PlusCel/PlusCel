package domainapp.dom.modules.atencion;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;

import domainapp.dom.modules.servicios.E_estado;
import domainapp.dom.modules.servicios.E_estadoGarantia;
import domainapp.dom.modules.servicios.E_estadoPresupuesto;
import domainapp.dom.modules.servicios.EnvioCorreo;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

/**
 * @author PlusCel
 *
 */

/**
 * Clase Orden de Servicio.
 */
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.APPLICATION)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Uniques({ @javax.jdo.annotations.Unique(name = "OrdenServicio_numero_must_be_unique", members = { "numero" }) })
@javax.jdo.annotations.Queries({	
		@javax.jdo.annotations.Query(name = "buscarPorNumero", language = "JDOQL", value = "SELECT "
				+ "FROM dom.modules.atencion.OrdenServicio WHERE numero.indexOf(:numero) >= 0"),
		
		@javax.jdo.annotations.Query(name = "buscarPorEstado", language = "JDOQL", value = "SELECT "
				+ "FROM dom.modules.atencion.OrdenServicio " + "WHERE estado == :estado"),
		
		@javax.jdo.annotations.Query(name = "sinArreglo", language = "JDOQL", value = "SELECT "
				+ "FROM dom.modules.atencion.OrdenServicio WHERE estado == 'SIN_ARREGLO'"),
             			
		@javax.jdo.annotations.Query(name = "reparados", language = "JDOQL", value = "SELECT "
				+ "FROM dom.modules.atencion.OrdenServicio WHERE estado == 'REPARADO'"),
		
		@javax.jdo.annotations.Query(name = "BuscarReparacionesFiltro", language = "JDOQL", value = "SELECT "
				+ "FROM dom.modules.atencion.OrdenServicio"+
				" WHERE equipo == :equipo && cliente== :cliente" +
				" && fechaHora >= :fechaDesde && fechaHora<= :fechaHasta"),
                     			
		@javax.jdo.annotations.Query(name = "LiquidarReparacionesPorTecnico", language = "JDOQL", value = "SELECT "
				+ "FROM dom.modules.atencion.OrdenServicio"+
				" WHERE tecnico == :tecnico && estado == :estado" +
				" && fechaHora >= :fechaDesde && fechaHora<= :fechaHasta")
})

@DomainObject(
		bounded=true,
        objectType = "ORDEN DE SERVICIO"
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)


public class OrdenServicio {
	
  
	public DomainObjectContainer getContainer() {
		return container;
	}

	public void setContainer(DomainObjectContainer container) {
		this.container = container;
	}
	
	public String title() {		
		return "Orden de Servicio:" + " " + getNumero()   ;
	}
	
		// //////////////////////////////////////
		// Numero de la Orden de Servicio (propiedad)
		// //////////////////////////////////////

		@PrimaryKey
		private long numero;

		@javax.jdo.annotations.Column(allowsNull = "false")
		@javax.jdo.annotations.PrimaryKey(column = "numero")
		@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT, sequence = "numero")
		@MemberOrder(sequence = "1")
		public long getNumero() {
			return numero;
		}

		public void setNumero(final long numero) {
			this.numero = numero;
		}
		
		// Tecnico
		private Tecnico tecnico;

		@MemberOrder(sequence = "1")
		@Column(allowsNull = "true")
		public Tecnico getTecnico() {
			return tecnico;
		}

		public void setTecnico(final Tecnico tecnico) {
			this.tecnico = tecnico;
		}
		//Cliente
		
	private Cliente cliente;	
	
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(final Cliente cliente) {
		this.cliente = cliente;
	}
		
	//Equipo
	private Equipo equipo;
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "true")
	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(final Equipo equipo) {
		this.equipo =equipo;
	}
	
	// FechaHora
 	private LocalDate fechaHora; 	
 	 	 	
 	@MemberOrder(sequence = "4")
 	@Column(allowsNull = "true")
 	public LocalDate getFechaHora() {
 		return fechaHora;
 	}

 	public void setFechaHora(final LocalDate fechaHora) {
 		this.fechaHora = fechaHora;
 	}
 //Descripcion de la Falla
    private String falla;
    @Persistent
	@MemberOrder(sequence = "5")
    @javax.jdo.annotations.Column(allowsNull="true", length = 300)
    public String getFalla(){
        return falla;
    }
    public void setFalla(final String falla) {
        this.falla = falla;
    }      
    
 // Importe
  	private double importe;
 	
  	@Column(allowsNull = "true")
  	@MemberOrder(sequence = "7")
  	public double getImporte() {
  		return importe;
  	}
  
  	public void setImporte(final double importe) {
  		this.importe = importe;
  	}
  	
  	 // Comision Tecnico
  	private double comisionTecnico;
 	
  	@Column(allowsNull = "true")
  	@MemberOrder(sequence = "8")
  	public double getComisionTecnico() {
  		return comisionTecnico;
  	}
  
  	public void setComisionTecnico(final double comisionTecnico) {
  		this.comisionTecnico = comisionTecnico;
  	}
  	
  //Estado de la orden de servicio	
    
  	private E_estado estado;  
    @Persistent
	@MemberOrder(sequence = "9")
    @javax.jdo.annotations.Column(allowsNull="true", length = 20)
    public E_estado getEstado() {
        return estado;
    }
    public void setEstado(final E_estado estado) {
        this.estado = estado;
    }
    
    
    public OrdenServicio EnviarAlerta() {	
    	if (OrdenServicio.this.estado == E_estado.SIN_ARREGLO) {
    		EnvioCorreo.send("Orden de Servicio :" + OrdenServicio.this.numero + " Estado : "+ OrdenServicio.this.estado , "ESTADO ORDEN DE SERVICIO" );
    		
    	}
    					
		return this;
	}
    
    
//Estado de la orden de servicio	
    
  	private E_estadoGarantia garantia;  
    @Persistent
	@MemberOrder(sequence = "9")
    @javax.jdo.annotations.Column(allowsNull="true", length = 20)
    public E_estadoGarantia getGarantia() {
        return garantia;
    }
    public void setGarantia(final E_estadoGarantia garantia) {
        this.garantia = garantia;
    }
    
	 //region > injected services
    @javax.inject.Inject
		private DomainObjectContainer container;
}
