
package domainapp.dom.modules.atencion;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.QueryDefault;
@DomainService
public class EquipoTecnicoRepositorio {
	
	
	 //region > listAll (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "3")
    public List<EquipoTecnico> listarTodos() {
        return container.allInstances(EquipoTecnico.class);
    }
    //endregion

    //region > findBy (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "2")
    public List<Tecnico> buscarPorImei(
            @ParameterLayout(named="Imei")
            final String imei
    ) {
        return container.allMatches(
                new QueryDefault<>(
                        Tecnico.class,
                        "findByImei",
                        "apellido", imei));
    }
    //endregion

    //region > create (action)
    @MemberOrder(sequence = "1")
    public EquipoTecnico Alta(
            final @ParameterLayout(named="Equipo") Equipo equipo,
            final @ParameterLayout(named="Tecnico") Tecnico tecnico) {
    	
        final EquipoTecnico obj = container.newTransientInstance(EquipoTecnico.class);
        obj.setEquipo(equipo);
        obj.setTecnico(tecnico);
        container.persistIfNotAlready(obj);
        return obj;
    }

	public List<EquipoTecnico> listaTecnicoDeEquipo(Equipo equipo){
		
		return container.allMatches(new QueryDefault<EquipoTecnico>(EquipoTecnico.class,
				"TecnicosDeEquipo", 
				"Imei", equipo.getImei()));
	}	

    //endregion

    //region > injected services

    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion
	
 
}
