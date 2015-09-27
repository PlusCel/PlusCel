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
@DomainServiceLayout(menuOrder = "4" , named="Repuestos")
public class RepuestoRepositorio {

    //region > listAll (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "2")
    public List<Repuesto> listarTodos() {
        return container.allInstances(Repuesto.class);
    }
    //endregion


    //region > create (action)
    @MemberOrder(sequence = "1")
    public Repuesto altaRepuestos(
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

    //region > injected services

    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion
}
