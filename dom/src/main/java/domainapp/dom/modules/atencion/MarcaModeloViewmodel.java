package domainapp.dom.modules.atencion;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Collection;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.RenderType;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

import domainapp.dom.modules.atencion.Cliente;
import domainapp.dom.modules.atencion.ClienteRepositorio;
import domainapp.dom.modules.atencion.Marca;
import domainapp.dom.modules.atencion.MarcaRepositorio;
import domainapp.dom.modules.atencion.Modelo;
import domainapp.dom.modules.atencion.ModeloRepositorio;
import domainapp.dom.modules.atencion.OrdenServicio;
import domainapp.dom.modules.atencion.OrdenServicioRepositorio;

@MemberGroupLayout(columnSpans = { 4, 0, 0, 8 })
public class MarcaModeloViewmodel extends AbstractViewModel {

	private String title;
	private String memento;
	private String fitrlomarca;
	
	// {{ Marca (property)
	private String marca;

	@MemberOrder(sequence = "1")
	public String getMarca() {
		return marca;
	}

	public void setMarca(final String marca) {
		this.marca = marca;
	}
	// }}

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
	  public boolean getMarca1() {
			return MarcaRepositorio.listarTodo().contains(marca);
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
	}
	//............

	@Override
	public void viewModelInit(String memento) {
		this.memento = memento;
		
		Memento newMemento = mementoService.parse(memento);
		//titulo, marca
		this.title = newMemento.get("titulo", String.class);
		setMarca(newMemento.get("marca", String.class));
	
		try{
			
		inicializarListModelos();
					}catch(Exception e){
						System.out.println(e.getMessage());
					}

				}
				
				@Programmatic
				public void inicializarListModelos(){
					setModelosList(ModeloRepositorio.listarTodos());
				}
				
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
