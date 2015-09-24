package domainapp.dom.modules.servicios.validador;

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