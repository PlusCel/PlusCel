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
import org.joda.time.LocalDate;

import domainapp.dom.modules.servicios.Direccion;
import domainapp.dom.modules.servicios.Localidad;
import domainapp.dom.modules.servicios.Localidad.E_localidades;
import domainapp.dom.modules.servicios.Persona.E_nacionalidad;
import domainapp.dom.modules.servicios.Persona.E_sexo;

@DomainService(repositoryFor = Equipo.class)
@DomainServiceLayout(menuOrder = "10" , named="Equipo")

public class EquipoRepositorio {
	
	//region > listAll (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "1")
    public List<Equipo> listAll() {
        return container.allInstances(Equipo.class);
    }
    //endregion

    //region > findByApellido (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "2")
    public List<Equipo> findByMarca(
            @ParameterLayout(named="Marca")
            final String marca
    ) {
        return container.allMatches(
                new QueryDefault<>(
                        Equipo.class,
                        "findByMarca",
                        "marca", marca));
    }
    //endregion

    //region > create (action)
    @MemberOrder(sequence = "3")
    public Equipo create(
            final @ParameterLayout(named="Marca") String marca,
            final @ParameterLayout(named="Modelo") String modelo           
            
    		) {
        final Equipo obj = container.newTransientInstance(Equipo.class);
        obj.setMarca(marca);
        container.persistIfNotAlready(obj);
        return obj;
    }

    //endregion

    //region > injected services

    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion

}
