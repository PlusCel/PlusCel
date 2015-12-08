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

@DomainService(repositoryFor = TipoFalla.class)
@DomainServiceLayout(menuOrder = "1" , named="Gestion")

public class TipoFallaRepositorio {

    //region > create (action)
    @MemberOrder(sequence = "1")
    public TipoFalla ingresarTipoFalla(
            final @ParameterLayout(named="Descripcion") String descripcion
  
            ) {
    	  	final TipoFalla obj = container.newTransientInstance(TipoFalla.class);
    		obj.setDescripcion(descripcion);
            container.persistIfNotAlready(obj);
            return obj;
    }

    //endregion
        
	 //region > listAll (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "2")
    public List<TipoFalla> listarTodosLosTiposFallas() {
        return container.allInstances(TipoFalla.class);
    }
    //endregion
    

    //region > injected services

    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion
	
	
}
