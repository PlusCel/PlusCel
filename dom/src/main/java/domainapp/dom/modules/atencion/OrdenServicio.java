package domainapp.dom.modules.atencion;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.VersionStrategy;

import net.sf.jasperreports.engine.JRException;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.MemberOrder;

import domainapp.dom.modules.reportes.E_formato;
import domainapp.dom.modules.reportes.EquiposSinRevisar;
import domainapp.dom.modules.reportes.GenerarReporte;
import domainapp.dom.modules.servicios.E_estado;
import domainapp.dom.modules.servicios.E_estadoGarantia;
import domainapp.dom.modules.servicios.EnvioCorreo;

import org.joda.time.LocalDate;


/**
 * @author PlusCel
 *
 */

/**
 * Clase Orden de Servicio.
 */
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.APPLICATION)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Uniques({ @javax.jdo.annotations.Unique(name = "OrdenServicio_numero_must_be_unique", members = { "numero" }) })
@javax.jdo.annotations.Queries({	
		@javax.jdo.annotations.Query(name = "buscarPorNumero", language = "JDOQL", value = "SELECT "
				+ "FROM dom.modules.atencion.OrdenServicio WHERE numero == :numero"),
		
		@javax.jdo.annotations.Query(name = "buscarPorEstado", language = "JDOQL", value = "SELECT "
				+ "FROM dom.modules.atencion.OrdenServicio " + "WHERE estado == :estado"),
		
		@javax.jdo.annotations.Query(name = "sinArreglo", language = "JDOQL", value = "SELECT "
				+ "FROM dom.modules.atencion.OrdenServicio WHERE estado == 'SIN_ARREGLO'"),
             			
		@javax.jdo.annotations.Query(name = "reparados", language = "JDOQL", value = "SELECT "
				+ "FROM dom.modules.atencion.OrdenServicio WHERE estado == 'REPARADO'"),
		
		@javax.jdo.annotations.Query(name = "sinRevisar", language = "JDOQL", value = "SELECT "
				+ "FROM dom.modules.atencion.OrdenServicio WHERE estado == 'SIN_REVISAR'"),
		
		@javax.jdo.annotations.Query(name = "BuscarReparacionesFiltro", language = "JDOQL", value = "SELECT "
				+ "FROM dom.modules.atencion.OrdenServicio"+
				" WHERE equipo == :equipo && cliente== :cliente" +
				" && fechaHora >= :fechaDesde && fechaHora<= :fechaHasta"),
                     			
		@javax.jdo.annotations.Query(name = "LiquidarReparacionesPorTecnico", language = "JDOQL", value = "SELECT "
				+ "FROM dom.modules.atencion.OrdenServicio"+
				" WHERE tecnico == :tecnico && estado == :estado" +
				" && fechaHora >= :fechaDesde && fechaHora<= :fechaHasta"),
		
		@javax.jdo.annotations.Query(name = "OrdenesServiciosPorTecnico", language = "JDOQL", value = "SELECT "
				+ "FROM dom.modules.atencion.OrdenServicio"+
				" WHERE tecnico == :tecnico" ),
		
		@javax.jdo.annotations.Query(name = "ContarOrdenesServiciosPorTecnico", language = "JDOQL", value = "SELECT count(*) "
				+ "FROM dom.modules.atencion.OrdenServicio"+
				" WHERE tecnico == :tecnico"),
		
		@javax.jdo.annotations.Query(name = "buscarOrdenadasPorFecha", language = "JDOQL", value = "SELECT "
				+ "FROM dom.modules.atencion.OrdenServicio "+
				" ORDER BY this.fechaHora desc")/*,
		@javax.jdo.annotations.Query(name = "buscarFallasXOrden", language = "JDOQL", value = "SELECT tipoFalla "
				+ "FROM dom.modules.atencion.OrdenServicio "
				+ " WHERE numero == :numero")		*/
		
})

@DomainObject(
		bounded=true,
        objectType = "ORDEN DE SERVICIO"
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)

public class OrdenServicio {
	
  
	public DomainObjectContainer getContainer() {
		return container;
	}

	public void setContainer(DomainObjectContainer container) {
		this.container = container;
	}
	
	public String title() {		
		return "Orden de Servicio:" + " " + getNumero()   ;
	}
	
		// //////////////////////////////////////
		// Numero de la Orden de Servicio (propiedad)
		// //////////////////////////////////////

		@PrimaryKey
		private long numero;

