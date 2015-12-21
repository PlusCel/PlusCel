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
import domainapp.dom.modules.atencion.OrdenServicio;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.joda.time.LocalDate;
import org.apache.isis.applib.query.QueryDefault;
import domainapp.dom.modules.servicios.E_estado;
import domainapp.dom.modules.atencion.TipoFalla;
import domainapp.dom.modules.atencion.TipoFallaRepositorio;
import domainapp.dom.modules.atencion.OrdenServicioRepositorio;
import domainapp.dom.modules.atencion.Tecnico;

import java.util.List;
@DomainService(repositoryFor = FallaEquipoTecnico.class)
@DomainServiceLayout(menuOrder = "5" , named="Tecnico")
public class FallaEquipoTecnicoRepositorio {
	
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
	
	//region > listarTodos (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )

    //region > create (action)
    @MemberOrder(sequence = "1")
    public FallaEquipoTecnico ingresarRegistroDeReparacion(
    		final @ParameterLayout(named="Numero de orden") OrdenServicio orden,
    		final @ParameterLayout(named="Fecha") LocalDate  fechaHora,
            final @ParameterLayout(named="Tecnico") Tecnico tecnico,
            final @ParameterLayout(named="Tipo de Falla") TipoFalla tipoFalla,
            final @ParameterLayout(named="Falla descripcion", multiLine=10) String fallaDesc,
            final @ParameterLayout(named="Estado") E_estado estado) {
    	
        final FallaEquipoTecnico obj = container.newTransientInstance(FallaEquipoTecnico.class);
        obj.setOrdenServicio(orden);
        final Falla fallaCel = new Falla();
        fallaCel.setTipoFalla(tipoFalla);
        fallaCel.setDescripcion(fallaDesc);
        obj.setFalla(fallaCel);
        obj.setFechaHora(fechaHora);
        obj.setTecnico(tecnico);
        obj.setEstado(estado);   
        container.persistIfNotAlready(obj);
        orden.setEstado(estado);
        container.persistIfNotAlready(orden);
        container.informUser("La falla a sido cargado correctamente");
              
        return obj;
    }
    
    @MemberOrder(sequence = "3")
    public List<FallaEquipoTecnico> listarTodos() {
        return container.allInstances(FallaEquipoTecnico.class);
    }
    //endregion
    
    @MemberOrder(sequence = "4")
    public List<FallaEquipoTecnico> buscarRegistroDeMovimientoPorOrden(           @ParameterLayout(named="Numero Orden de Servicio")
    final long numero)
  {
        return container.allMatches(
                new QueryDefault<>(
                		FallaEquipoTecnico.class,
                        "registroDeMovimientoPorOrden",
                        "numero", numero));
    }

	
    //region > injected services

    @javax.inject.Inject 
    DomainObjectContainer container;        

	@javax.inject.Inject
	TipoFallaRepositorio tipoFallaRepositorio;
	
	@javax.inject.Inject
	TipoFalla tipoFalla;
	
	@javax.inject.Inject
	OrdenServicioRepositorio ordenRepositorio;
	
    //endregion
//endregion

 
}
