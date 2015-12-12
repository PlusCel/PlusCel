package domainapp.dom.modules.atencion;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import domainapp.dom.modules.servicios.E_estadoPresupuesto;
import org.joda.time.LocalDate;
@DomainService(repositoryFor = Presupuesto.class)
@DomainServiceLayout(menuOrder = "6" , named="Orden de Servicio")

public class PresupuestoRepositorio {
	
	 //region > create (action)
    @MemberOrder(sequence = "1")
    public Presupuesto ingresarPresupuesto(   
    		 final @ParameterLayout(named="Equipo") Equipo equipo,            
             final @ParameterLayout(named="Cliente") Cliente cliente,
             final @ParameterLayout(named="Fecha") LocalDate  fechaHora ,
             final @ParameterLayout(named="Diagnostico", multiLine=10) String diagnostico,
             final @ParameterLayout(named="ReparacionRequerida")  String reparacionRequerida,
             final @ParameterLayout(named="Importe") double importe,
             final @ParameterLayout(named="Observacion", multiLine=10) String observacion,
             final @ParameterLayout(named="Estado") E_estadoPresupuesto estado,
             final @ParameterLayout(named="Garantia(Meses)") String  garantia   ) {
    	
        final Presupuesto obj = container.newTransientInstance(Presupuesto.class);
         obj.setEquipo(equipo);       
        obj.setCliente(cliente);
        obj.setFechaHora(fechaHora);
        obj.setDiagnostico(diagnostico);
        obj.setReparacionRequerida(reparacionRequerida);
        obj.setImporte(importe);
        obj.setObservacion(observacion);
        obj.setEstadoPresupuesto(estado);
        obj.setGarantia(garantia);
        container.persistIfNotAlready(obj);
        return obj;
    }
       	
    
    @MemberOrder(sequence = "2")
    public List<Presupuesto> listarTodos() {
        return container.allInstances(Presupuesto.class);
    }
    //endregion
	
    
    //region > injected services
    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion
}
