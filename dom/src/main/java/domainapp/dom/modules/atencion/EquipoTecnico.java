
package domainapp.dom.modules.atencion;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Where;
@SuppressWarnings("unused")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(name = "MateriaDelCursoDeUnCurso", language = "JDOQL", value = "SELECT FROM dom.simple.MateriaDelCurso "
			+ "WHERE this.curso.anio.plan.descripcion == :plan "
			+ "&& this.curso.anio.anioNumero == :anio"
			+ "&& this.curso.division == :division")})
		

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
	// }}
	// Title (GUI)
	// //////////////////////////////////////////
/*
	public String title() {
		return getMateria().getNombre() + " de " 
				+ getMateria().getAnio().getAnioNumero() + "° " 
				+ "'" + getCurso().getDivision() + "' "
				+ "(" + getMateria().getAnio().getPlan().getDescripcion() + ")";
	}
*/
	// end region Title (GUI)
	// //////////////////////////////////////////

}
