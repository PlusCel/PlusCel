package domainapp.dom.modules.atencion;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;

import domainapp.dom.modules.servicios.E_estado;
import domainapp.dom.modules.servicios.E_estadoGarantia;

@DomainService(repositoryFor = SumaTecnico.class)
@DomainServiceLayout(menuOrder = "6" , named="Liquidaciones/Resultados")


public class SumaTecnicoRepo {
	
	 public SumaTecnico ingresarLiquidacionPorTecnico(   
    		      
             final @ParameterLayout(named="Tecnico") Tecnico tecnico,
             final @ParameterLayout(named="FechaDesde") LocalDate  fechaDesde,
             final @ParameterLayout(named="FechaHasta") LocalDate  fechaHasta
           
    			) {

        final SumaTecnico obj = container.newTransientInstance(SumaTecnico.class);
      
  
        obj.setTecnico(tecnico);
       
      obj.setFechaHora( LocalDate.now());
        
        obj.setFechaDesde(fechaDesde);
        obj.setFechaHasta(fechaHasta);
        
        double importe2=0;
        OrdenServicioRepositorio oOrdenRepo = new OrdenServicioRepositorio();
        List<OrdenServicio> lista=oOrdenRepo.liquidacionPorTecnicoII(tecnico, fechaDesde, fechaHasta);
        for (OrdenServicio oOrden : lista) {
    	   importe2=importe2+oOrden.getComisionTecnico();
    	   
    	  }
          obj.setImporte(importe2);
    
        container.persistIfNotAlready(obj);
        
      
        return obj;
    }
	 
	 //Busco para liquidar por tecnico	  
	  @MemberOrder(sequence = "8")
	    public List<SumaTecnico> busquedaLiquidacionPorTecnico(
	    		@ParameterLayout(named="Fecha Desde")LocalDate fechaDesde, 
	    		@ParameterLayout(named="Fecha Hasta")LocalDate fechaHasta)
	      {
	       
	            return container.allMatches(
	                    new QueryDefault<>(
	                    		SumaTecnico.class,
	                            "ListarLiquidacionTecnicos",
	                            "fechaDesde" ,fechaDesde,"fechaHasta",fechaHasta));
	        }	
	 
		/**
		 * Inyección del Contenedor.
		 */
		@javax.inject.Inject
		private static DomainObjectContainer container;

}
