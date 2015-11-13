package domainapp.dom.modules.atencion;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

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




@MemberGroupLayout(columnSpans = { 4, 0, 0, 8 })
public class MarcaModeloViewmodel extends AbstractViewModel {

	private String title;
	private String memento;
	
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
	

	@Override
	public void viewModelInit(String memento) {
		this.memento = memento;
		
		Memento newMemento = mementoService.parse(memento);
		//titulo, alumno, ciclo, curso, division, dni, periodo, turno
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
		setModelosList(modeloRepositorio.listarTodos());
	}
	
	
	@Override
	public String viewModelMemento() {
		return memento;
	}
	
	
	@javax.inject.Inject
	DomainObjectContainer container;
	
	@javax.inject.Inject
    MementoService mementoService;
	
	@javax.inject.Inject
	ModeloRepositorio modeloRepositorio;
	
	@javax.inject.Inject
	MarcaRepositorio marcaRepositorio;
	
	
	
}
