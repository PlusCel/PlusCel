package domainapp.dom.modules.seguridad;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;


@DomainService(repositoryFor = Permiso.class)
@DomainServiceLayout(menuOrder = "1" , named="Configuracion")

public class PermisoRepositorio {
	
	 //region > identification in the UI
    // //////////////////////////////////////

    public String getId() {
        return "Permisos";
    }

    public String iconName() {
        return "Permisos";
    }

    //endregion

    //region > Alta PErmiso (action)
    // //////////////////////////////////////
    
    @MemberOrder(sequence = "1", name = "Menu Permisos")
   
    public Permiso altaPermisos(
    		final @ParameterLayout(named="Nombre") String permisoNombre,
            final @ParameterLayout(named="Descripcion")  String permisoDescripcion) {
        final Permiso obj = container.newTransientInstance(Permiso.class);
        
        obj.setPermisoNombre(permisoNombre);
        obj.setPermisoDescripcion(permisoDescripcion);
        
        container.persistIfNotAlready(obj);
        return obj;
    }
            
    //endregion

    //region > Eliminar Permiso (action)
    // //////////////////////////////////////
    
    @MemberOrder(sequence = "2", name = "Menu Permisos")

    public String eliminarPermisos(Permiso permission) {
    		String permisoDescripcion = permission.getPermisoDescripcion();
			container.remove(permission);
			return "El Permiso" + permisoDescripcion + " se elimino con exito";
	}

    //endregion
    
    //region > listAll (action)
    // //////////////////////////////////////
    @MemberOrder(sequence = "3", name = "Menu Permisos")
    
    public List<Permiso> listarPermisos() {
        return container.allInstances(Permiso.class);
    }

    //endregion
    
    
    //region > injected services
    // //////////////////////////////////////

    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion

}
