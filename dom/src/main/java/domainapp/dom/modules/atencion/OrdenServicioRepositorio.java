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
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;

import domainapp.dom.modules.servicios.E_estado;
import domainapp.dom.modules.servicios.E_estadoGarantia;


@DomainService(repositoryFor = OrdenServicio.class)
@DomainServiceLayout(menuOrder = "6" , named="Orden de Servicio")

public class OrdenServicioRepositorio {
	
	public String iconName() {
		return "fa fa-road";
	}
	
	@MemberOrder(sequence = "1")
    public OrdenServicio ingresarOrdenDeServicio(   
    		 final @ParameterLayout(named="Cliente") Cliente cliente,
    		 final @ParameterLayout(named="Equipo" ) Equipo equipo,             
             final @ParameterLayout(named="Tecnico") @Parameter(optionality=Optionality.OPTIONAL) Tecnico tecnico,
             final @ParameterLayout(named="Fecha") LocalDate  fechaHora,
             final @ParameterLayout(named="Tipo de Falla") TipoFalla tipofalla,
             final @ParameterLayout(named="Descripcion de la Falla", multiLine=8) @Parameter(optionality=Optionality.OPTIONAL) String falla,
             final @ParameterLayout(named="Importe") double importe,
             final @ParameterLayout(named="Comision Tecnico") double comisionTecnico,
             final @ParameterLayout(named="Estado") E_estado estado,
             final @ParameterLayout(named="Garantia") E_estadoGarantia garantia
    			) {

        final OrdenServicio obj = container.newTransientInstance(OrdenServicio.class);
        obj.setCliente(cliente);
        obj.setEquipo(equipo);
        obj.setTecnico(tecnico);
        obj.setFechaHora(fechaHora);
        obj.setTipoFalla(tipofalla);
        obj.setFalla(falla);
        obj.setImporte(importe);
        obj.setComisionTecnico(comisionTecnico);
        obj.setEstado(estado);   
        obj.setGarantia(garantia); 

        container.persistIfNotAlready(obj);
        container.informUser("La orden de servicio sido cargado correctamente");
        return obj;
    }
	
	// region > buscarTodos (action)
		@MemberOrder(sequence = "2")
	    public List<OrdenServicio> listarTodas() {
	        return container.allInstances(OrdenServicio.class);
	    }
		
	//Inicio Buscar ultimos
		@MemberOrder(sequence = "3")
		public List<OrdenServicio> listar10Ultimas() {
			return container.allMatches(new QueryDefault<OrdenServicio>(OrdenServicio.class,
		      "buscarOrdenadasPorFecha").withCount(10));
		}
	//Fin Buscar Ultimos
	
	//Busco por por numero orden de servicio	  
	  @MemberOrder(sequence = "4")
	    
	    public List<OrdenServicio> buscarPorNumero(
	            @ParameterLayout(named="Numero Orden de Servicio")
	            final long numero
	    ) {
	        return container.allMatches(
	                new QueryDefault<>(
	                		OrdenServicio.class,
	                        "buscarPorNumero",
	                        "numero", numero));
	    }
	
	  // region > buscarPorEstado (action)
	  @Action(
	          semantics = SemanticsOf.SAFE
	 )
	  @ActionLayout(
	          bookmarking = BookmarkPolicy.AS_ROOT
	  )
	  @MemberOrder(sequence = "5")
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
	  @MemberOrder(sequence = "6")
	    public List<OrdenServicio> buscarPorReparaciones(Equipo equipo,
	    		@ParameterLayout(named="Fecha Desde")LocalDate fechaDesde,
	    		@ParameterLayout(named="Fecha Hasta")LocalDate fechaHasta, Cliente cliente)
	      {
	       
	            return container.allMatches(
	                    new QueryDefault<>(
	                    		OrdenServicio.class,
	                            "BuscarReparacionesFiltro",
	                            "equipo", equipo,
	                             "fechaDesde" ,fechaDesde,"fechaHasta",fechaHasta,"cliente", cliente));
	        }
		
