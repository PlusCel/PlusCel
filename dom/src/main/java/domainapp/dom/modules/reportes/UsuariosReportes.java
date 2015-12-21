/*
 * This is software for administration and management of mobile equipment repair
 *
 * Copyright ( C ) 2015 , Pluscel
 *
 * This program is free software ; you can redistribute it and / or
 * Modify it under the terms of the GNU General Public License
 * As published by the Free Software Foundation ; either version 2
 * Of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * But WITHOUT ANY WARRANTY; without even the implied warranty
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. Boil
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * Along with this program ; if not, write to the Free Software
 *
 *
 * Foundation , Inc. , 51 Franklin Street, Fifth Floor , Boston, MA 02110-1301 , USA .
 */
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
