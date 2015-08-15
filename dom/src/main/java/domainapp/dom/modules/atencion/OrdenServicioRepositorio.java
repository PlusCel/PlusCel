package domainapp.dom.modules.atencion;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.DateTime;

import domainapp.dom.modules.servicios.E_estado;

import java.util.Date;

@SuppressWarnings("deprecation")
@DomainService(repositoryFor = OrdenServicio.class)
@DomainServiceLayout(menuOrder = "11" , named="Orden de Servicio")

public class OrdenServicioRepositorio {
	
	public String iconName() {
		return "OrdenServicio";
	}
	
	@MemberOrder(sequence = "1")
    public OrdenServicio Alta(   
    		 final @ParameterLayout(named="Equipo") Equipo equipo,            
             final @ParameterLayout(named="Cliente") Cliente cliente,
             final @ParameterLayout(named="Fecha") Date  fechaHora,
             final @ParameterLayout(named="Falla", multiLine=10) String falla,
             final @ParameterLayout(named="Importe") double importe,
             final @ParameterLayout(named="Estado") E_estado estado
    			) {
    	
        final OrdenServicio obj = container.newTransientInstance(OrdenServicio.class);
        obj.setEquipo(equipo);       
        obj.setCliente(cliente);
        obj.setFechaHora(fechaHora);
        obj.setFalla(falla);
        obj.setImporte(importe);
        obj.setEstado(estado);  
        
        container.persistIfNotAlready(obj);
        return obj;
    }
	
	@MemberOrder(sequence = "2")
    public List<OrdenServicio> listarTodos() {
        return container.allInstances(OrdenServicio.class);
    }
    
	  // region > buscarPorEstado (action)
	  @Action(
	          semantics = SemanticsOf.SAFE
	 )
	  @ActionLayout(
	          bookmarking = BookmarkPolicy.AS_ROOT
	  )
	  @MemberOrder(sequence = "3")
	  public List<OrdenServicio> buscarPorEstado(
	          @ParameterLayout(named="Estado")
	        final E_estado estado
	  ) {
	      return container.allMatches(
	              new QueryDefault<>(
	            		  OrdenServicio.class,
	                      "buscarPorEstado",
	                      "estado", estado));
	  }
	 //endregion
	
	
	
    //region > injected services
    @javax.inject.Inject 
    DomainObjectContainer container;
}
