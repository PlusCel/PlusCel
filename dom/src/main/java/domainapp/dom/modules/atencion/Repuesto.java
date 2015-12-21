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

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.util.ObjectContracts;
import org.joda.time.LocalDate;

import domainapp.dom.modules.servicios.E_tipoRepuesto;


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
                        + "FROM domainapp.dom.modules.atencion.Cliente "),
        @javax.jdo.annotations.Query(name = "findByDni", language = "JDOQL", value = "SELECT "
                    			+ "FROM dom.modules.atencion.Cliente " + "WHERE dni == :dni"),
        @javax.jdo.annotations.Query(
                name = "findByApellido", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.dom.modules.atencion.Cliente "
                        + "WHERE apellido.startsWith(:apellido)")
})


@DomainObject(
		bounded=true,
        objectType = "REPUESTO"
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)

public class Repuesto implements Comparable<Repuesto> {
	private String modelo;
	private String descripcion;

	public DomainObjectContainer getContainer() {
		return container;
	}

	public void setContainer(DomainObjectContainer container) {
		this.container = container;
	}
	
	public String title() {
		return getTipoRepuesto() +"-"+ getModelo();
	}
	

	@Override
	public int compareTo(Repuesto repuesto) {

		return ObjectContracts.compare(this, repuesto, "Repuesto");
	}
	
	//E_tipoRepuesto de telefono ejemplo: "FLEX"

	private E_tipoRepuesto tipoRepuesto;

	@Persistent
	@MemberOrder(sequence = "1")
	@javax.jdo.annotations.Column(allowsNull="false")
	public E_tipoRepuesto getTipoRepuesto() {
		return tipoRepuesto;
	}

	public void setTipoRepuesto(final E_tipoRepuesto tipoRepuesto) {
		this.tipoRepuesto = tipoRepuesto;
	}
	
	//Modelo de telefono ejemplo: "Motorola V80"
	@Persistent
	@MemberOrder(sequence = "2")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getModelo() {
		return modelo;
	}

	public void setModelo(final String modelo) {
		this.modelo = modelo;
	}
	
	//Descripcion de telefono ejemplo: "FLEX CON LCD EXTERNO"
	@Persistent
	@MemberOrder(sequence = "3")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	//FechadeArribo de telefono ejemplo: "18/06/15"
	private LocalDate fechaArribo;

	@Persistent
	@MemberOrder(sequence = "4")
	@javax.jdo.annotations.Column(allowsNull="true")
	public LocalDate getFechaArribo() {
		return fechaArribo;
	}

	public void setFechaArribo(final LocalDate fechaArribo) {
		this.fechaArribo = fechaArribo;
	}

	//Costo repuesto ejemplo: "15,30"
	private double costo;
	
	@Persistent
	@MemberOrder(sequence = "5")
	@javax.jdo.annotations.Column(allowsNull="true")
	public double getCosto() {
		return costo;
	}

	public void setCosto(final double costo) {
		this.costo = costo;
	}
	
	//Cantidad disponible ejemplo: "15"
	private int cantidad;
	
	@Persistent
	@MemberOrder(sequence = "6")
	@javax.jdo.annotations.Column(allowsNull="true")
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(final int cantidad) {
		this.cantidad = cantidad;
	}

	@javax.inject.Inject

	private DomainObjectContainer container;

}