		@javax.jdo.annotations.Column(allowsNull = "false")
		@javax.jdo.annotations.PrimaryKey(column = "numero")
		@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT, sequence = "numero")
		@MemberOrder(name="Orden de Servicio",sequence = "1")
		public long getNumero() {
			return numero;
		}

		public void setNumero(final long numero) {
			this.numero = numero;
		}
		
		
		private Tecnico tecnico;

		@MemberOrder(sequence = "1")
		@Column(allowsNull = "true")
		public Tecnico getTecnico() {
			return tecnico;
		}

		public void setTecnico(final Tecnico tecnico) {
			this.tecnico = tecnico;
		}
		//Cliente
		
	private Cliente cliente;	
	
	@MemberOrder( sequence = "2")
	@Column(allowsNull = "true")
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(final Cliente cliente) {
		this.cliente = cliente;
	}
		
	//Equipo
	private Equipo equipo;
	@MemberOrder( sequence = "3")
	@Column(allowsNull = "true")
	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(final Equipo equipo) {
		this.equipo =equipo;
	}
	
	// FechaHora
 	private LocalDate fechaHora; 	
 	 	 	
 	@MemberOrder( sequence = "4")
 	@Column(allowsNull = "true")
 	public LocalDate getFechaHora() {
 		return fechaHora;
 	}

 	public void setFechaHora(final LocalDate fechaHora) {
 		this.fechaHora = fechaHora;
 	}
 	
 	//TipoFalla
 		private TipoFalla tipofalla;	
 		
 		@MemberOrder( sequence = "5")
 		@Column(allowsNull = "true")
 		public TipoFalla getTipoFalla() {
 			return tipofalla;
 		}

 		public void setTipoFalla(final TipoFalla tipofalla) {
 			this.tipofalla = tipofalla;
 		}
 	
 //Descripcion de la Falla
    private String falla;
    @Persistent
	@MemberOrder( sequence = "6")
    @javax.jdo.annotations.Column(allowsNull="true", length = 300)
    public String getFalla(){
        return falla;
    }
    public void setFalla(final String falla) {
        this.falla = falla;
    }      
    
 // Importe
  	private double importe;
 	
  	@Column(allowsNull = "true")
  	@MemberOrder(sequence = "7")
  	public double getImporte() {
  		return importe;
  	}
  
  	public void setImporte(final double importe) {
  		this.importe = importe;
  	}
  	
  	 // Comision Tecnico
  	private double comisionTecnico;
 	
  	@Column(allowsNull = "true")
  	@MemberOrder(sequence = "8")
  	public double getComisionTecnico() {
  		return comisionTecnico;
  	}
  
  	public void setComisionTecnico(final double comisionTecnico) {
  		this.comisionTecnico = comisionTecnico;
  	}
  	
  //Estado de la orden de servicio	
    
  	private E_estado estado;  
    @Persistent
	@MemberOrder(sequence = "9")
    @javax.jdo.annotations.Column(allowsNull="true", length = 20)
    public E_estado getEstado() {
        return estado;
    }
    public void setEstado(final E_estado estado) {
        this.estado = estado;
    }
    
//Estado de la orden de servicio	
    
  	private E_estadoGarantia garantia;  
    @Persistent
	@MemberOrder(sequence = "10")
    @javax.jdo.annotations.Column(allowsNull="true", length = 20)
    public E_estadoGarantia getGarantia() {
        return garantia;
    }
    public void setGarantia(final E_estadoGarantia garantia) {
        this.garantia = garantia;
    }
        
    public OrdenServicio EnviarAlertaSinArreglo() {	
    	if (OrdenServicio.this.estado == E_estado.SIN_ARREGLO) {
    		EnvioCorreo.send(getCliente().getEmail(),
    				"ESTADO ORDEN DE SERVICIO", 
    				"Orden de Servicio :" + OrdenServicio.this.numero + " Estado : "+ OrdenServicio.this.estado);
    	}
    					
		return this;
	}
    
    //Enviamos alerta via mail al tecnico para informar de un nuevo equipo.
    public OrdenServicio EnviarAlertaTecnico() {	
    	if (OrdenServicio.this.estado == E_estado.SIN_REVISAR) {
    		EnvioCorreo.send(getTecnico().getEmail(),
    				"Nuevo equipo para revisar", 
    				"La Orden de Servicio :" + OrdenServicio.this.numero +
    				" con el Estado : "+ OrdenServicio.this.estado + 
    				"Necesita ser chequeada por el tecnico antes de las 48 horas");
    	}
    					
		return this;
	}
    
    public String imprimirOrden() throws JRException{
		List<Object> objectsReport = new ArrayList<Object>();
								
		
			EquiposSinRevisar orden = new EquiposSinRevisar();
			
			orden.setFalla(getTipoFalla().getDescripcion() + " - " + getFalla() );
			orden.setCliente(String.valueOf(getCliente().getNombre() + " " + getCliente().getApellido() ));
			orden.setEquipo(String.valueOf("Imei: " + getEquipo().getImei() + "  " + "Modelo: " + getEquipo().getModelo().getAbreviatura()+ "  " + "Modelo: " + getEquipo().getModelo().getAbreviatura()));		
			
			orden.setTecnico(String.valueOf(getTecnico().getApellido() + " " + getTecnico().getNombre()));
			orden.setNumero(getNumero());
			

			objectsReport.add(orden);
			
			
		String nombreArchivo ="reportes/OrdenServicio" + String.valueOf(orden.getNumero()) ;

		GenerarReporte.generarReporte("reportes/OrdenServicio.jrxml", objectsReport, nombreArchivo);
		
			
		
		return "Reporte Generado.";
	}
       
    
    
	 //region > injected services
    @javax.inject.Inject
		private DomainObjectContainer container;
}
