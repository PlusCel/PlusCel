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
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.QueryDefault;
import domainapp.dom.modules.atencion.TipoFalla;

@DomainService(repositoryFor = TipoFalla.class)
@DomainServiceLayout(menuOrder = "1" , named="Gestion")

public class TipoFallaRepositorio {

    //region > create (action)
    @MemberOrder(sequence = "1")
    public TipoFalla ingresarTipoFalla(
            final @ParameterLayout(named="Descripcion") String descripcion
  
            ) {
    	  	final TipoFalla obj = container.newTransientInstance(TipoFalla.class);
    		obj.setDescripcion(descripcion);
            container.persistIfNotAlready(obj);
            container.informUser("El tipo de falla a sido cargado correctamente");
            return obj;
    }

    //endregion
        
	 //region > listAll (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "2")
    public List<TipoFalla> listarTodosLosTiposFallas() {
        return container.allInstances(TipoFalla.class);
    }
    
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "4")
    public List<TipoFalla> buscarTipoFallaXOrden(    @ParameterLayout(named="Numero Orden de Servicio")
    final String descripcion) {
		return container.allMatches(new QueryDefault<TipoFalla>(TipoFalla.class,
				"buscarTipoFallaXOrden","descripcion", descripcion));
    }

    //endregion
    //region > injected services

    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion
	
	
}
