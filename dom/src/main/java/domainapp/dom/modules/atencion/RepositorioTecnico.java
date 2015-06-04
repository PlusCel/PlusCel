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

import domainapp.dom.modules.servicios.Direccion;
import domainapp.dom.modules.servicios.Localidad.E_localidades;
import domainapp.dom.modules.servicios.Persona.E_nacionalidad;
import domainapp.dom.modules.servicios.Persona.E_sexo;
import domainapp.dom.modules.servicios.Localidad;

@DomainService(repositoryFor = Tecnico.class)
@DomainServiceLayout(menuOrder = "10" , named="Tecnico")

public class RepositorioTecnico {

	 //region > listAll (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "3")
    public List<Tecnico> listarTodos() {
        return container.allInstances(Tecnico.class);
    }
    //endregion

    //region > findByApellido (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "2")
    public List<Tecnico> findByApellido(
            @ParameterLayout(named="Apellido")
            final String apellido
    ) {
        return container.allMatches(
                new QueryDefault<>(
                        Tecnico.class,
                        "findByApellido",
                        "apellido", apellido));
    }
    //endregion

    //region > create (action)
    @MemberOrder(sequence = "1")
    public Tecnico Alta(
            final @ParameterLayout(named="Apellido") String apellido,
            final @ParameterLayout(named="Nombre") String nombre,
            final @ParameterLayout(named="Dni") int dni,
            final @ParameterLayout(named="Sexo") E_sexo sexo,
            final @ParameterLayout(named="Fecha Nacimiento") LocalDate nacimiento,
            final @ParameterLayout(named="Nacionalidad") E_nacionalidad nacionalidad,
            final @ParameterLayout(named="Domicilio.Localidad") E_localidades localidad,
            final @ParameterLayout(named="Domicilio.Calle") String calle,
            final @ParameterLayout(named="Domicilio.Numero") int numero,
            final @ParameterLayout(named="Domicilio.Piso") String piso,
            final @ParameterLayout(named="Domicilio.Departamento") String departamento,
            final @ParameterLayout(named="Domicilio.Teléfono") String telefono
            
    		) {
        final Tecnico obj = container.newTransientInstance(Tecnico.class);
        final Direccion dire = new Direccion();
        final Localidad loca = new Localidad();
        loca.setNombre(localidad);
        dire.setCalle(calle.toUpperCase());
        dire.setNumero(numero);
        dire.setPiso(piso);
        dire.setDepartamento(departamento);
        dire.setLocalidad(loca);
        obj.setSexo(sexo);
        obj.setNombre(nombre.toUpperCase());
        obj.setApellido(apellido.toUpperCase());
        obj.setDni(dni);
        obj.setFechaNacimiento(nacimiento);
        obj.setDireccion(dire);
        obj.setTelefono(telefono);
        obj.setApellido(apellido);
        container.persistIfNotAlready(obj);
        return obj;
    }

    //endregion

    //region > injected services

    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion
	
	
}
