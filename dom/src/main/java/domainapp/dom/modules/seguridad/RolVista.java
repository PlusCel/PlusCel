package domainapp.dom.modules.seguridad;

import java.util.List;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Programmatic;

@MemberGroupLayout(columnSpans = { 5, 0, 0, 7 })
@DomainServiceLayout(menuOrder = "1" , named="Configuracion")

public class RolVista extends AbstractViewModel{

	
	// {{ ListaPremission (property)
	private List<Rol> ListaRole;

	@MemberOrder(sequence = "2")
	public List<Rol> getListaRoles() {
		return ListaRole;
	}

	public void setListaRoles(final List<Rol> ListaRole) {
		this.ListaRole = ListaRole;
	}
	// }}

	@MemberOrder(sequence = "1", name = "Menu Roles")

	public Rol crearunnuevorol(final String roleName,
            final Permiso permission) {

		return rolerepo.altaRol(roleName, permission);
	}

	@MemberOrder(sequence = "2", name = "Menu Roles")

	public RolVista eliminarunrol(Rol Role) {
						
		rolerepo.eliminarRol(Role);
		return Newviewmodel();
	}
	
	@Programmatic
	private RolVista Newviewmodel()
	{
		return cuentasrepo.Roles();
	}
	
	@MemberOrder(sequence = "3", name = "Menu Roles")

	public UsuarioVista Usuario() {
		return cuentasrepo.Usuarios();
	}
	
	@MemberOrder(sequence = "4", name = "Menu Roles")

	public PermisoVista permisos()
	{
		return cuentasrepo.Permisos();
	}
	
	
	//private String Menu="Menu";
	
	public void getMenu()
	{
	}
	
	private String title="Roles";

	// region > identification in the UI
	public String title() {
		return title;
	}
	
	String memento;
	
	@Override
	public String viewModelMemento() {
		return memento;
	}

	@Override
	public void viewModelInit(String mementoString) {
		this.memento = mementoString;
		setListaRoles(rolerepo.listarTodos());
		
	}

	@javax.inject.Inject 
	RolRepositorio rolerepo;
	@javax.inject.Inject 
	Cuentas cuentasrepo;
}
