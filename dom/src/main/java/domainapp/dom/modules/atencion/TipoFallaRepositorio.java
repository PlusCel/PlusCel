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
import domainapp.dom.modules.atencion.TipoFalla;
import domainapp.dom.modules.atencion.OrdenServicio;

import java.util.ArrayList;
import java.util.List;
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
    
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "4")
    public List<TipoFalla> buscarTipoFallaXOrden(    @ParameterLayout(named="Numero Orden de Servicio")
    final String descripcion) {
		return container.allMatches(new QueryDefault<TipoFalla>(TipoFalla.class,
				"buscarTipoFallaXOrden","descripcion", descripcion));
    }

    //endregion
    //region > injected services

    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion
	
	
}
