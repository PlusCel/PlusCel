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
import org.apache.isis.applib.value.Blob;


@DomainService(repositoryFor = Modelo.class)
@DomainServiceLayout(menuOrder = "1" , named="Gestion")

public class ModeloRepositorio {
	
	//region > listAll (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "2")
    public static List<Modelo> listarTodos() {
        return container.allInstances(Modelo.class);
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
    public List<Modelo> buscarPorDescripcion(
            @ParameterLayout(named="Descripcion")
            final String descripcion
    ) {
        return container.allMatches(
                new QueryDefault<>(
                        Modelo.class,
                        "findByDescripcion",
                        "descripcion", descripcion));
    }
    
    
    //endregion
    
    
    public List<Modelo> crearListaModelosXMarca(final Marca marca) {

		return container.allMatches(new QueryDefault<Modelo>(Modelo.class,
				"findByMarca","marca", marca));
    }
     
    //region > create (action)
    @MemberOrder(sequence = "1")
    public Modelo altaModelo(
            final @ParameterLayout(named="Abreviatura") String abreviatura,
            final @ParameterLayout(named="Descripcion", multiLine=10) String descripcion,
            final @ParameterLayout(named="Marca") Marca marca,
            final @ParameterLayout(named="Imagen") Blob attachment
    		) {
        final Modelo obj = container.newTransientInstance(Modelo.class);
        obj.setAbreviatura(abreviatura);
        obj.setMarca(marca);
        obj.setDescripcion(descripcion);
        obj.setAttachment(attachment);
        container.persistIfNotAlready(obj);
        return obj;
    }

    //endregion

    //region > injected services

    @javax.inject.Inject
	static 
    DomainObjectContainer container;

    //endregion

}
