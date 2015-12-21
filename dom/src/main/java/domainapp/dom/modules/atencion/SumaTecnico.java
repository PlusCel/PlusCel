/*
 * This is software for administration and management of mobile equipment repair
 *
 * Copyright ( C ) 2015 , Pluscel
 *
 * This program is free software ; you can redistribute it and / or
 * Modify it under the terms of the GNU General Public License
 * As published by the Free Software Foundation ; either version 2
 * Of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * But WITHOUT ANY WARRANTY; without even the implied warranty
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. Boil
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * Along with this program ; if not, write to the Free Software
 *
 *
 * Foundation , Inc. , 51 Franklin Street, Fifth Floor , Boston, MA 02110-1301 , USA .
 */
package domainapp.dom.modules.atencion;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

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
