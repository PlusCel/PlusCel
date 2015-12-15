
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
    		final @ParameterLayout(named="Número de orden") OrdenServicio orden,
    		final @ParameterLayout(named="Tipo de Falla") TipoFalla tipoFalla,
            final @ParameterLayout(named="Equipo") Equipo equipo,
            final @ParameterLayout(named="Tecnico") Tecnico tecnico,
            final @ParameterLayout(named="Falla descripcion", multiLine=10) String fallaDesc,
            final @ParameterLayout(named="Estado") E_estado estado) {
    	
        final FallaEquipoTecnico obj = container.newTransientInstance(FallaEquipoTecnico.class);
        obj.setOrdenServicio(orden);
        final Falla fallaCel = new Falla();
        fallaCel.setTipoFalla(tipoFalla);
        fallaCel.setDescripcion(fallaDesc);
        obj.setFalla(fallaCel);
        obj.setEquipo(equipo);
        obj.setTecnico(tecnico);
        obj.setEstado(estado);   
        container.persistIfNotAlready(obj);
        orden.setEstado(estado);
        container.persistIfNotAlready(orden);
        
        
        
        return obj;
    }
    
    @MemberOrder(sequence = "3")
    public List<FallaEquipoTecnico> listarTodos() {
        return container.allInstances(FallaEquipoTecnico.class);
    }
    //endregion
    
    @MemberOrder(sequence = "4")
    public List<FallaEquipoTecnico> buscarRegistroDeMovimientoPorOrden(final OrdenServicio orden)
  {
        return container.allMatches(
                new QueryDefault<>(
                		FallaEquipoTecnico.class,
                        "registroDeMovimientoPorOrden",
                        "orden", orden));
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
