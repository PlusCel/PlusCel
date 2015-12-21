package domainapp.dom.modules.atencion;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import domainapp.dom.modules.atencion.OrdenServicio;
import domainapp.dom.modules.atencion.OrdenServicioRepositorio;

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
	
			
			@javax.jdo.annotations.Query(name = "ListarLiquidacionTecnicos", language = "JDOQL", value = "SELECT "
					+ "FROM dom.modules.atencion.SumaTecnico "+
					" WHERE fechaHora >= :fechaDesde && fechaHora<= :fechaHasta"),
	
})


public class SumaTecnico {
	
	private Tecnico tecnico;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(final Tecnico tecnico) {
		this.tecnico = tecnico;
	}
	
  	private double importe;
 	
  	@Column(allowsNull = "true")
  	@MemberOrder(sequence = "7")
  	public double getImporte() {
  		return importe;
  	}
  
  	public void setImporte(final double importe) {
  		this.importe = importe;
  	}
  	
 	private LocalDate fechaHora; 	
 	
 	@MemberOrder( sequence = "4")
 	@Column(allowsNull = "true")
 	public LocalDate getFechaHora() {
 		return fechaHora;
 	}

 	public void setFechaHora(final LocalDate fechaHora) {
 		this.fechaHora = fechaHora;
 	}
 	
  	
  	
  	private LocalDate fechaDesde; 	
	 	
 	@MemberOrder( sequence = "4")
 	@Column(allowsNull = "true")
 	public LocalDate getFechaDesde() {
 		return fechaDesde;
 	}

 	public void setFechaDesde(final LocalDate fechaDesde) {
 		this.fechaDesde = fechaDesde;
 	}
 	
 	private LocalDate fechaHasta; 	
	 	
 	@MemberOrder( sequence = "4")
 	@Column(allowsNull = "true")
 	public LocalDate getFechaHasta() {
 		return fechaHasta;
 	}

 	public void setFechaHasta(final LocalDate fechaHasta) {
 		this.fechaHasta = fechaHasta;
 	}
  	
}
