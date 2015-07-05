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

import domainapp.dom.modules.servicios.E_estadoParte;
;
@DomainService(repositoryFor = EstadoEquipo.class)
@DomainServiceLayout(menuOrder = "10" , named="EstadoEquipo")

public class EstadoEquipoRepositorio {
	
	//region > listarTodos (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "1")
   public List<EstadoEquipo> listarTodos() {
        return container.allInstances(EstadoEquipo.class);
    }
    //endregion

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

    //region > create (action)
    @MemberOrder(sequence = "3")
    public EstadoEquipo Alta(
            final @ParameterLayout(named="Equipo") Equipo equipo,    		           
            final @ParameterLayout(named="Memoria") E_estadoParte memoria, 
            final @ParameterLayout(named="Chip") E_estadoParte chip,  
            final @ParameterLayout(named="Observación") String observacion   
            
    		) {
        final EstadoEquipo obj = container.newTransientInstance(EstadoEquipo.class);
        obj.setEquipo(equipo);
        obj.setMemoria(memoria);
        obj.setChip(chip);
        obj.setObservacion(observacion);
        container.persistIfNotAlready(obj);
        return obj;
    }

    //endregion

    //region > injected services

    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion

}