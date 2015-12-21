package domainapp.dom.modules.atencion;

import java.util.List;

import javax.jdo.annotations.Column;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.RenderType;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import org.joda.time.LocalDate;

import domainapp.dom.modules.atencion.Cliente;
import domainapp.dom.modules.atencion.Equipo;
import domainapp.dom.modules.atencion.Marca;
import domainapp.dom.modules.atencion.Modelo;
import domainapp.dom.modules.atencion.OrdenServicio;
import domainapp.dom.modules.atencion.OrdenServicioRepositorio;
import domainapp.dom.modules.atencion.Tecnico;

@DomainServiceLayout(menuOrder = "8" , 
menuBar = DomainServiceLayout.MenuBar.SECONDARY
,named="Ordenes Servicio por Cliente")

@MemberGroupLayout(columnSpans = { 4, 0, 0, 9 })
public class OrdenServicioClienteViewmodel extends AbstractViewModel {

	private String title;
	private String memento;
	private String dni;

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
		@MemberOrder(sequence = "2")
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
	
	//Equipos por cliente
	
    @MemberOrder(sequence = "3")
    @CollectionLayout(
            render = RenderType.EAGERLY
    )
	public List<OrdenServicio> getOrdenServicioPorCliente() {
    	OrdenServicioRepositorio oOrdenRepo =new OrdenServicioRepositorio();
		return oOrdenRepo.buscarOrdenServicioPorCliente(dni);
	}
	//.............	

	@Override
	public void viewModelInit(String mementoString) {
		this.memento = mementoString;
		
		Memento memento = mementoService.parse(mementoString);
		setCliente(memento.get("dni", String.class));
		title = memento.get("titulo", String.class); //Intervalo de fechas
		dni=memento.get("dni", String.class);
		
	}
	
	// {{ Asistencia (property)
	private String cliente;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public String getCliente() {
		return cliente;
	}

	public void setCliente(final String cliente) {
		this.cliente = cliente;
	}
    									
	@javax.inject.Inject
	DomainObjectContainer container;
		
	@javax.inject.Inject
	MementoService mementoService;
	
	@Override
	public String viewModelMemento() {
		return memento;
	}	
}
