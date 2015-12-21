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

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.joda.time.LocalDate;

import domainapp.dom.modules.servicios.E_tipoRepuesto;

@DomainService(repositoryFor = Repuesto.class)
@DomainServiceLayout(menuOrder = "4" , named="Gestion")
public class RepuestoRepositorio {

    //region > create (action)
    @MemberOrder(sequence = "1")
    public Repuesto ingresarRepuestos(
    		final @ParameterLayout(named="Tipo Repuesto") E_tipoRepuesto tiporepuesto,
            final @ParameterLayout(named="Modelo") String modelo,
            final @ParameterLayout(named="Descripcion" , multiLine=10)  String descripcion,
            final @ParameterLayout(named="Fecha probable arribo") LocalDate fechaArribo,
            final @ParameterLayout(named="Precio Costo") Double costo,
            final @ParameterLayout(named="Cantidad") @Parameter(regexPattern = domainapp.dom.modules.validador.ValidadorCaracteres.ValidacionNumerica.PERMITIDOS, maxLength = 5) int cantidad
            
    		) {
        final Repuesto obj = container.newTransientInstance(Repuesto.class);
        obj.setTipoRepuesto(tiporepuesto);
        obj.setModelo(modelo);
        obj.setDescripcion(descripcion);
        obj.setFechaArribo(fechaArribo);
        obj.setCosto(costo);
        obj.setCantidad(cantidad);
        
        container.persistIfNotAlready(obj);
        return obj;
    }

    //endregion

    //region > listAll (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "2")
    public List<Repuesto> listarTodosLosRepuestos() {
        return container.allInstances(Repuesto.class);
    }
    //endregion
    
    //region > injected services

    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion
}
