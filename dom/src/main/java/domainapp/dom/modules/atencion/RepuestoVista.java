package domainapp.dom.modules.atencion;

import java.util.List;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.joda.time.LocalDate;

import domainapp.dom.modules.seguridad.Cuentas;

import domainapp.dom.modules.servicios.E_tipoRepuesto;

public class RepuestoVista extends AbstractViewModel{
	
	// {{ Listado de repuestos (property)
		private List<Repuesto> ListarRepuesto;

		@MemberOrder(sequence = "2")
		public List<Repuesto> getListaRepuesto() {
			return ListarRepuesto;
		}

		public void setListaRepuesto(final List<Repuesto> ListarRepuesto) {
			this.ListarRepuesto = ListarRepuesto;
		}
		// }}

		@MemberOrder(sequence = "1", name = "Menu")
		
		public Repuesto altaRepuestos(
				final @ParameterLayout(named="Tipo Repuesto") E_tipoRepuesto tiporepuesto,
	            final @ParameterLayout(named="Modelo") String modelo,
	            final @ParameterLayout(named="Descripcion" , multiLine=10)  String descripcion,
	            final @ParameterLayout(named="Fecha probable arribo") LocalDate fechaArribo,
	            final @ParameterLayout(named="Precio Costo") Double costo,
	            final @ParameterLayout(named="Cantidad") @Parameter(regexPattern = domainapp.dom.modules.validador.ValidadorCaracteres.ValidacionNumerica.PERMITIDOS, maxLength = 5) int cantidad
	              ){
			return reprepo.altaRepuestos(tiporepuesto, modelo, descripcion, fechaArribo, costo, cantidad);
		}
/*
		// {{ Eliminar permisos (property)
		
		@MemberOrder(sequence = "2", name = "Menu")
		public PermisoVista eliminarunpermiso(@ParameterLayout(named="Nombre") Permiso permission) {
							
			reprepo.eliminarRepuesto(permission);
			return Newviewmodel();
		}
		*/
		@MemberOrder(sequence = "4", name = "Menu")
		public RepuestoVista repuestos()
		{
			return cuentasrepo.Repuestos();
		}
		
		//private String Menu="Menu";
		
		public void getMenu()
		{
		}
		
		private String title="Repuestos";

		// region > identification in the UI
		public String title() {
			return title;
		}
		
		String memento;
		
		@Override
		public String viewModelMemento() {
			return memento;
		}

		@Override
		public void viewModelInit(String mementoString) {
			this.memento = mementoString;
			setListaRepuesto(reprepo.listarTodos());
			
		}

	    @javax.inject.Inject 
		RepuestoRepositorio reprepo;
		@javax.inject.Inject 
		Cuentas cuentasrepo;

}
