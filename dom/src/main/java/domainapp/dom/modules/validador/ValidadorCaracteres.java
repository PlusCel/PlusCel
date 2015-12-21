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


package domainapp.dom.modules.validador;

//import java.util.Calendar;

public class ValidadorCaracteres {

	public static final class ValidacionAlfanumerico{ 	
		private ValidacionAlfanumerico() { }
		public static final String PERMITIDOS = "[a-z,A-Z,0-9,ñ,Ñ, ]+"; 
	}
	
	public static final class ValidacionNumerica{ 	
		private ValidacionNumerica() { }
		public static final String PERMITIDOS = "[0-9]+"; 
	}
	
	public static final class ValidacionTel {
		private ValidacionTel() {}
		public static final String PERMITIDOS = "[+]?[0-9 -]*";
	}
	
	public static final class ValidacionEmail {
		private ValidacionEmail() {}
		public static final String PERMITIDOS = "(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+";
	}
	
	public static final class ValidacionLetras {
		private ValidacionLetras() {}
		public static final String PERMITIDOS ="[A-Z,a-z,ñ,Ñ,]+";
	}
	
}