package domainapp.dom.modules.atencion;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.QueryDefault;


@DomainService(repositoryFor = TipoFalla.class)
@DomainServiceLayout(menuOrder = "1" , named="Gestion")

public class TipoFallaRepositorio {

	 //region > listAll (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "1")
    public List<TipoFalla> listarTodos() {
        return container.allInstances(TipoFalla.class);
    }
    //endregion


    //region > create (action)
    @MemberOrder(sequence = "2")
    public TipoFalla altaTipoFalla(
            final @ParameterLayout(named="Descripcion") String descripcion
  
            ) {
    	  	final TipoFalla obj = container.newTransientInstance(TipoFalla.class);
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
