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
package domainapp.dom.modules.atencion;

import javax.inject.Named;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;


@Named("Orden de Servicio")
@DomainService
public class OrdenServicioCliente {

		public String getId() {
			return "Ordenes de Servicio Clientes";
		}

		public String title() {
			return "OrdenServicioPorClientes";
		}

		public String iconName() {
			return "SimpleObject";
		}

		// endregion

		@MemberOrder(name = "Orden",sequence = "10")
		
		public OrdenServicioClienteViewmodel ordenServicioPorCliente(
				@ParameterLayout(named="DNI")
				@Named("clientes") String dni){		

			Memento memento = mementoService.create();

			memento.set("titulo", "OrdenServicioPorCliente");
			memento.set("dni", dni);
	
			return container.newViewModelInstance(
					OrdenServicioClienteViewmodel.class, memento.asString());
		}
				
		@javax.inject.Inject
		DomainObjectContainer container;
		@javax.inject.Inject
		MementoService mementoService;
		@javax.inject.Inject
		OrdenServicioRepositorio ordenServicio;

}