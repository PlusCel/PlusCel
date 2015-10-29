package domainapp.dom.modules.atencion;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.MemberOrder;

/**
 * @author PlusCel
 *
 */
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="id")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@javax.jdo.annotations.Queries({

        @javax.jdo.annotations.Query(name = "buscarPorImei", language = "JDOQL", value = "SELECT "
                    			+ "FROM dom.modules.atencion.Equipo " + "WHERE imei == :imei"),

	    @javax.jdo.annotations.Query(name = "buscarPorMarca", language = "JDOQL", value = "SELECT "
	                    			+ "FROM dom.modules.atencion.Equipo " + "WHERE marca.abreviatura == :marca"),	 

	    @javax.jdo.annotations.Query(name = "buscarPorEstado", language = "JDOQL", value = "SELECT "
	     	                    			+ "FROM dom.modules.atencion.Equipo " + "WHERE estado == :estado"),	                    			
                    			
})


@javax.jdo.annotations.Unique(name="Equipo_name_UNQ", members = {"imei","marca"})


@DomainObject(
		bounded=true,
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
	
	public String title() {		
		return getImei()  + ", " + getMarca().getDescripcion();
	}
    
    private Marca marca;
    
    @Persistent
	@MemberOrder(sequence = "1")
    @javax.jdo.annotations.Column(allowsNull="false")
	
    public Marca getMarca() {
        return marca;
    }
    public void setMarca(final Marca marca) {
        this.marca = marca;
    }
    
    private Modelo modelo;
    @Persistent
	@MemberOrder(sequence = "2")
    @javax.jdo.annotations.Column(allowsNull="false")
    public Modelo getModelo() {
        return modelo;
    }
    public void setModelo(final Modelo modelo) {
        this.modelo = modelo;
    }
           
 
    //{{ Imei (property)
    private String imei;
    @Persistent
	@MemberOrder(sequence = "6")
    @javax.jdo.annotations.Column(allowsNull="false", length = 40)
    public String getImei(){
        return imei;
    }
    public void setImei(final String imei) {
        this.imei = imei;
    }      
       
    
   //}}
	@javax.inject.Inject

	private DomainObjectContainer container;

}