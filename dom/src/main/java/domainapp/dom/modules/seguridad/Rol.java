package domainapp.dom.modules.seguridad;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.RenderType;


@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

public class Rol implements Comparable<Rol> {

	// {{ RoleName (property)
	private String roleName;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(final String roleName) {
		this.roleName = roleName;
	}

	// }}

	// {{ PermissionsList (Collection)
	@Join
	@Element(dependent = "false")
	private SortedSet<Permiso> permissionsList = new TreeSet<Permiso>();

	@MemberOrder(sequence = "3")
    @CollectionLayout(
            render = RenderType.EAGERLY
    )
	public SortedSet<Permiso> getPermissionsList() {
		return permissionsList;
	}

	public void setPermissionsList(final SortedSet<Permiso> permissionsList) {
		this.permissionsList = permissionsList;
	}

	// }}
	
	//region > add permission (action)
    // //////////////////////////////////////
    
    @MemberOrder(sequence = "4")
    public Rol addPermission(
            final Permiso permission) {
              
        permissionsList.add(permission);
        
        return this;
    }

    //endregion

	public String title() {
		String text = roleName;
		return text;
	}

	@Override
	public int compareTo(Rol other) {
		int last = this.getRoleName().compareTo(other.getRoleName());
		return last;
	}
	
	//region > remove permission (action)
    // //////////////////////////////////////
    
    @MemberOrder(sequence = "5")
    
    public Rol removePermission(
            final Permiso permission) {
    	
    	getPermissionsList().remove(permission);    	
        return this;
    }

    //endregion

    
    public SortedSet<Permiso> choices0RemovePermission(){
    	return getPermissionsList();
    }
}
