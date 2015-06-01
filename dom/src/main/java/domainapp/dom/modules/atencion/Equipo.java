package domainapp.dom.modules.atencion;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.util.ObjectContracts;


@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
//@javax.jdo.annotations.DatastoreIdentity(
//        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
//         column="id")
//@javax.jdo.annotations.Version(
//        strategy=VersionStrategy.VERSION_NUMBER, 
//        column="version")
//@javax.jdo.annotations.Queries({
//        @javax.jdo.annotations.Query(
//                name = "find", language = "JDOQL",
//                value = "SELECT "
//                        + "FROM domainapp.dom.modules.atencion.Equipo "),
//        @javax.jdo.annotations.Query(name = "findByMarca", language = "JDOQL", value = "SELECT "
//                    			+ "FROM dom.modules.atencion.Equipo " + "WHERE marca == :marca"),
//        @javax.jdo.annotations.Query(
//                name = "findByModelo", language = "JDOQL",
//                value = "SELECT "
//                        + "FROM domainapp.dom.modules.atencion.Modelo "
//                        + "WHERE modelo.startsWith(:modelo)")
//})


//@javax.jdo.annotations.Unique(name="Equipo_name_UNQ", members = {"marca"})

@DomainObject(
        objectType = "EQUIPO"
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)

public class Equipo {
	public DomainObjectContainer getContainer() {
		return container;
	}

	public void setContainer(DomainObjectContainer container) {
		this.container = container;
	}

	private String marca;

    @javax.jdo.annotations.Column(allowsNull="false", length = 40)
    @Title(sequence="1")
    @Property(
            editing = Editing.DISABLED
    )
    public String getMarca() {
        return marca;
    }

    public void setMarca(final String marca) {
        this.marca = marca;
    }
    private String modelo;

    @javax.jdo.annotations.Column(allowsNull="false", length = 40)
    @Title(sequence="1")
    @Property(
            editing = Editing.DISABLED
    )
    public String getModelo() {
        return modelo;
    }

    public void setModelo(final String modelo) {
        this.modelo = modelo;
    }
	@javax.inject.Inject

	private DomainObjectContainer container;

}