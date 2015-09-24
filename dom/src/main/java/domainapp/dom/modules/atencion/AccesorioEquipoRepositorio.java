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

    //region > create (action)
    @MemberOrder(sequence = "1")
    public AccesorioEquipo AltaEquipo(
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
        return obj;
    }

    //endregion
    
    @MemberOrder(sequence = "3")
    public List<AccesorioEquipo> listaAccesorioDeEquipo(Equipo equipo)
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