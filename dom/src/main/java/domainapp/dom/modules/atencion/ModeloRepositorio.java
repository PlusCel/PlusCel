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
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.value.Blob;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;

@DomainService(repositoryFor = Modelo.class)
@DomainServiceLayout(menuOrder = "1" , named="Gestion")

public class ModeloRepositorio {
	
    //region > create (action)
    @MemberOrder(sequence = "1")
    public Modelo ingresarModelo(
            final @ParameterLayout(named="Abreviatura") String abreviatura,
            final @ParameterLayout(named="Descripcion", multiLine=10) String descripcion,
            final @ParameterLayout(named="Marca") Marca marca,
            final @ParameterLayout(named="Imagen") @Parameter(optionality=Optionality.OPTIONAL) Blob attachment
    		) {
        final Modelo obj = container.newTransientInstance(Modelo.class);
        obj.setAbreviatura(abreviatura);
        obj.setMarca(marca);
        obj.setDescripcion(descripcion);
        obj.setAttachment(attachment);
        container.persistIfNotAlready(obj);
        container.informUser("El nuevo modelo a sido cargado correctamente");
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
    public static List<Modelo> buscarTodosLosModelos() {
        return container.allInstances(Modelo.class);
    }
    //endregion

    //region > findByDescripcion (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "3")
    public List<Modelo> buscarPorDescripcion(
            @ParameterLayout(named="Descripcion")  @Parameter(optionality=Optionality.OPTIONAL)
            final String descripcion
    ) {
        return container.allMatches(
                new QueryDefault<>(
                        Modelo.class,
                        "findByDescripcion",
                         "descripcion", descripcion==null?"":descripcion));
    }
    
    @MemberOrder(sequence = "4")
    public List<Modelo> buscarModelosXMarca(final Marca marca) {

		return container.allMatches(new QueryDefault<Modelo>(Modelo.class,
				"findByMarca","marca", marca));
    }
     //endregion
    
    //region > injected services

    @javax.inject.Inject
	static 
    DomainObjectContainer container;

    //endregion

}
