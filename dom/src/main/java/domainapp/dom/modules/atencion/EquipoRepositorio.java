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


@DomainService(repositoryFor = Equipo.class)
@DomainServiceLayout(menuOrder = "10" , named="Equipo")

public class EquipoRepositorio {
	
	//region > listAll (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "1")
    public List<Equipo> listAll() {
        return container.allInstances(Equipo.class);
    }
    //endregion

   // region > findByMarca (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "2")
    public List<Equipo> findByMarca(
            @ParameterLayout(named="Marca")
            final String marca
    ) {
        return container.allMatches(
                new QueryDefault<>(
                        Equipo.class,
                        "findByMarca",
                        "marca", marca));
    }
    //endregion

    //region > create (action)
    @MemberOrder(sequence = "3")
    public Equipo create(
            final @ParameterLayout(named="Marca") String marca,
            final @ParameterLayout(named="Modelo") String modelo  , 
            final @ParameterLayout(named="Estado") String estado ,  
            final @ParameterLayout(named="Accesorio") String accesorio,   
            final @ParameterLayout(named="IMEI") String imei   
            
    		) {
        final Equipo obj = container.newTransientInstance(Equipo.class);
        obj.setMarca(marca);
        obj.setModelo(modelo);
        obj.setEstado(estado);
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