		//Busco equipos asignados por tecnicos	  
		  @MemberOrder(sequence = "7")
		    public List<OrdenServicio> buscarOrdenServicioPorTecnico(Tecnico tecnico)
		      {
		       
		            return container.allMatches(
		                    new QueryDefault<>(
		                    		OrdenServicio.class,
		                    		"OrdenesServiciosPorTecnico",
		                            "tecnico", tecnico));
		        }	
		
		//Busco para liquidar por tecnico	  
		  @MemberOrder(sequence = "8")
		    public String liquidacionPorTecnico(Tecnico tecnico, 
		    		@ParameterLayout(named="Fecha Desde")LocalDate fechaDesde, 
		    		@ParameterLayout(named="Fecha Hasta")LocalDate fechaHasta)
		      {
		       
		            return container.allMatches(
		                    new QueryDefault<>(
		                    		OrdenServicio.class,
		                            "LiquidarReparacionesPorTecnicoTotal",
		                            "tecnico", tecnico,
		                            "fechaDesde" ,fechaDesde,"fechaHasta",fechaHasta)).get(0).toString();
		        }	
		  
		//Busco para liquidar por tecnico	  
		  @MemberOrder(sequence = "8")
		    public List<OrdenServicio> liquidacionPorTecnicoII(Tecnico tecnico, 
		    		@ParameterLayout(named="Fecha Desde")LocalDate fechaDesde, 
		    		@ParameterLayout(named="Fecha Hasta")LocalDate fechaHasta)
		      {
		       
		            return container.allMatches(
		                    new QueryDefault<>(
		                    		OrdenServicio.class,
		                            "LiquidarReparacionesPorTecnico",
		                            "tecnico", tecnico,
		                            "fechaDesde" ,fechaDesde,"fechaHasta",fechaHasta));
		        }	
		  
 
		@Programmatic
		public static List<OrdenServicio> sinArreglo() {
			
			final List<OrdenServicio> lista = container
					.allMatches(new QueryDefault<OrdenServicio>(OrdenServicio.class,
							"sinArreglo"));
			if (lista.isEmpty())
				container.informUser("No hay equipos en espera de ser retirados.");
			return lista;
		}
		
		@Programmatic
		public static List<OrdenServicio> reparados() {
			final List<OrdenServicio> lista = container
					.allMatches(new QueryDefault<OrdenServicio>(OrdenServicio.class,
							"reparados"));
			if (lista.isEmpty())
				container.informUser("No hay equipos en espera de ser retirados.");
			return lista;
		}
		
		@Programmatic
		public static List<OrdenServicio> sinRevisar() {
			final List<OrdenServicio> lista = container
					.allMatches(new QueryDefault<OrdenServicio>(OrdenServicio.class,
							"sinRevisar"));
			if (lista.isEmpty())
				container.informUser("No hay equipos en espera de ser revisados.");
			return lista;
		}
		//Busco equipos asignados por tecnicos	  
		@Programmatic
		    public List<OrdenServicio> buscarOrdenServicioPorTecnicoa(String apellido)
		      {
			final List<OrdenServicio> lista = container
					.allMatches(new QueryDefault<OrdenServicio>(OrdenServicio.class,
							"buscarOrdenServicioPorTecnicoa","apellido",apellido));
			if (lista.isEmpty())
				container.informUser("No hay equipos en espera de ser revisados.");
			return lista;
		        }	
		//Busco equipos asignados por clientes	  
				//@Programmatic
				    public List<OrdenServicio> buscarOrdenServicioPorCliente(String dni)
				      {
					final List<OrdenServicio> lista = container
							.allMatches(new QueryDefault<OrdenServicio>(OrdenServicio.class,
									"buscarOrdenServicioPorCliente","dni",Integer.parseInt(dni)));
					if (lista.isEmpty())
						container.informUser("No hay equipos por cliente.");
					return lista;
				        }	
		
		
		
		/**
		 * Inyección del Contenedor.
		 */
		@javax.inject.Inject
		private static DomainObjectContainer container;
}
