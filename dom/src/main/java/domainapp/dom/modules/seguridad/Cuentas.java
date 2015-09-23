package domainapp.dom.modules.seguridad;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

public class Cuentas {

    @MemberOrder(sequence = "1")
	public PermisoVista Permisos()
	{
		Memento memento = mementoService.create();
		memento.set("Title","Titulo");
		return container.newViewModelInstance(
				PermisoVista.class, memento.asString());
	}
	
    @MemberOrder(sequence = "2")

	public RolVista Roles()
	{
		Memento memento = mementoService.create();
		memento.set("Title","Titulo");
		return container.newViewModelInstance(
				RolVista.class, memento.asString());
	}
    
    @MemberOrder(sequence = "3")
	public UsuarioVista Usuarios()
	{
		Memento memento = mementoService.create();
		memento.set("Title","Titulo");
		return container.newViewModelInstance(
				UsuarioVista.class, memento.asString());
	}
	
    @javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	MementoService mementoService;
}

