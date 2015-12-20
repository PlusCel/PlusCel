
package domainapp.dom.modules.atencion;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;
@DomainService(repositoryFor = EquipoTecnico.class)
@DomainServiceLayout(menuOrder = "3" , named="Equipo")
public class EquipoTecnicoRepositorio {
	
    //region > create (action)
    @MemberOrder(sequence = "1")
    public EquipoTecnico ingresarEquipoTecnico(
            final @ParameterLayout(named="Equipo") Equipo equipo,
            final @ParameterLayout(named="Tecnico") Tecnico tecnico) {
    	
        final EquipoTecnico obj = container.newTransientInstance(EquipoTecnico.class);
        obj.setEquipo(equipo);
        obj.setTecnico(tecnico);
        container.persistIfNotAlready(obj);
        container.informUser("El Equipo a sido asignado correctamente al tecnico");
        return obj;
    }
    
    @MemberOrder(sequence = "3")
    public List<EquipoTecnico> listarTodos() {
        return container.allInstances(EquipoTecnico.class);
    }
    //endregion
    
    @MemberOrder(sequence = "4")
    public List<EquipoTecnico> buscarTecnicoDeEquipo(Equipo equipo)
  {
        return container.allMatches(
                new QueryDefault<>(
                		EquipoTecnico.class,
                        "listaTecnicoDeEquipo",
                        "equipo", equipo));
    }


//endregion

    //region > injected services
    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion
 
}
