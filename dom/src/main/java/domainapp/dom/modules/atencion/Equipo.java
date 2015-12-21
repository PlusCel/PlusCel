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
           
    //IMEI
    private String imei;
    @Persistent
	@MemberOrder(sequence = "3")
    @javax.jdo.annotations.Column(allowsNull="false", length = 20)
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