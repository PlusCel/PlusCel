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

import domainapp.dom.modules.servicios.E_estado;
import domainapp.dom.modules.servicios.EnvioCorreo;

@DomainService(repositoryFor = Equipo.class)
@DomainServiceLayout(menuOrder = "10" , named="Equipo")

public class EquipoRepositorio {
	
	//region > listarTodos (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "1")
    public List<Equipo> listarTodos() {
        return container.allInstances(Equipo.class);
    }
    //endregion

   // region > buscarPorMarca (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "2")
    public List<Equipo> buscarPorMarca(
            @ParameterLayout(named="Marca")
            final String marca
    ) {
        return container.allMatches(
                new QueryDefault<>(
                        Equipo.class,
                        "buscarPorMarca",
                        "marca", marca));
    }
  // endregion   

    //region > create (action)
    @MemberOrder(sequence = "3")
    public Equipo AltaEquipo(
            final @ParameterLayout(named="Marca") Marca marca,    		           
            final @ParameterLayout(named="Modelo") Modelo modelo, 
            final @ParameterLayout(named="Estado") E_estado estado,             
            final @ParameterLayout(named="IMEI") String imei   
            
    		) {
        final Equipo obj = container.newTransientInstance(Equipo.class);
        obj.setMarca(marca);
        obj.setModelo(modelo);
        obj.setEstado(estado);     
        obj.setImei(imei);
   
        container.persistIfNotAlready(obj);
        return obj;
    }

 // region > buscarPorImei (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "4")
    public List<Equipo> buscarPorImei(
            @ParameterLayout(named="IMEI")
            final String imei
    ) {
        return container.allMatches(
                new QueryDefault<>(
                        Equipo.class,
                        "buscarPorImei",
                        "imei", imei));
    }
    //endregion
    
    
    // region > buscarPorEstado (action)
  @Action(
          semantics = SemanticsOf.SAFE
 )
  @ActionLayout(
          bookmarking = BookmarkPolicy.AS_ROOT
  )
  @MemberOrder(sequence = "5")
  public List<Equipo> buscarPorEstado(
          @ParameterLayout(named="Estado")
        final E_estado estado
  ) {
      return container.allMatches(
              new QueryDefault<>(
                      Equipo.class,
                      "buscarPorEstado",
                      "estado", estado));
  }
 //endregion
  
      

    //region > injected services

    @javax.inject.Inject 
    DomainObjectContainer container;    


	public static List<Equipo> listAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
    

    //endregion

}