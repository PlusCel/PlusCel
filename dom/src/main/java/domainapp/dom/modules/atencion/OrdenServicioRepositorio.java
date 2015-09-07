package domainapp.dom.modules.atencion;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Join;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DescribedAs;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;

import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty.Type;

import domainapp.dom.modules.servicios.E_estado;
import domainapp.dom.modules.servicios.E_formato;
import domainapp.dom.modules.servicios.GenerarReporte;
import net.sf.jasperreports.engine.JRException;



@SuppressWarnings("deprecation")
@DomainService(repositoryFor = OrdenServicio.class)
@DomainServiceLayout(menuOrder = "11" , named="Orden de Servicio")

public class OrdenServicioRepositorio {
	
	public String iconName() {
		return "OrdenServicio";
	}
	
	@MemberOrder(sequence = "1")
    public OrdenServicio Alta(   
    		 final @ParameterLayout(named="Cliente") Cliente cliente,
    		 final @ParameterLayout(named="Equipo" ) Equipo equipo,             
             final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Tecnico") Tecnico tecnico,
             final @ParameterLayout(named="Fecha") LocalDate  fechaHora,
             final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Falla", multiLine=10) String falla,
             final @ParameterLayout(named="Importe") double importe,
             final @ParameterLayout(named="Comision Tecnico") double comisionTecnico,
             final @ParameterLayout(named="Estado") E_estado estado
    			) {
    	
        final OrdenServicio obj = container.newTransientInstance(OrdenServicio.class);
        obj.setCliente(cliente);
        obj.setEquipo(equipo);
        obj.setTecnico(tecnico);
        obj.setFechaHora(fechaHora);
        obj.setFalla(falla);
        obj.setImporte(importe);
        obj.setComisionTecnico(comisionTecnico);
        obj.setEstado(estado);   

        container.persistIfNotAlready(obj);
        return obj;
    }
	
	@MemberOrder(sequence = "2")
    public List<OrdenServicio> buscarTodos() {
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
	  @MemberOrder(sequence = "4")
	    public List<OrdenServicio> buscarPorReparaciones(Equipo equipo,
	    		@ParameterLayout(named="Fecha Desde")LocalDate fechaDesde,@ParameterLayout(named="Fecha Hasta")LocalDate fechaHasta, Cliente cliente)
	      {
	       
	            return container.allMatches(
	                    new QueryDefault<>(
	                    		OrdenServicio.class,
	                            "BuscarReparacionesFiltro",
	                            "equipo", equipo,
	                             "fechaDesde" ,fechaDesde,"fechaHasta",fechaHasta,"cliente", cliente));
	        }
//Busco para liquidar por tecnico	  
	  @MemberOrder(sequence = "5")
	    public List<OrdenServicio> liquidacionPorTecnico(Tecnico tecnico, 
	    		@ParameterLayout(named="Estado") final E_estado estado,
	    		LocalDate fechaDesde, LocalDate fechaHasta)
	      {
	       
	            return container.allMatches(
	                    new QueryDefault<>(
	                    		OrdenServicio.class,
	                            "LiquidarReparacionesPorTecnico",
	                            "tecnico", tecnico,
	                            "estado", estado,
	                            "fechaDesde" ,fechaDesde,"fechaHasta",fechaHasta));
	        }
/* //Imprimir reporte Liquidacion por tecnico
	   
	  	@Join
		@Element(dependent = "true")
		private List<OrdenServicio> OrdenServicioList = new ArrayList<OrdenServicio>();

		//@Render(Type.EAGERLY)
		@MemberOrder(sequence = "1")
		@Named("Analisis de Asistencia por Alumno")
		public List<OrdenServicio> getOrdenServicioList() {
			return OrdenServicioList;
		}

		public void setOrdenServicioList(
				final List<OrdenServicio> OrdenServicioList) {
			this.OrdenServicioList = OrdenServicioList;
		}
	  
	  	@Named("Imprimir Liquidacion por Tecnico")
		//@DescribedAs(value = "El archivo se almacenará en el directorio 'reportes' del proyecto")
		public String elegirFormato(final @Named("Formato") E_formato formato) throws JRException{
			return imprimirReporte(formato);		
		}
		
		public E_formato default0ElegirFormato(final @Named("Formato") E_formato formato){
			return E_formato.PDF;		
		}
		
		public String imprimirReporte(E_formato format) throws JRException{
			List<Object> objectsReport = new ArrayList<Object>();
			
			for(OrdenServicio a: getOrdenServicioList()){
				OrdenServicio ordenservicio = new OrdenServicio();

				ordenservicio.setTecnico(a.getTecnico());
				ordenservicio.setFechaHora(a.getFechaHora());
				ordenservicio.setFalla(a.getFalla());
				ordenservicio.setEstado(a.getEstado());
				ordenservicio.setComisionTecnico(a.getComisionTecnico());
				
				objectsReport.add(ordenservicio);
			}
			
			String nombreArchivo = "reportes/"; 
			GenerarReporte.generarReporte("liquidaciontecnico.jrxml", objectsReport, format, nombreArchivo);
			return "Liquidacion por Tecnico Generado.";
		}*/
	  
    @javax.inject.Inject 
    DomainObjectContainer container;
}
