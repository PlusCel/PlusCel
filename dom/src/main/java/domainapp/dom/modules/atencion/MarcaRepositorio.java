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


@DomainService(repositoryFor = Equipo.class)
@DomainServiceLayout(menuOrder = "1" , named="Gestion")

public class MarcaRepositorio {
	
	//region > listAll (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "2")
    public List<Marca> listarTodo() {
        return container.allInstances(Marca.class);
    }
    //endregion

    //region > findByDescripcion (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "3")
    public List<Marca> buscarPorDescripcion(
            @ParameterLayout(named="Descripcion")
            final String descripcion
    ) {
        return container.allMatches(
                new QueryDefault<>(
                        Marca.class,
                        "findByDescripcion",
                        "descripcion", descripcion));
    }
    //endregion

    //region > create (action)
    @MemberOrder(sequence = "1")
    public Marca altaMarca(
    		final @ParameterLayout(named="Abreviatura") String abreviatura,
            final @ParameterLayout(named="Descripcion") String descripcion
    		) {
        final Marca obj = container.newTransientInstance(Marca.class);
        obj.setAbreviatura(abreviatura);
        obj.setDescripcion(descripcion);  
        container.persistIfNotAlready(obj);
        return obj;
    }

    //endregion

    //region > injected services

    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion

}
