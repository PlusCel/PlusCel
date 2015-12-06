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
