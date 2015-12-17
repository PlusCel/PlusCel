package domainapp.dom.modules.atencion;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

@DomainService

public class CargaRapidaServices {
	
	public String getId() {
		return "Vistas Rapidas";
	}

	public CargaRapidaViewmodel cargaRapida(
			){
		
		Memento newMemento = mementoService.create();
			
		final CargaRapidaViewmodel newView = container.newViewModelInstance(CargaRapidaViewmodel.class, newMemento.asString());
		return newView;
		
	}	
	
	@javax.inject.Inject
    MementoService mementoService;
	
	@javax.inject.Inject
	DomainObjectContainer container;
	
}
