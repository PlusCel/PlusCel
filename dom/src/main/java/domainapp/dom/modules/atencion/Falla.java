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
                        + "FROM domainapp.dom.modules.atencion.Marca "),
        @javax.jdo.annotations.Query(name = "findByDescripcion", language = "JDOQL", value = "SELECT "
                    			+ "FROM dom.modules.atencion.Marca.Descripcion " + "WHERE descripcion == :descripcion"),
    
})

@DomainObject(
		bounded=true,
        objectType = "FALLA"
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)

public class Falla {
	
	public DomainObjectContainer getContainer() {
		return container;
	}

	public void setContainer(DomainObjectContainer container) {
		this.container = container;
	}
/*
	// {{ TipoFalla (property)
	private TipoFalla tipoFalla;

	@Persistent
	@MemberOrder(sequence = "1")
	@javax.jdo.annotations.Column(allowsNull="true")
	public TipoFalla getTipoFalla() {
		return tipoFalla;
	}

	public void setTipoFalla(final TipoFalla tipoFalla) {
		this.tipoFalla = tipoFalla;
	}
	// }}*/
	// {{ Descripcion (property)
    private String descripcion;
    
    @Persistent
	@MemberOrder(sequence = "2")
    @javax.jdo.annotations.Column(allowsNull="false", length = 40)
	
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }
	// }}    
    
    @javax.inject.Inject

	private DomainObjectContainer container;
}
