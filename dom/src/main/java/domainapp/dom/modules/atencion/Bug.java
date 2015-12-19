package domainapp.dom.modules.atencion;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.MemberOrder;

import org.joda.time.LocalDate;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="id")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")

@javax.jdo.annotations.Queries({

    @javax.jdo.annotations.Query(name = "buscarTodos", language = "JDOQL", value = "SELECT "
                			+ "FROM dom.modules.atencion.Bug "),
    
    @javax.jdo.annotations.Query(name = "buscarUltimos", language = "JDOQL", value = "SELECT "
			+ "FROM dom.modules.atencion.Bug ORDER BY this.fecha desc"),//order by this.descripcion asc
})


@DomainObject(
		bounded=true,
        objectType = "BUG"
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)

@javax.jdo.annotations.Unique(name="Bug_id_UNQ", members = {"id"})

public class Bug {
	public DomainObjectContainer getContainer() {
		return container;
	}

	public void setContainer(DomainObjectContainer container) {
		this.container = container;
	}
	//Agrego campo Formulario
	 private String formulario;
	    @Persistent
		@MemberOrder(sequence = "1")
	    @javax.jdo.annotations.Column(allowsNull="false", length = 40)
	    public String getFormulario(){
	        return formulario;
	    }
	    public void setFormulario(final String formulario) {
	        this.formulario = formulario;
	    }      
	//Fin campo Formulario
	    
	  //Agrego campo FECHA
		// FechaHora
	 	private LocalDate fecha; 	
	 	 	 	
	 	@MemberOrder(sequence = "2")
	 	@Column(allowsNull = "true")
	 	public LocalDate getFecha() {
	 		return fecha;
	 	}

	 	public void setFecha(final LocalDate fecha) {
	 		this.fecha = fecha;
	 	} 
		//Fin campo FECHA
	
		//Agrego campo Descripcion del proceso
		 private String proceso;
		    @Persistent
			@MemberOrder(sequence = "3")
		    @javax.jdo.annotations.Column(allowsNull="false", length = 600)
		    public String getProceso(){
		        return proceso;
		    }
		    public void setProceso(final String proceso) {
		        this.proceso = proceso;
		    }      
		//Fin campo Descripcion del proceso
		  
		    //Agrego campo Descripcion del error
			 private String error;
			    @Persistent
				@MemberOrder(sequence = "4")
			    @javax.jdo.annotations.Column(allowsNull="false", length = 600)
			    public String getError(){
			        return error;
			    }
			    public void setError(final String error) {
			        this.error = error;
			    }      
			//Fin campo Descripcion del error
	
	@javax.inject.Inject

	private DomainObjectContainer container;

}
