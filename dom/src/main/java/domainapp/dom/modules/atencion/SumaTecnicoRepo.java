/*
 * This is software for administration and management of mobile equipment repair
 *
 * Copyright ( C ) 2015 , Pluscel
 *
 * This program is free software ; you can redistribute it and / or
 * Modify it under the terms of the GNU General Public License
 * As published by the Free Software Foundation ; either version 2
 * Of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * But WITHOUT ANY WARRANTY; without even the implied warranty
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. Boil
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * Along with this program ; if not, write to the Free Software
 *
 *
 * Foundation , Inc. , 51 Franklin Street, Fifth Floor , Boston, MA 02110-1301 , USA .
 */
package domainapp.dom.modules.atencion;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;

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
