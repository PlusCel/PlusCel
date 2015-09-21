
package domainapp.dom.modules.atencion;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Where;
import domainapp.dom.modules.atencion.Falla;
@SuppressWarnings("unused")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")


@javax.jdo.annotations.Query(name = "listadoFallaPorEquipo", language = "JDOQL", value = "SELECT "
		+ "FROM dom.modules.atencion.FallaEquipotecnico " + "WHERE tecnico == :tecnico")


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
		return getEquipo().title() + " - " + getTecnico().title() ;
	}
	
	// {{ Falla (property)

    private Falla falla;
	@MemberOrder(sequence = "1")
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

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(final Tecnico tecnico) {
		this.tecnico = tecnico;
	}
	// }}
	
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


}
