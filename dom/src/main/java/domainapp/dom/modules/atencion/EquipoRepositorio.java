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

import domainapp.dom.modules.servicios.Estado;
import domainapp.dom.modules.servicios.Estado.E_estados;




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
    //endregion

    //region > create (action)
    @MemberOrder(sequence = "3")
    public Equipo Alta(
            final @ParameterLayout(named="Marca") String marca,    		           
            final @ParameterLayout(named="Modelo") String modelo, 
            final @ParameterLayout(named="Estado") E_estados estado,
            final @ParameterLayout(named="Accesorio") String accesorio,   
            final @ParameterLayout(named="IMEI") String imei   
            
    		) {
        final Equipo obj = container.newTransientInstance(Equipo.class);
        final Estado esta = new Estado();
        obj.setMarca(marca);
        obj.setModelo(modelo);
        esta.setEstado(estado);
        obj.setAccesorio(accesorio);
        obj.setImei(imei);
   
        container.persistIfNotAlready(obj);
        return obj;
    }

    //endregion

    //region > injected services

    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion

}
