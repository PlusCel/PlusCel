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
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.value.Blob;

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
                        + "FROM domainapp.dom.modules.atencion.Modelo "),
        @javax.jdo.annotations.Query(name = "findByDescripcion", language = "JDOQL", value = "SELECT "
                    			+ "FROM domainapp.dom.modules.atencion.Modelo " + 
        		" WHERE ((:descripcion=='') || (descripcion.toLowerCase().indexOf(:descripcion) >= 0))"),

        @javax.jdo.annotations.Query(name = "findByMarca", language = "JDOQL", value = "SELECT "
                            			+ "FROM dom.modules.atencion.Modelo " + "WHERE marca == :marca"),
            			
	})


@DomainObject(
		bounded=true,
        objectType = "MODELO"
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)

public class Modelo {
	
	public DomainObjectContainer getContainer() {
		return container;
	}

	public void setContainer(DomainObjectContainer container) {
		this.container = container;
	}
	//
		  
	  
	public String title() {
		return getAbreviatura() +"-"+ getDescripcion();
	}
	
	  //{{ Marca (property)
	
	private Marca marca;	
   

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Marca getMarca() {
		return marca;
	}
	
	public void setMarca(final Marca marca) {
		this.marca = marca;
	}
	
	// }}
	// {{ Abreviatura (property)
	private String abreviatura;

	@Persistent
	@MemberOrder(sequence = "2")
	@javax.jdo.annotations.Column(allowsNull="true")

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(final String abreviatura) {
		this.abreviatura = abreviatura;
	}
	// }}
	// {{ Descripcion (property)
  private String descripcion;
  
  @Persistent
	@MemberOrder(sequence = "3")
  @javax.jdo.annotations.Column(allowsNull="false", length = 255)
	
  public String getDescripcion() {
      return descripcion;
  }
  
  public void setDescripcion(final String descripcion) {
      this.descripcion = descripcion;
  }
  
  //endregion
  
  /*Esta porcion de codigo nos permite insertar documentos en nuestra clase.*/
  private Blob attachment;

  @javax.jdo.annotations.Persistent(defaultFetchGroup="false")
  @javax.jdo.annotations.Column(allowsNull="true")
  public Blob getAttachment() {
      return attachment;
  }

  public void setAttachment(final Blob attachment) {
      this.attachment = attachment;
  }
  //fin
    @javax.inject.Inject

	private DomainObjectContainer container;
}
