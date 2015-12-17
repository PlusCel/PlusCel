
package domainapp.dom.modules.atencion;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;
import org.joda.time.LocalDate;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Where;
import domainapp.dom.modules.atencion.Falla;
import domainapp.dom.modules.servicios.E_estado;

@SuppressWarnings("unused")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")


@javax.jdo.annotations.Query(name = "registroDeMovimientoPorOrden", language = "JDOQL", value = "SELECT "
		+ "FROM dom.modules.atencion.FallaEquipotecnico  " + "WHERE orden.numero == :numero")


@DomainObject(
		bounded=true,
        objectType = "FALLAEQUIPOTECNICO"
)		
public class FallaEquipoTecnico {
	
	/**
	 * Titulo de la clase.
	 * 
	 * @return the string
	 */
	public String title() {		
		return getFechaHora() + " - " + getEstado() ;
	}

	private OrdenServicio orden;

	@javax.jdo.annotations.Column(allowsNull = "false")
	@MemberOrder(name="Orden de Servicio",sequence = "1")
	public OrdenServicio getOrdenServicio() {
		return orden;
	}

	public void setOrdenServicio(final OrdenServicio orden) {
		this.orden = orden;
	}
	
	
	
	// {{ Falla (property)

    private Falla falla;
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")	
    public Falla getFalla() {
        return falla;
    }
    public void setFalla(final Falla falla) {
        this.falla = falla;
        
    }
	// }}
	
	// {{ Tecnico (property)
	private Tecnico tecnico;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "true")
	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(final Tecnico tecnico) {
		this.tecnico = tecnico;
	}
	// }}
	
	// FechaHora
 	private LocalDate fechaHora; 	
 	 	 	
 	@MemberOrder( sequence = "4")
 	@Column(allowsNull = "true")
 	public LocalDate getFechaHora() {
 		return fechaHora;
 	}

 	public void setFechaHora(final LocalDate fechaHora) {
 		this.fechaHora = fechaHora;
 	}

	
 //Estado de la orden de servicio	
    
  	private E_estado estado;  
    @Persistent
	@MemberOrder(sequence = "5")
    @javax.jdo.annotations.Column(allowsNull="true", length = 20)
    public E_estado getEstado() {
        return estado;
    }
    public void setEstado(final E_estado estado) {
        this.estado = estado;
    }
    


}
