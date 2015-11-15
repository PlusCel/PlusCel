package domainapp.dom.modules.atencion;

import javax.inject.Named;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

import domainapp.dom.modules.atencion.Marca;

@DomainService
public class CargaRapidaServices {
	
	public String getId() {
		return "CargaRapidaService";
	}

	public String title() {
		return "MarcaModelo";
	}

	public String iconName() {
		return "Repuesto";
	}

	@Named("Modelos por marca")
	public CargaRapidaViewmodel cargaRapida(
			final @Named("Marca") Marca marca){
		
		Memento newMemento = mementoService.create();
		
		newMemento.set("titulo", "Modelos por Marca");
		newMemento.set("marca12", marca.title());		
		
		final CargaRapidaViewmodel newView = container.newViewModelInstance(CargaRapidaViewmodel.class, newMemento.asString());
		return newView;
		
	}	
	
	@javax.inject.Inject
    MementoService mementoService;
	
	@javax.inject.Inject
	DomainObjectContainer container;
	
}
