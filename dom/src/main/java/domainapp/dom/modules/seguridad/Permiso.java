package domainapp.dom.modules.seguridad;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.MemberOrder;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="id")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")

public class Permiso implements Comparable<Permiso>{

	// {{ Nombre Permiso (property)
	private String permisoNombre;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public String getPermisoNombre() {
		return permisoNombre;
	}

	public void setPermisoNombre(final String permisoNombre) {
		this.permisoNombre = permisoNombre;
	}
	// }}
	
	// {{ Descripcion Permiso (property)
	private String permisoDescripcion;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public String getPermisoDescripcion() {
		return permisoDescripcion;
	}

	public void setPermisoDescripcion(final String permisoDescripcion) {
		this.permisoDescripcion = permisoDescripcion;
	}
	// }}

	public String title(){
		String text = permisoNombre;
		return text;
	}

	@Override
	public int compareTo(Permiso other) {
		int last = this.getPermisoDescripcion().compareTo(other.getPermisoDescripcion());
        return last;
	}
}
