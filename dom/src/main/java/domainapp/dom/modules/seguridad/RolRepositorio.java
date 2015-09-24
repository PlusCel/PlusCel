package domainapp.dom.modules.seguridad;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;

import domainapp.dom.modules.atencion.Marca;
import domainapp.dom.modules.atencion.Modelo;

@DomainService(repositoryFor = Rol.class)
@DomainServiceLayout(menuOrder = "1" , named="Configuracion")

public class RolRepositorio {
	
	 //region > identification in the UI
    // //////////////////////////////////////

    public String getId() {
        return "Roles";
    }

    public String iconName() {
        return "Roles";
    }

    //endregion

    //region > create (action)
    // //////////////////////////////////////

    @MemberOrder(sequence = "1", name = "Menu Roles")
    public Rol altaRol(
            final @ParameterLayout(named="Nombre Rol")  String roleName,
            final @ParameterLayout(named="Permiso") Permiso permission
            ) {
        final Rol obj = container.newTransientInstance(Rol.class);
        final SortedSet<Permiso> permissionsList = new TreeSet<Permiso>();
        permissionsList.add(permission);
        obj.setRoleName(roleName);
        obj.setPermissionsList(permissionsList);
        //obj.setPermissions(permission);
        container.persistIfNotAlready(obj);
        return obj;
    }

    //endregion

    @MemberOrder(sequence = "1")
    public Modelo altaModelo(
            final @ParameterLayout(named="Abreviatura") String abreviatura,
            final @ParameterLayout(named="Descripcion", multiLine=10) String descripcion,
            final @ParameterLayout(named="Marca") Marca marca
    		) {
        final Modelo obj = container.newTransientInstance(Modelo.class);
        obj.setAbreviatura(abreviatura);
        obj.setMarca(marca);
        obj.setDescripcion(descripcion);
   
        container.persistIfNotAlready(obj);
        return obj;
    }
    
    
    
    
    
    
    
    //region > Eliminar Role (action)
    // //////////////////////////////////////
    
    @MemberOrder(sequence = "2", name = "Menu Roles")
    public String eliminarRol(Rol role) {
    	String roleName = role.getRoleName();
    	container.remove(role);
        return "El rol " + roleName + " fue eliminado con exito";
    }

    //endregion
    
    //region > listAll (action)
    // //////////////////////////////////////
    @MemberOrder(sequence = "3", name = "Menu Roles")
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
