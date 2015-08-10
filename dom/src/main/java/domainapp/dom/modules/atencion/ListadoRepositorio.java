
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
import java.util.Date;



@DomainService(repositoryFor = Listado.class)
@DomainServiceLayout(menuOrder = "15" , named="Listados")
public class ListadoRepositorio {
	

    
    
  
  


    @MemberOrder(sequence = "1")
    public List<Presupuesto> listadoReparaciones(Equipo equipo,
    	Date fechaDesde,Date fechaHasta, Cliente cliente)
  {
   
        return container.allMatches(
                new QueryDefault<>(
                		Presupuesto.class,
                        "listadoReparaciones",
                        "equipo", equipo,
                         "fechaDesde" ,fechaDesde,"fechaHasta",fechaHasta,"cliente", cliente));
    }
    




//endregion


    //region > injected services
    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion
 
}
