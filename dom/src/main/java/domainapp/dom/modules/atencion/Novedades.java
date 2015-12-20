package domainapp.dom.modules.atencion;

import javax.inject.Named;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;


@Named("Orden de Servicio")
@DomainService
public class Novedades {

		public String getId() {
			return "Orden";
		}

		public String title() {
			return "OrdenServicio";
		}

		public String iconName() {
			return "SimpleObject";
		}

		// endregion

		@MemberOrder(name = "Orden",sequence = "10")
		
		public NovedadesViewmodel novedadesView(
				@Named("tecnico") Tecnico tecnico){
				//@Named("marca") Marca marca, 
				//@Named("equipo") Equipo equipo) {			

			Memento memento = mementoService.create();

			memento.set("titulo", "OrdenxTecnico");
			memento.set("apellido", tecnico.getApellido());
//			memento.set("tecnico", tecnico.getApellido() + " " + tecnico.getNombre());
//			memento.set("marca", marca.getAbreviatura());
//			memento.set("equipo", equipo.getImei());
		

			return container.newViewModelInstance(
					NovedadesViewmodel.class, memento.asString());
		}
		
		
		@javax.inject.Inject
		DomainObjectContainer container;
		@javax.inject.Inject
		MementoService mementoService;
		@javax.inject.Inject
		OrdenServicioRepositorio ordenServicio;

}