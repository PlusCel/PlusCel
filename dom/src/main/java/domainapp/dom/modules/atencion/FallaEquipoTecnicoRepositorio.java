
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
@DomainService(repositoryFor = EquipoTecnico.class)
@DomainServiceLayout(menuOrder = "10" , named="FallaEquipoTecnico")
public class FallaEquipoTecnicoRepositorio {
	
	
    //region > create (action)
    @MemberOrder(sequence = "1")
    public FallaEquipoTecnico Alta(
    		final @ParameterLayout(named="Falla") String falla,
            final @ParameterLayout(named="Equipo") Equipo equipo,
            final @ParameterLayout(named="Tecnico") Tecnico tecnico) {
    	
        final FallaEquipoTecnico obj = container.newTransientInstance(FallaEquipoTecnico.class);
        obj.setFalla(falla);
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
    public List<FallaEquipoTecnico> listadoFallaPorEquipo(Tecnico tecnico)
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