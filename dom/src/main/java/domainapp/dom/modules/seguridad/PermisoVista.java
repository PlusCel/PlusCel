package domainapp.dom.modules.seguridad;

import java.util.List;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;

@MemberGroupLayout(columnSpans = { 5, 0, 0, 7 })
@DomainServiceLayout(
		menuOrder = "21" , 
		menuBar = DomainServiceLayout.MenuBar.SECONDARY,
		named="Configuracion")

public class PermisoVista extends AbstractViewModel{

	// {{ Listado de permisos (property)
	
	private List<Permiso> ListarPermisos;

	@MemberOrder(sequence = "2")
	public List<Permiso> getListaPremission() {
		return ListarPermisos;
	}

	public void setListaPremission(final List<Permiso> ListarPermisos) {
		this.ListarPermisos = ListarPermisos;
	}
	// }}

	@MemberOrder(sequence = "1", name = "Menu")
	
	public Permiso crearunnuevopermiso(
			final @ParameterLayout(named="Nombre") String permisoNombre,
            final @ParameterLayout(named="Descripcion")  String permisoDescripcion)  {

		return permrepo.altaPermisos(permisoNombre, permisoDescripcion);
	}

	// {{ Eliminar permisos (property)
	
	@MemberOrder(sequence = "2", name = "Menu")
	public PermisoVista eliminarunpermiso(@ParameterLayout(named="Nombre") Permiso permission) {
						
		permrepo.eliminarPermisos(permission);
		return Newviewmodel();
	}
	
	@MemberOrder(sequence = "4", name = "Menu")
	public RolVista roles()
	{
		return cuentasrepo.Roles();
	}
	
	@Programmatic
	private PermisoVista Newviewmodel()
	{
		return cuentasrepo.Permisos();
	}

	@MemberOrder(sequence = "3", name = "Menu")
	public UsuarioVista Usuario() {
		return cuentasrepo.Usuarios();
	}
	
	//private String Menu="Menu";
	
	public void getMenu()
	{
	}
	
	private String title="Permisos";

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
		setListaPremission(permrepo.listarPermisos());
		
	}

    @javax.inject.Inject 
	PermisoRepositorio permrepo;
	@javax.inject.Inject 
	Cuentas cuentasrepo;
}
