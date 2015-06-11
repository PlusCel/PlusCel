package domainapp.dom.modules.servicios;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.MemberOrder;



@PersistenceCapable
public class Estado {
	
	
	// {{ Nombre (property)
		private E_estados descripcion;

		@Column(allowsNull = "true")
		@MemberOrder(sequence = "1")
		@Persistent
		public E_estados getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(final E_estados descripcion) {
			this.descripcion = descripcion;
		}
		// }}


		public enum E_estados{	
			REPARADO,ENTREGADO,ENESPERA;
		}
		
		public String title(){
			return descripcion.toString();
		}

		@Override
		public String toString() {
			return descripcion.toString();
		}
		

}
