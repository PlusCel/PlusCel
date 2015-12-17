package domainapp.dom.modules.atencion;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Collection;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.RenderType;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import org.joda.time.LocalDate;

import domainapp.dom.modules.atencion.Cliente;
import domainapp.dom.modules.atencion.ClienteRepositorio;
import domainapp.dom.modules.atencion.Equipo;
import domainapp.dom.modules.atencion.Marca;
import domainapp.dom.modules.atencion.MarcaRepositorio;
import domainapp.dom.modules.atencion.Modelo;
import domainapp.dom.modules.atencion.ModeloRepositorio;
import domainapp.dom.modules.atencion.OrdenServicio;
import domainapp.dom.modules.atencion.OrdenServicioRepositorio;
import domainapp.dom.modules.atencion.Tecnico;


@DomainServiceLayout(menuOrder = "8" , 
menuBar = DomainServiceLayout.MenuBar.SECONDARY
,named="Vistas Rapidas")

@MemberGroupLayout(columnSpans = { 4, 0, 0, 9 })
public class NovedadesViewmodel extends AbstractViewModel {

	private String title;
	private String memento;
	
	// {{ Marca (property)
	//private String marca;

	/*@MemberOrder(sequence = "1")
	public String getMarca() {
		return marca;
	}

	public void setMarca(final String marca) {
		this.marca = marca;
	}*/
	// Agrego cliente datos basicos
	
	@MemberOrder(sequence = "1")
    public Cliente altaRapidaCliente(
            final  @ParameterLayout(named="Apellido") @Parameter(regexPattern = domainapp.dom.modules.validador.ValidadorCaracteres.ValidacionLetras.PERMITIDOS, maxLength = 40) String apellido,
            final @ParameterLayout(named="Nombre") @Parameter(regexPattern = domainapp.dom.modules.validador.ValidadorCaracteres.ValidacionLetras.PERMITIDOS, maxLength = 40)  String nombre,
            final @ParameterLayout(named="Dni")@Parameter(regexPattern = domainapp.dom.modules.validador.ValidadorCaracteres.ValidacionNumerica.PERMITIDOS, maxLength = 12)  int dni,
            final @ParameterLayout(named="Teléfono") @Parameter(regexPattern = domainapp.dom.modules.validador.ValidadorCaracteres.ValidacionTel.PERMITIDOS, maxLength = 30) String telefono
            
    		) {
        final Cliente obj = container.newTransientInstance(Cliente.class);
        
        obj.setNombre(nombre.toUpperCase());
        obj.setApellido(apellido.toUpperCase());
        obj.setDni(dni);
        container.persistIfNotAlready(obj);
        return obj;
    }

    //clientes basicos
	
	// Agrego Equipo datos basicos
	
		@MemberOrder(sequence = "1")
		public Equipo altaRapidaEquipo(
	    		
	            final @ParameterLayout(named="Marca") Marca marca,    		           
	            final @ParameterLayout(named="Modelo") Modelo modelo,            
	            final @ParameterLayout(named="IMEI") @Parameter(regexPattern = domainapp.dom.modules.validador.ValidadorCaracteres.ValidacionNumerica.PERMITIDOS, maxLength = 20) String imei   
	            
	    		) {
	        final Equipo obj = container.newTransientInstance(Equipo.class);
	        obj.setMarca(marca);
	        obj.setModelo(modelo);    
	        obj.setImei(imei);
	   
	        container.persistIfNotAlready(obj);
	        return obj;
	}

	    //Equipo basicos
		//Agrego OrdenServicio datos basicos
		public OrdenServicio altaOrdenDeServicio(   
	    		 final @ParameterLayout(named="Cliente") Cliente cliente,
	    		 final @ParameterLayout(named="Equipo" ) Equipo equipo,             
	             final @ParameterLayout(named="Tecnico") @Parameter(optionality=Optionality.OPTIONAL) Tecnico tecnico,
	             final @ParameterLayout(named="Fecha") LocalDate  fechaHora,
	             final @ParameterLayout(named="Falla", multiLine=10) @Parameter(optionality=Optionality.OPTIONAL) String falla,
	             final @ParameterLayout(named="Importe") double importe
	    			) {

	        final OrdenServicio obj = container.newTransientInstance(OrdenServicio.class);
	        obj.setCliente(cliente);
	        obj.setEquipo(equipo);
	        obj.setTecnico(tecnico);
	        obj.setFechaHora(fechaHora);
	        obj.setFalla(falla);
	        obj.setImporte(importe); 

	        container.persistIfNotAlready(obj);
	        return obj;
	    }	
		//OrdenServicio datos basicos

	private List<Modelo> modelosList= new ArrayList<Modelo>();
	  @Collection(
	            editing = Editing.DISABLED
	    )
	    @MemberOrder(sequence = "2")
	    @CollectionLayout(
	            render = RenderType.EAGERLY
	    )
	public List<Modelo> getModelosList() {
		return modelosList;
	}

	public void setModelosList(final List<Modelo> modelosList) {
		this.modelosList = modelosList;
	}
//Marca	
	
	private List<Marca> marcaList= new ArrayList<Marca>();
	  @Collection(
	            editing = Editing.DISABLED
	    )
	    @MemberOrder(sequence = "1")
	    @CollectionLayout(
	            render = RenderType.EAGERLY
	    )
	  public List<Marca> getMarca1() {
			return MarcaRepositorio.listarTodas();
		}
//Marca
	
	//Equipos sin revisar	
	
    @MemberOrder(sequence = "3")
    @CollectionLayout(
            render = RenderType.EAGERLY
    )
	public List<OrdenServicio> getEquiposSinRevisar() {
		return OrdenServicioRepositorio.sinRevisar();
	}
	//.............	
    
	//Cliente	
	
    @MemberOrder(sequence = "4")
    @CollectionLayout(
            render = RenderType.EAGERLY
    )
	public List<Cliente> getCliente() {
		return ClienteRepositorio.ultimosClientes().subList(0, 10);//Filtro los resultados para que devuelva solo 10 clientes
    	//return ClienteRepositorio.ultimosClientes().contains()
    }
	//............

	@Override
	public void viewModelInit(String memento) {
		this.memento = memento;
		
		Memento newMemento = mementoService.parse(memento);
		//titulo, marca
		this.title = newMemento.get("titulo", String.class);
		//setMarca(newMemento.get("marca", String.class));
		
		try{
			
		inicializarListModelos();
					}catch(Exception e){
						System.out.println(e.getMessage());
					}

				}

				@Programmatic
				public void inicializarListModelos(){
					setModelosList(ModeloRepositorio.buscarTodosLosModelos());
				}
							
				
				// }} (end region)
				
				

										
	@javax.inject.Inject
	DomainObjectContainer container;
		
	@javax.inject.Inject
	MementoService mementoService;
		
	@javax.inject.Inject
	ModeloRepositorio modeloRepositorio;
		
	@javax.inject.Inject
	MarcaRepositorio marcaRepositorio;
	
	@Override
	public String viewModelMemento() {
		return memento;
	}	
}
