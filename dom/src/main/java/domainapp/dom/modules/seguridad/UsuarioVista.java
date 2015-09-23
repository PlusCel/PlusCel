package domainapp.dom.modules.seguridad;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import org.joda.time.LocalDate;

@MemberGroupLayout(columnSpans = { 5, 0, 0, 7 })
public class UsuarioVista extends AbstractViewModel{

	
	// {{ ListaPremission (property)
	private List<Usuario> ListaShiroUser;

	@MemberOrder(sequence = "2")
	public List<Usuario> getListaPremission() {
		return ListaShiroUser;
	}

	public void setListaPremission(final List<Usuario> ListaShiroUser) {
		this.ListaShiroUser = ListaShiroUser;
	}
	// }}

	@MemberOrder(sequence = "1", name = "Menu")

	public Usuario crearunnuevousuario(final String userName,
			final  String password,
			final Rol role) {

		return shirorepo.altaUsuario(userName, password, role);
	}


	@MemberOrder(sequence = "2", name = "Menu")

	public UsuarioVista eliminarunusuario(Usuario ShiroUser) {
						
		shirorepo.eliminarUsuario(ShiroUser);
		return NuevoViewModel();
	}
	
	@MemberOrder(sequence = "3", name = "Menu")
	public RolVista roles()
	{
		return cuentasrepo.Roles();
	}
	
	@MemberOrder(sequence = "4", name = "Menu")
	public PermisoVista permisos()
	{
		return cuentasrepo.Permisos();
	}
	
	@Programmatic
	private UsuarioVista NuevoViewModel() {
		return cuentasrepo.Usuarios();
	}
	
	//private String Menu="Menu";
	
	public void getMenu()
	{
	}
	
	private String title="Usuarios";

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
		List<Usuario> ListaShiroUser =new ArrayList<Usuario>();
		/*
		for(ShiroUser User:shirorepo.listAll())
		{
			ShiroUser newUser=new ShiroUser();
			
			newUser.setUserName(User.getUserName());
			newUser.setRolesList(User.getRolesList());
			newUser.setPassword("************");
			
			ListaShiroUser.add(newUser);
		}*/
		
		//setListaPremission(ListaShiroUser);
		setListaPremission(shirorepo.listAll());
	}


	@javax.inject.Inject 
	UsuarioRepositorio shirorepo;
	@javax.inject.Inject 
	Cuentas cuentasrepo;
}
