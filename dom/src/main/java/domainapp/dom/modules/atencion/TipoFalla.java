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
                        + "FROM domainapp.dom.modules.atencion.TipoFalla "),

        @javax.jdo.annotations.Query(name = "buscarTipoFallaXOrden", language = "JDOQL", value = "SELECT   "
        				+"FROM dom.modules.atencion.TipoFalla WHERE descripcion == :descripcion")
})

@DomainObject(
		bounded=true,
        objectType = "TIPOFALLA"
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)

public class TipoFalla {
	
	public DomainObjectContainer getContainer() {
		return container;
	}

	public void setContainer(DomainObjectContainer container) {
		this.container = container;
	}

	public String title() {
		return getDescripcion();
	}
	// {{ Descripcion (property)
	private String descripcion;

	@Persistent
	@MemberOrder(sequence = "1")
	@javax.jdo.annotations.Column(allowsNull="true")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(final String Descripcion) {
		this.descripcion=Descripcion;
	}
	// }}
	   
    
    @javax.inject.Inject

	private DomainObjectContainer container;
}
