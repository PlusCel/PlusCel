
package domainapp.dom.modules.atencion;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;

@DomainService(repositoryFor = FallaEquipoTecnico.class)
@DomainServiceLayout(menuOrder = "5" , named="Tecnico")
public class FallaEquipoTecnicoRepositorio {
	
    //region > create (action)
    @MemberOrder(sequence = "1")
    public FallaEquipoTecnico altaFallaPorEquipoPorTecnico(
    		final @ParameterLayout(named="Tipo de Falla") TipoFalla tipoFalla,
    		final @ParameterLayout(named="Falla descripcion", multiLine=10) String fallaDesc,
            final @ParameterLayout(named="Equipo") Equipo equipo,
            final @ParameterLayout(named="Tecnico") Tecnico tecnico) {
    	
        final FallaEquipoTecnico obj = container.newTransientInstance(FallaEquipoTecnico.class);
        final Falla fallaCel = new Falla();
        fallaCel.setTipoFalla(tipoFalla);
        fallaCel.setDescripcion(fallaDesc);
        obj.setFalla(fallaCel);
        obj.setEquipo(equipo);
        obj.setTecnico(tecnico);
        container.persistIfNotAlready(obj);
        return obj;
    }
      
    @MemberOrder(sequence = "3")
    public List<FallaEquipoTecnico> listarTodos() {
        return container.allInstances(FallaEquipoTecnico.class);
    }
    //endregion
    
    @MemberOrder(sequence = "4")
    public List<FallaEquipoTecnico> buscarFallaXEquipo(Tecnico tecnico)
  {
        return container.allMatches(
                new QueryDefault<>(
                		FallaEquipoTecnico.class,
                        "listadoFallaPorEquipo",
                        "Tecnico", tecnico));
    }
    
//endregion

    //region > injected services
    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion
 
}
