package domainapp.dom.modules.atencion;

import javax.inject.Named;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

import domainapp.dom.modules.atencion.Marca;
import domainapp.dom.modules.atencion.Modelo;

@DomainService
public class MarcaModeloServices {
	
	public String getId() {
		return "MarcaModeloService";
	}

	public String title() {
		return "MarcaModelo";
	}

	public String iconName() {
		return "Repuesto";
	}


	@Named("Modelos por marca")
	public MarcaModeloViewmodel modelosPorMarca(
			final @Named("Marca") Marca marca){
		//titulo, alumno, ciclo, curso, division, dni, periodo, turno
		Memento newMemento = mementoService.create();
		
		newMemento.set("titulo", "Modelos por Marca");
		newMemento.set("marca", marca.title());
		newMemento.set("modelo", Modelo.class);
		
		
		final MarcaModeloViewmodel newView = container.newViewModelInstance(MarcaModeloViewmodel.class, newMemento.asString());
		return newView;
		
	}
	
	
	@javax.inject.Inject
    MementoService mementoService;
	
	@javax.inject.Inject
	DomainObjectContainer container;
	
	
}
