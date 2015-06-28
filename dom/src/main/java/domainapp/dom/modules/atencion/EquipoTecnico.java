
package domainapp.dom.modules.atencion;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Where;
@SuppressWarnings("unused")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@javax.jdo.annotations.Query(name = "listaTecnicoDeEquipo", language = "JDOQL", value = "SELECT "
		+ "FROM dom.modules.atencion.Equipotecnico " + "WHERE equipo == :equipo")


@DomainObject(
		bounded=true,
        objectType = "EQUIPOTECNICO"
)		
public class EquipoTecnico {
	
	// {{ Tecnico (property)
	private Tecnico tecnico;

	@MemberOrder(sequence = "1")
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
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(final Equipo equipo) {
		this.equipo =equipo;
	}


}
