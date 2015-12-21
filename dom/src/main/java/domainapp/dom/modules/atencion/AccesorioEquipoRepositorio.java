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
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.QueryDefault;

import domainapp.dom.modules.servicios.E_accesorioParte;
;
@DomainService(repositoryFor = AccesorioEquipo.class)
@DomainServiceLayout(menuOrder = "3" , named="Equipo")

public class AccesorioEquipoRepositorio {
	
    //region > create (action)
    @MemberOrder(sequence = "1")
    public AccesorioEquipo ingresarAccesorioEquipo(
            final @ParameterLayout(named="Equipo") @Parameter(regexPattern = domainapp.dom.modules.validador.ValidadorCaracteres.ValidacionAlfanumerico.PERMITIDOS, maxLength = 10) Equipo equipo,    		           
            final @ParameterLayout(named="Memoria") E_accesorioParte memoria, 
            final @ParameterLayout(named="Chip") E_accesorioParte chip,  
            final @ParameterLayout(named="Observación" , multiLine=10) String observacion   
            
    		) {
        final AccesorioEquipo obj = container.newTransientInstance(AccesorioEquipo.class);
        obj.setEquipo(equipo);
        obj.setMemoria(memoria);
        obj.setChip(chip);
        obj.setObservacion(observacion);
        container.persistIfNotAlready(obj);
        container.informUser("El Accesorio del equipo a sido cargado correctamente");
        return obj;
    }

    //endregion
    
	//region > listarTodos (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "2")
   public List<AccesorioEquipo> listarTodos() {
        return container.allInstances(AccesorioEquipo.class);
    }
    //endregion

    
    @MemberOrder(sequence = "3")
    public List<AccesorioEquipo> buscarAccesorioPorEquipo(Equipo equipo)
  {
        return container.allMatches(
                new QueryDefault<>(
                		AccesorioEquipo.class,
                        "listaAccesorioDeEquipo",
                        "equipo", equipo));
    }
    

    //region > injected services

    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion

}