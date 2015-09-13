package domainapp.dom.modules.atencion;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.MemberOrder;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)

@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="id")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "find", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.dom.modules.atencion.TipoFalla "),
    
})

@DomainObject(
		bounded=true,
        objectType = "TIPOFALLA"
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)

public class TipoFalla {
	
	public DomainObjectContainer getContainer() {
		return container;
	}

	public void setContainer(DomainObjectContainer container) {
		this.container = container;
	}

	// {{ Descripcion (property)
	private String descripcion;

	@Persistent
	@MemberOrder(sequence = "1")
	@javax.jdo.annotations.Column(allowsNull="true")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(final String Descripcion) {
		this.descripcion=Descripcion;
	}
	// }}
	   
    
    @javax.inject.Inject

	private DomainObjectContainer container;
}
