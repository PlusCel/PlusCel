package domainapp.dom.modules.seguridad;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Join;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Programmatic;

import domainapp.dom.modules.reportes.UsuariosReportes;
import domainapp.dom.modules.servicios.E_formato;
import domainapp.dom.modules.servicios.GenerarReporte;
import net.sf.jasperreports.engine.JRException;

@MemberGroupLayout(columnSpans = { 5, 0, 0, 7 })
@DomainServiceLayout(
		menuOrder = "21" , 
		menuBar = DomainServiceLayout.MenuBar.SECONDARY,
		named="Configuracion")

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

	@MemberOrder(sequence = "1", name = "Configuracion")

	public Usuario crearunnuevousuario(final String userName,
			final  String password,
			final Rol role) {

		return shirorepo.altaUsuario(userName, password, role);
	}


	@MemberOrder(sequence = "2", name = "Configuracion")

	public UsuarioVista eliminarunusuario(Usuario ShiroUser) {
						
		shirorepo.eliminarUsuario(ShiroUser);
		return NuevoViewModel();
	}
	
	@MemberOrder(sequence = "3", name = "Configuracion")
	public RolVista roles()
	{
		return cuentasrepo.Roles();
	}
	
	@MemberOrder(sequence = "4", name = "Configuracion")
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
	
	
	@Join
	@Element(dependent = "true")
	private List<UsuarioVista> usuarioVista = new ArrayList<UsuarioVista>();


	@MemberOrder(sequence = "1")
	@Named("Listado de ususario")
	public List<UsuarioVista> getUsuarioVista() {
		return usuarioVista;
	}

	public void setUsuarioVista(
			final List<UsuarioVista> usuarioVista) {
		this.usuarioVista = usuarioVista;
	}

	// }} (end region)
	// //////////////////////////////////////
	
	@Named("Imprimir Reporte")
	//@DescribedAs(value = "El archivo se almacenará en el directorio 'reportes' del proyecto")
	public String elegirFormato(final @Named("Formato") E_formato formato) throws JRException{
		return imprimirReporte(formato);		
	}
	
	public E_formato default0ElegirFormato(final @Named("Formato") E_formato formato){
		return E_formato.PDF;		
	}
	
	public String imprimirReporte(E_formato format) throws JRException{
		List<Object> objectsReport = new ArrayList<Object>();
		
		for(UsuarioVista u: getUsuarioVista()){
			UsuariosReportes usuarios = new UsuariosReportes();
			
			usuarios.setUserName("Hola Mundo");
			
			objectsReport.add(usuarios);
		}
		
		String nombreArchivo = "reportes/asistencia/"; 
		GenerarReporte.generarReporte("asistenciaCurso.jrxml", objectsReport, format, nombreArchivo);
		return "Reporte Generado.";
	}
	


	@javax.inject.Inject 
	UsuarioRepositorio shirorepo;
	@javax.inject.Inject 
	Cuentas cuentasrepo;
}
