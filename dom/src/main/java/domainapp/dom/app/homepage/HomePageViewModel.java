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

package domainapp.dom.app.homepage;

import domainapp.dom.modules.atencion.OrdenServicio;
import domainapp.dom.modules.atencion.OrdenServicioRepositorio;

import domainapp.dom.modules.atencion.Cliente;
import domainapp.dom.modules.atencion.ClienteRepositorio;

import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.MemberOrder;

import java.util.List;

import org.apache.isis.applib.annotation.RenderType;
import org.apache.isis.applib.annotation.ViewModel;


@ViewModel
public class HomePageViewModel {

    //region > title
    public String title() {
    	if (getEquiposSinReparar().size() > 1 ){
    		 return getEquiposSinReparar().size() + " Ordenes de servicio sin reparación ";
    	}else if (getEquiposSinReparar().size() == 1) {
    		return getEquiposSinReparar().size() + " Orden de servicio sin reparación";
    	}else{
    		return "No hay ordenes de servicio sin reparación ";
    	}
       
    }
    //endregion
    @MemberOrder(sequence = "1")
    @CollectionLayout(
            render = RenderType.EAGERLY
    )
	public List<OrdenServicio> getEquiposSinReparar() {
		return OrdenServicioRepositorio.sinArreglo();
	}
    
    @MemberOrder(sequence = "2")
    @CollectionLayout(
            render = RenderType.EAGERLY
    )
    public List<OrdenServicio> getEquiposReparados() {
		return OrdenServicioRepositorio.reparados();
	}
    	
    @MemberOrder(sequence = "3")
    @CollectionLayout(
            render = RenderType.EAGERLY
    )
	public List<OrdenServicio> getEquiposSinRevisar() {
		return OrdenServicioRepositorio.sinRevisar();
	}
    
    @MemberOrder(sequence = "4")
    @CollectionLayout(
            render = RenderType.EAGERLY
    )
	public List<Cliente> getUltimosClientes() {
		return ClienteRepositorio.ultimosClientes();
	}

    
    @javax.inject.Inject
    OrdenServicioRepositorio OrdenServicioRepositorio;
   //ClienteRepositorio ClienteRepositorio;
    

    //endregion
}
