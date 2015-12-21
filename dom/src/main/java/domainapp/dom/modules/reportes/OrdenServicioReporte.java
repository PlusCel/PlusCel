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

public class OrdenServicioReporte {
	
	// {{ Titulo (property)
		private String titulo;


		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(final String titulo) {
			this.titulo = titulo;
		}
		// }}
		
		private String falla;
		
		 public String getFalla() {
		        return falla;
		 }
		 public void setFalla(final String falla) {
		        this.falla = falla;
		 }
		 
		 private String cliente;
			
		 public String getCliente() {
		        return cliente;
		 }
		 public void setCliente(final String cliente) {
		        this.cliente = cliente;
		 }
		 
		 private String equipo;
			
		 public String getEquipo() {
		        return equipo;
		 }
		 public void setEquipo(final String equipo) {
		        this.equipo = equipo;
		 }
		 
		 private String tecnico;
			
		 public String getTecnico() {
		        return tecnico;
		 }
		 public void setTecnico(final String tecnico) {
		        this.tecnico = tecnico;
		 }
		 
		 private long numero;
			
		 public long getNumero() {
		        return numero;
		 }
		 public void setNumero(final long numero) {
		        this.numero = numero;
		 }
		 
		 private String dni;
			
		 public String getDNI() {
		        return dni;
		 }
		 public void setDNI(final String dni) {
		        this.dni = dni;
		 }		 
		 


		
}
