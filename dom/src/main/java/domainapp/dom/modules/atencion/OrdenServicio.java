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

import domainapp.dom.modules.servicios.E_estadoPresupuesto;

import java.util.ArrayList;
import java.util.Date;
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
				+ "FROM dom.modules.atencion.OrdenServicio WHERE numero.indexOf(:numero) >= 0") })
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
		
	private Cliente cliente;	
	
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(final Cliente cliente) {
		this.cliente = cliente;
	}
		
	// {{ Equipo (property)
	private Equipo equipo;
	//@Hidden(where = Where.ALL_TABLES)
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "true")
	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(final Equipo equipo) {
		this.equipo =equipo;
	}

	// {{ FechaHora (property)
 	private Date fechaHora; 	
 	 	 	
 	@MemberOrder(sequence = "4")
 	@Column(allowsNull = "false")
 	public Date getFechaHora() {
 		return fechaHora;
 	}

 	public void setFechaHora(final Date fechaHora) {
 		this.fechaHora = fechaHora;
 	}
 //{{ Falla (property)
    private String falla;
    @Persistent
	@MemberOrder(sequence = "5")
    @javax.jdo.annotations.Column(allowsNull="false", length = 300)
    public String getFalla(){
        return falla;
    }
    public void setFalla(final String falla) {
        this.falla = falla;
    }      
    
 // {{  (property)
  	private double importe;
 	
  	@Column(allowsNull = "false")
  	@MemberOrder(sequence = "7")
  	public double getImporte() {
  		return importe;
  	}
  
  	public void setImporte(final double importe) {
  		this.importe = importe;
  	}
  	
	 //region > injected services
    @javax.inject.Inject
		private DomainObjectContainer container;
}
