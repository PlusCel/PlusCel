package domainapp.dom.modules.atencion;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.MemberOrder;


import domainapp.dom.modules.servicios.E_accesorioParte;

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

        @javax.jdo.annotations.Query(
                name = "find", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.dom.modules.atencion.AccesorioEquipo "),
        
                        @javax.jdo.annotations.Query(name = "listaAccesorioDeEquipo", language = "JDOQL", value = "SELECT "
                        		+ "FROM dom.modules.atencion.AccesorioEquipo " + "WHERE equipo == :equipo")
})


@javax.jdo.annotations.Unique(name="AccesorioEquipo_name_UNQ", members = {})

@DomainObject(
		bounded=true,
        objectType = "ACCESORIOEQUIPO"
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)

public class AccesorioEquipo {
	public DomainObjectContainer getContainer() {
		return container;
	}

	public void setContainer(DomainObjectContainer container) {
		this.container = container;
	}
	
	public String title() {		
		return equipo.getImei();
	}


//{{ Equipo (property)
	    private Equipo equipo;
	    
	    @Persistent
		@MemberOrder(sequence = "1")
	    @javax.jdo.annotations.Column(allowsNull="false")
		
	    public Equipo getEquipo() {
	        return equipo;
	    }
	    public void setEquipo(final Equipo equipo) {
	        this.equipo = equipo;
	    }
//}}
	
//{{ Memoria (property)
    private E_accesorioParte memoria;
    
    @Persistent
	@MemberOrder(sequence = "2")
    @javax.jdo.annotations.Column(allowsNull="false")
	
    public E_accesorioParte getMemoria() {
        return memoria;
    }
    public void setMemoria(final E_accesorioParte memoria) {
        this.memoria = memoria;
    }
  //}}         
    
  //{{ Chip (property)
    private E_accesorioParte chip;
    @Persistent
	@MemberOrder(sequence = "3")
    @javax.jdo.annotations.Column(allowsNull="false")
    
    public E_accesorioParte getChip() {
        return chip;
    }
    
    public void setChip(final E_accesorioParte chip) {
        this.chip = chip;
    }
 
 //}}     
          
 //{{ Observacion (property)
    private String observacion;
    @Persistent
	@MemberOrder(sequence = "4")
    @javax.jdo.annotations.Column(allowsNull="false", length = 40)   
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(final String observacion) {
        this.observacion = observacion;
    }
  //}}  
	@javax.inject.Inject
	private DomainObjectContainer container;
}