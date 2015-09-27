package domainapp.dom.modules.reportes;

import org.apache.isis.applib.annotation.MemberOrder;

public class UsuariosReportes {

	// {{ Nombre de Usuario (property)
		private String userName;

		@MemberOrder(sequence = "1")
		public String getUserName() {
			return userName;
		}

		public void setUserName(final String userName) {
			this.userName = userName;
		}
		// }}

		// {{ Password (property)
		private String password;

		@MemberOrder(sequence = "1")
		public String getPassword() {
			return password;
		}

		public void setPassword(final String password) {
			this.password = password;
		}
		// }}

		// {{ Registros (property)
		private String registros;

		@MemberOrder(sequence = "1")
		public String getRegistros() {
			return registros;
		}

		public void setRegistros(final String registros) {
			this.registros = registros;
		}
		// }}

		// {{ Rol (property)
		private String role;

		@MemberOrder(sequence = "1")
		public String getRole() {
			return role;
		}

		public void setRole(final String role) {
			this.role = role;
		}
		// }}
	
}
