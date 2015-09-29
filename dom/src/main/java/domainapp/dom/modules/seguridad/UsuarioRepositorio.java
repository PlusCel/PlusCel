package domainapp.dom.modules.seguridad;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.PostConstruct;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;


@DomainService(repositoryFor = Usuario.class)
@DomainServiceLayout(
		menuOrder = "21" , 
		menuBar = DomainServiceLayout.MenuBar.SECONDARY,
		named="Configuracion")

public class UsuarioRepositorio {

	// region > identification in the UI
	// //////////////////////////////////////

	public String getId() {
		return "Usuarios";
	}

	public String iconName() {
		return "Usuarios";
	}
	
	@ActionLayout(cssClassFa="fa fa-user")

	// endregion


	// region > create (action)
	// //////////////////////////////////////

	@MemberOrder(sequence = "1", name = "Configuracion")
	public Usuario altaUsuario(final @ParameterLayout(named="Nombre de Usuario") String userName,
			final @ParameterLayout(named="Password")   String password,
			final @ParameterLayout(named="Rol")   Rol role) {
		final Usuario obj = container.newTransientInstance(Usuario.class);

		final SortedSet<Rol> rolesList = new TreeSet<Rol>();
		rolesList.add(role);
		obj.setUserName(userName);
		obj.setPassword(password);
		obj.setRolesList(rolesList);
		container.persistIfNotAlready(obj);
		return obj;
	}

	// endregion

	// region > remove User (action)
	// //////////////////////////////////////
	@MemberOrder(sequence = "2", name = "Configuracion")

	public String eliminarUsuario(Usuario shiroUser) {
		String userName = shiroUser.getUserName();
		container.remove(shiroUser);
		return "The user " + userName + " has been removed";
	}

	// endregion

	// region > initialization
	// //////////////////////////////////////

	@Programmatic
	@PostConstruct
	public void init() {
		List<Usuario> users = listAll();
		if (users.isEmpty()) {
			
			Permiso permission = new Permiso();
			Rol role = new Rol();
			SortedSet<Permiso> permissions = new TreeSet<Permiso>();

			permission.setPermisoDescripcion("Todos");
			permission.setPermisoNombre("*");
			permissions.add(permission);
			role.setRoleName("nacho.cartes");
			role.setPermissionsList(permissions);

			altaUsuario("nacho.cartes", "pluscel", role);
		
		}
	}

	// endregion
	
	// region > listAll (action)
	// //////////////////////////////////////


	@MemberOrder(sequence = "1", name = "Configuracion")
	public List<Usuario> listAll() {
		return container.allInstances(Usuario.class);
	}

	// endregion

	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	DomainObjectContainer container;
	// endregion

}
