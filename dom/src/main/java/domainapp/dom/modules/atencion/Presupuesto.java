package domainapp.dom.modules.atencion;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.VersionStrategy;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import domainapp.dom.modules.servicios.E_estadoPresupuesto;
import domainapp.dom.modules.servicios.EnvioCorreo;
import java.util.ArrayList;
import org.joda.time.LocalDate;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

/**
 * @author PlusCel
 *
 */


/**
 * Clase Presupuesto.
 */
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.APPLICATION)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Uniques({ @javax.jdo.annotations.Unique(name = "Presupuesto_numero_must_be_unique", members = { "numero" }) })
@javax.jdo.annotations.Queries({	
@javax.jdo.annotations.Query(name = "buscarPorNumero", language = "JDOQL", value = "SELECT "
				+ "FROM dom.modules.atencion.Presupuesto WHERE numero.indexOf(:numero) >= 0"),
@javax.jdo.annotations.Query(name = "listadoReparaciones", language = "JDOQL", value = "SELECT "		
						+ "FROM dom.modules.atencion.Presupuesto " +
							"WHERE equipo == :equipo && cliente== :cliente" +
						 " && fechaHora >= :fechaDesde && fechaHora<= :fechaHasta"
						)

})
@DomainObject(
		bounded=true,
        objectType = "PRESUPUESTO"
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)


public class Presupuesto {
	
  
	public DomainObjectContainer getContainer() {
		return container;
	}

	public void setContainer(DomainObjectContainer container) {
		this.container = container;
	}
	
	/**
	 * Titulo de la clase.
	 * 
	 * @return the string
	 */
	public String title() {		
		return "Presupuesto:" + " " + getNumero()   ;
	}
	
	
		// //////////////////////////////////////
		// NumeroPresupuesto (propiedad)
		// //////////////////////////////////////

		@PrimaryKey
		private long numero;

		@javax.jdo.annotations.Column(allowsNull = "false")
		@javax.jdo.annotations.PrimaryKey(column = "numero")
		@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT, sequence = "numero")
		@MemberOrder(sequence = "10")
		public long getNumero() {
			return numero;
		}

		public void setNumero(final long numero) {
			this.numero = numero;
		}
		

	
	private Cliente cliente;	
	
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(final Cliente cliente) {
		this.cliente = cliente;
	}
		
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
	
 
	
 // {{ FechaHora (property)
 	private LocalDate fechaHora;
 	@MemberOrder(sequence = "4")
 	@Column(allowsNull = "false")
 	public LocalDate getFechaHora() {
 		return fechaHora;
 	}

 	public void setFechaHora(final LocalDate fechaHora) {
 		this.fechaHora = fechaHora;
 	}

 	// }}
 	
    
 	//{{ Diagnostico (property)
    private String diagnostico;
    @Persistent
	@MemberOrder(sequence = "5")
    @javax.jdo.annotations.Column(allowsNull="false", length = 40)
    public String getDiagnostico(){
        return diagnostico;
    }
    public void setDiagnostico(final String diagnostico) {
        this.diagnostico = diagnostico;
    }      
   //}}
 			
  //{{ Reparacion Requerida(property)
    private String reparacionRequerida;
    @Persistent
	@MemberOrder(sequence = "6")
    @javax.jdo.annotations.Column(allowsNull="false", length = 40)
    public String getReparacionRequerida(){
        return reparacionRequerida;
    }
    public void setReparacionRequerida(final String reparacionRequerida) {
        this.reparacionRequerida = reparacionRequerida;
    }      
   //}}
    
 // {{  (property)
  	private double importe;
  	@Column(allowsNull = "false")
  	@MemberOrder(sequence = "7")
  	public double getImporte() {
  		return importe;
  	}
  
  	public void setImporte(final double importe) {
  		this.importe = importe;
  	}
  	

 	//{{ Observacion (property)
    private String observacion;
    @Persistent
	@MemberOrder(sequence = "8")
    @javax.jdo.annotations.Column(allowsNull="false", length = 40)
    public String getObservacion(){
        return observacion;
    }
    public void setObservacion(final String observacion) {
        this.observacion = observacion;
    }      
   //}}
    
    private E_estadoPresupuesto estado;  
    @Persistent
	@MemberOrder(sequence = "9")
    @javax.jdo.annotations.Column(allowsNull="true", length = 40)
    public E_estadoPresupuesto getEstadoPresupuesto() {
        return estado;
    }
    public void setEstadoPresupuesto(final E_estadoPresupuesto estado) {
        this.estado = estado;
    }

    public static Long getDateDiff(LocalDate date) {		
    LocalDate date1 = new LocalDate();    	
    //los milisegundos
    long diferenciaMils = date1.toDate().getTime() - date.toDate().getTime();
  
    //obtenemos los segundos
    long segundos = diferenciaMils / 1000;
  
    //obtenemos las horas
    long horas = segundos / 3600;
  
    //restamos las horas para continuar con minutos
    segundos -= horas*3600;
  
    //igual que el paso anterior
    long minutos = segundos /60;
    segundos -= minutos*60;
      
        return (horas)  ;
    } 
    
     
    public Long getAlerta(){
    	if ((getDateDiff(getFechaHora()) > 40) && (getEstadoPresupuesto() == E_estadoPresupuesto.A_PRESUPUESTAR) ){
   		    	    	return  getDateDiff(getFechaHora()) ;
    	}
		return null;
    }
    
    
       
  //{{ Diagnostico (property)
    private String garantia ;     
    @Persistent
	@MemberOrder(sequence = "10")
    @javax.jdo.annotations.Column(allowsNull="false", length = 40)
   
    public String getGarantia(){
    	return garantia  ;
    	}  	
        
    
   public void setGarantia(final String  garantia) {
        this.garantia = garantia  ;
    }      
   //}}
   
        	
    
	 //region > injected services
    @javax.inject.Inject
		private DomainObjectContainer container;
		
	//endregion
		
}
