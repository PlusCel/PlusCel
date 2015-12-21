package domainapp.dom.modules.atencion;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;

@DomainService(repositoryFor = ResultadoMensual.class)
@DomainServiceLayout(menuOrder = "6" , named="Liquidaciones/Resultados")


public class ResultadoMensualRepo {
	
	 public ResultadoMensual ingresarResultadoMensual(   
    		      
            
             final @ParameterLayout(named="FechaDesde") LocalDate  fechaDesde,
             final @ParameterLayout(named="FechaHasta") LocalDate  fechaHasta
           
    			) {

        final ResultadoMensual obj = container.newTransientInstance(ResultadoMensual.class);
      
  
              
        obj.setFechaHora( LocalDate.now());
        
        obj.setFechaDesde(fechaDesde);
        obj.setFechaHasta(fechaHasta);
        
        double importe2=0;
        OrdenServicioRepositorio oOrdenRepo = new OrdenServicioRepositorio();
        List<OrdenServicio> lista=oOrdenRepo.liquidacionMensual(fechaDesde, fechaHasta);
        for (OrdenServicio oOrden : lista) {
    	   importe2=importe2+oOrden.getImporte();
    	   
    	  }
          obj.setImporte(importe2);
    
        container.persistIfNotAlready(obj);
        
      
        return obj;
    }
	 
	 //Busco para Resultado mensual  
	  @MemberOrder(sequence = "8")
	 
	    public List<ResultadoMensual> busquedaResultadoMensual(
	    		@ParameterLayout(named="Fecha Desde")LocalDate fechaDesde, 
	    		@ParameterLayout(named="Fecha Hasta")LocalDate fechaHasta)
	      {
	       
	            return container.allMatches(
	                    new QueryDefault<>(
	                    		ResultadoMensual.class,
	                            "ListarLiquidacionMensual",
	                            "fechaDesde" ,fechaDesde,"fechaHasta",fechaHasta));
	        }	
	 
		/**
		 * Inyección del Contenedor.
		 */
		@javax.inject.Inject
		private static DomainObjectContainer container;

}
