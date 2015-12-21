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
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;

@DomainService(repositoryFor = Bug.class)
@DomainServiceLayout(menuOrder = "21", 
                      menuBar = DomainServiceLayout.MenuBar.SECONDARY,						
                      named="Bug")

public class BugRepositorio {
	@ActionLayout(cssClassFa="fa fa-bug",bookmarking = BookmarkPolicy.AS_ROOT)
//Inicio Metodo Alta
	@MemberOrder(sequence = "1")
	
	@Action(
            semantics = SemanticsOf.SAFE
    )

    public Bug reportarBug(
    		
            final @ParameterLayout(named="Formulario") String formulario,  
            final @ParameterLayout(named="Fecha") LocalDate fecha,
            final @ParameterLayout(named="Descripcion Proceso", multiLine=10) String proceso,            
            final @ParameterLayout(named="Descripcion Error", multiLine=15) String error   
            
    		) {
        final Bug obj = container.newTransientInstance(Bug.class);
        obj.setFormulario(formulario);
		obj.setFecha(fecha);
        obj.setProceso(proceso);    
        obj.setError(error);
   
        container.persistIfNotAlready(obj);
        return obj;
    }
//Fin Metodo Alta

//Inicio Buscar todos
	@MemberOrder(sequence = "2")
    public List<Bug> buscarTodos() {
        return container.allInstances(Bug.class);
    }
//Fin Buscar Todos	

//Inicio Buscar todos
	@MemberOrder(sequence = "2")
	public List<Bug> buscarUltimos() {
	    return container.allMatches(new QueryDefault<Bug>(Bug.class,
	    		"buscarUltimos"));
	}
//Fin Buscar Todos		

	
	//region > injected services

    @javax.inject.Inject 
    DomainObjectContainer container;        

    //endregion


}
