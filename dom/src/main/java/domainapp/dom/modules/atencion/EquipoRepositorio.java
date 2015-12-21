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
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.QueryDefault;
import domainapp.dom.modules.atencion.Modelo;
import domainapp.dom.modules.atencion.Marca;
import domainapp.dom.modules.atencion.ModeloRepositorio;

@DomainService(repositoryFor = Equipo.class)
@DomainServiceLayout(menuOrder = "3" , named="Equipo")

public class EquipoRepositorio {
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
	
	//region > listarTodos (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )

    //region > create (action)
    @MemberOrder(sequence = "1")

    public Equipo ingresarEquipo(
    		
            final @ParameterLayout(named="Marca") Marca marca,    		           
            final @ParameterLayout(named="Modelo") Modelo modelo,            
            final @ParameterLayout(named="IMEI") @Parameter(regexPattern = domainapp.dom.modules.validador.ValidadorCaracteres.ValidacionNumerica.PERMITIDOS, maxLength = 20) String imei   
            
    		) {
        final Equipo obj = container.newTransientInstance(Equipo.class);
        obj.setMarca(marca);
        obj.setModelo(modelo);    
        obj.setImei(imei);
   
        container.persistIfNotAlready(obj);
        container.informUser("El Equipo a sido cargado correctamente");
        return obj;
    }
	@Programmatic
	public List<Modelo> choices1IngresarEquipo(final Marca marca)
		{
			return modeloRepositorio.buscarModelosXMarca(marca);
		}
	
	@MemberOrder(sequence = "2")
    public List<Equipo> listarTodos() {
        return container.allInstances(Equipo.class);
    }
    //endregion

   // region > buscarPorMarca (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "3")
    public List<Equipo> buscarPorMarca(
            @ParameterLayout(named="Marca")
            final String marca
    ) {
        return container.allMatches(
                new QueryDefault<>(
                        Equipo.class,
                        "buscarPorMarca",
                        "marca", marca));
    }
  // endregion   
    

    // region > buscarPorImei (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "4")
    public List<Equipo> buscarPorImei(
            @ParameterLayout(named="IMEI")
            final String imei
    ) {
        return container.allMatches(
                new QueryDefault<>(
                        Equipo.class,
                        "buscarPorImei",
                        "imei", imei));
    }
    //endregion
	
    //region > injected services

    @javax.inject.Inject 
    DomainObjectContainer container;        

	@javax.inject.Inject
	ModeloRepositorio modeloRepositorio;
    //endregion

}