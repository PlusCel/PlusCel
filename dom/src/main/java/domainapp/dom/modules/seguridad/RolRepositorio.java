package domainapp.dom.modules.seguridad;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.RenderType;

@DomainService(repositoryFor = Rol.class)
@DomainServiceLayout(
		menuOrder = "21" , 
		menuBar = DomainServiceLayout.MenuBar.SECONDARY,
		named="Configuracion")

public class RolRepositorio {
	
	 //region > identification in the UI
    // //////////////////////////////////////

    public String getId() {
        return "Roles";
    }

    @ActionLayout(cssClassFa="fa fa-users")

    //endregion

    //region > create (action)
    // //////////////////////////////////////

    @MemberOrder(sequence = "1", name = "Configuracion")
    public Rol altaRol(
            final @ParameterLayout(named="Nombre Rol")  String roleName,
            final @ParameterLayout(named="Permiso") Permiso permission
            ) {
        final Rol obj = container.newTransientInstance(Rol.class);
        
        final SortedSet<Permiso> permissionsList = new TreeSet<Permiso>();
        permissionsList.add(permission);
        
        obj.setRoleName(roleName);
        obj.setPermissionsList(permissionsList);
        
        container.persistIfNotAlready(obj);
        return obj;
    }

    //endregion
    
    //region > Eliminar Role (action)
    // //////////////////////////////////////
    
    @MemberOrder(sequence = "2", name = "Configuracion")
    public String eliminarRol(Rol role) {
    	String roleName = role.getRoleName();
    	container.remove(role);
        return "El rol " + roleName + " fue eliminado con exito";
    }

    //endregion
    
    //region > listAll (action)
    // //////////////////////////////////////
    @CollectionLayout(
            render = RenderType.EAGERLY
    )
    @MemberOrder(sequence = "3", name = "Configuracion")
    public List<Rol> listarTodos() {
        return container.allInstances(Rol.class);
    }

    //endregion
    
    
    //region > injected services
    // //////////////////////////////////////

    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion

}
