package domainapp.dom.modules.atencion;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Where;

import domainapp.dom.modules.servicios.E_estadoPresupuesto;

import java.util.Date;


/**
 * @author PlusCel
 *
 */
@SuppressWarnings("unused")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@javax.jdo.annotations.Query(name = "listaTecnicoDeEquipo", language = "JDOQL", value = "SELECT "
		+ "FROM dom.modules.atencion.Equipotecnico " + "WHERE presupuesto == :presupuesto")


@DomainObject(
		bounded=true,
        objectType = "PRESUPUESTO"
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)


public class Presupuesto {
	public DomainObjectContainer getContainer() {
		return container;
	}

	public void setContainer(DomainObjectContainer container) {
		this.container = container;
	}
			
	
	private Cliente cliente;
	
	@MemberOrder(sequence = "1")
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
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(final Equipo equipo) {
		this.equipo =equipo;
	}
	
		
 
	
 // {{ FechaHora (property)
 	private Date fechaHora;

 	@MemberOrder(sequence = "3")
 	@Column(allowsNull = "false")
 	public Date getFechaHora() {
 		return fechaHora;
 	}

 	public void setFechaHora(final Date fechaHora) {
 		this.fechaHora = fechaHora;
 	}

 	// }}
 	
    
 	//{{ Diagnostico (property)
    private String diagnostico;
    @Persistent
	@MemberOrder(sequence = "4")
    @javax.jdo.annotations.Column(allowsNull="false", length = 40)
    public String getDiagnostico(){
        return diagnostico;
    }
    public void setDiagnostico(final String diagnostico) {
        this.diagnostico = diagnostico;
    }      
   //}}
 			
  //{{ Reparacion Requerida(property)
    private String reparacionRequerida;
    @Persistent
	@MemberOrder(sequence = "5")
    @javax.jdo.annotations.Column(allowsNull="false", length = 40)
    public String getReparacionRequerida(){
        return reparacionRequerida;
    }
    public void setReparacionRequerida(final String reparacionRequerida) {
        this.reparacionRequerida = reparacionRequerida;
    }      
   //}}
    
 // {{  (property)
  	private double importe;
 	
  	
  	@Column(allowsNull = "false")
  	@MemberOrder(sequence = "6")
  	public double getImporte() {
  		return importe;
  	}
  
  	public void setImporte(final double importe) {
  		this.importe = importe;
  	}
  	

 	//{{ Observacion (property)
    private String observacion;
    @Persistent
	@MemberOrder(sequence = "7")
    @javax.jdo.annotations.Column(allowsNull="false", length = 40)
    public String getObservacion(){
        return observacion;
    }
    public void setObservacion(final String observacion) {
        this.observacion = observacion;
    }      
   //}}
    
    private E_estadoPresupuesto estado;  
    @Persistent
	@MemberOrder(sequence = "8")
    @javax.jdo.annotations.Column(allowsNull="true", length = 40)
    public E_estadoPresupuesto getEstadoPresupuesto() {
        return estado;
    }
    public void setEstadoPresupuesto(final E_estadoPresupuesto estado) {
        this.estado = estado;
    }

		
	 //region > injected services
		@javax.inject.Inject

		private DomainObjectContainer container;
		
	//endregion
		
		
		

}
