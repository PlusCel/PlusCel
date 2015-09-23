package domainapp.dom.modules.seguridad;

import java.util.SortedSet;
import java.util.TreeSet;


import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.VersionStrategy;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Title;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

public class Usuario {

	// {{ UserName (property)
	private String userName;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	@Title
	public String getUserName() {
		return userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	// }}

	// {{ Password (property)
	private String password;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	// }}

	// {{ RolesList (Collection)
	@Join
	@Element(dependent = "false")
	private SortedSet<Rol> rolesList = new TreeSet<Rol>();

	@MemberOrder(sequence = "3")
	
	public SortedSet<Rol> getRolesList() {
		return rolesList;
	}

	public void setRolesList(final SortedSet<Rol> rolesList) {
		this.rolesList = rolesList;
	}

	// }}

	// region > add role (action)
	// //////////////////////////////////////

	@MemberOrder(sequence = "3")
	public Usuario addRole(final Rol role) {

		rolesList.add(role);

		return this;
	}

	// endregion

	// region > remove permission (action)
	// //////////////////////////////////////

	@MemberOrder(sequence = "5")

	public Usuario removeRole(final Rol role) {

		getRolesList().remove(role);
		return this;
	}

	public SortedSet<Rol> choices0RemoveRole() {
		return getRolesList();
	}

	// endregion

		
	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	DomainObjectContainer container;

	// endregion

}
