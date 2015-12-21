package domainapp.dom.modules.atencion;

import javax.inject.Named;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;


@Named("Orden de Servicio")
@DomainService
public class OrdenServicioCliente {

		public String getId() {
			return "Ordenes de Servicio Clientes";
		}

		public String title() {
			return "OrdenServicioPorClientes";
		}

		public String iconName() {
			return "SimpleObject";
		}

		// endregion

		@MemberOrder(name = "Orden",sequence = "10")
		
		public OrdenServicioClienteViewmodel ordenServicioPorCliente(
				@ParameterLayout(named="DNI")
				@Named("clientes") String dni){		

			Memento memento = mementoService.create();

			memento.set("titulo", "OrdenServicioPorCliente");
			memento.set("dni", dni);
	
			return container.newViewModelInstance(
					OrdenServicioClienteViewmodel.class, memento.asString());
		}
				
		@javax.inject.Inject
		DomainObjectContainer container;
		@javax.inject.Inject
		MementoService mementoService;
		@javax.inject.Inject
		OrdenServicioRepositorio ordenServicio;

}