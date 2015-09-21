package domainapp.dom.modules.atencion;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.QueryDefault;

import org.joda.time.LocalDate;
import domainapp.dom.modules.servicios.Direccion;
import domainapp.dom.modules.servicios.Localidad.E_localidades;
import domainapp.dom.modules.servicios.E_nacionalidad;
import domainapp.dom.modules.servicios.E_sexo;
import domainapp.dom.modules.servicios.Localidad;


@DomainService(repositoryFor = Tecnico.class)
@DomainServiceLayout(menuOrder = "5" , named="Tecnico")

public class TecnicoRepositorio {

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
    public List<Tecnico> buscarPorApellido(
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
    public Tecnico altaTecnico(
            final @ParameterLayout(named="Apellido") String apellido,
            final @ParameterLayout(named="Nombre") String nombre,
            final @ParameterLayout(named="Dni") int dni,
            final @ParameterLayout(named="Sexo") E_sexo sexo,
            final @ParameterLayout(named="Fecha Nacimiento") @Parameter(optionality=Optionality.OPTIONAL)  LocalDate nacimiento,
            final @ParameterLayout(named="Nacionalidad") @Parameter(optionality=Optionality.OPTIONAL)  E_nacionalidad nacionalidad,
            final @ParameterLayout(named="Localidad") @Parameter(optionality=Optionality.OPTIONAL)  E_localidades localidad,
            final @ParameterLayout(named="Calle") @Parameter(optionality=Optionality.OPTIONAL)  String calle,
            final @ParameterLayout(named="Numero") @Parameter(optionality=Optionality.OPTIONAL)  java.lang.Integer  numero ,
            final @ParameterLayout(named="Piso") @Parameter(optionality=Optionality.OPTIONAL)  String piso,
            final @ParameterLayout(named="Departamento") @Parameter(optionality=Optionality.OPTIONAL)  String departamento,
            final @ParameterLayout(named="Email") @Parameter(optionality=Optionality.OPTIONAL)  String email,
            final @ParameterLayout(named="Teléfono") String telefono,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Local")String local
            
            
    		) {
        final Tecnico obj = container.newTransientInstance(Tecnico.class);
        final Direccion dire = new Direccion();
        final Localidad loca = new Localidad();
        loca.setNombre(localidad);
        if (calle != null && !calle.isEmpty()) { dire.setCalle(calle.toUpperCase());}
        dire.setNumero(numero);
        if (piso != null && !piso.isEmpty()) { dire.setPiso(piso);}
        if (departamento != null && !departamento.isEmpty()) {dire.setDepartamento(departamento);}
        if (loca != null) {dire.setLocalidad(loca);}
        obj.setSexo(sexo);
        obj.setNombre(nombre.toUpperCase());
        obj.setApellido(apellido.toUpperCase());
        obj.setDni(dni);
        obj.setFechaNacimiento(nacimiento);
        obj.setDireccion(dire);            
        obj.setEmail(email);
        obj.setTelefono(telefono);
        obj.setApellido(apellido);
        obj.setLocal(local);

        container.persistIfNotAlready(obj);
        return obj;
    }

    //endregion

    //region > injected services

    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion
	
	
}
