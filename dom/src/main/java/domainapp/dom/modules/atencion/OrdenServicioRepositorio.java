package domainapp.dom.modules.atencion;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.joda.time.DateTime;

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
             final @ParameterLayout(named="Importe") double importe
    			) {
    	
        final OrdenServicio obj = container.newTransientInstance(OrdenServicio.class);
        obj.setEquipo(equipo);       
        obj.setCliente(cliente);
        obj.setFechaHora(fechaHora);
        obj.setFalla(falla);
        obj.setImporte(importe);
        
        container.persistIfNotAlready(obj);
        return obj;
    }
	
	@MemberOrder(sequence = "2")
    public List<OrdenServicio> listarTodos() {
        return container.allInstances(OrdenServicio.class);
    }
    
    //region > injected services
    @javax.inject.Inject 
    DomainObjectContainer container;
}
