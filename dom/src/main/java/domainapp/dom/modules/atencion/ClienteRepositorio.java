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
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;

import domainapp.dom.modules.servicios.Direccion;
import domainapp.dom.modules.servicios.E_nacionalidad;
import domainapp.dom.modules.servicios.E_sexo;
import domainapp.dom.modules.servicios.Localidad;
import domainapp.dom.modules.servicios.Localidad.E_localidades;

@DomainService(repositoryFor = Cliente.class)
@DomainServiceLayout(menuOrder = "2" , named="Cliente")
public class ClienteRepositorio {
 //region > create (action)
    @MemberOrder(sequence = "1")
    public Cliente ingresarCliente(
            final  @ParameterLayout(named="Apellido") @Parameter(regexPattern = domainapp.dom.modules.validador.ValidadorCaracteres.ValidacionLetras.PERMITIDOS, maxLength = 40) String apellido,
            final @ParameterLayout(named="Nombre") @Parameter(regexPattern = domainapp.dom.modules.validador.ValidadorCaracteres.ValidacionLetras.PERMITIDOS, maxLength = 40)  String nombre,
            final @ParameterLayout(named="Dni")@Parameter(regexPattern = domainapp.dom.modules.validador.ValidadorCaracteres.ValidacionNumerica.PERMITIDOS, maxLength = 12)  int dni,
            final @ParameterLayout(named="Sexo") @Parameter(optionality=Optionality.OPTIONAL) E_sexo sexo,
            final @ParameterLayout(named="Fecha Nacimiento") @Parameter(optionality=Optionality.OPTIONAL) LocalDate nacimiento,
            final @ParameterLayout(named="Nacionalidad") @Parameter(optionality=Optionality.OPTIONAL) E_nacionalidad nacionalidad,
            final @ParameterLayout(named="Localidad") @Parameter(optionality=Optionality.OPTIONAL) E_localidades localidad,
            final @ParameterLayout(named="Calle") @Parameter(optionality=Optionality.OPTIONAL) String calle,
            final @ParameterLayout(named="Numero") @Parameter(optionality=Optionality.OPTIONAL, regexPattern = domainapp.dom.modules.validador.ValidadorCaracteres.ValidacionNumerica.PERMITIDOS, maxLength = 4)  java.lang.Integer numero,
            final @ParameterLayout(named="Piso") @Parameter(optionality=Optionality.OPTIONAL) String piso,
            final @ParameterLayout(named="Departamento") @Parameter(optionality=Optionality.OPTIONAL) String departamento,
            final @ParameterLayout(named="Email") @Parameter(optionality=Optionality.OPTIONAL, regexPattern = domainapp.dom.modules.validador.ValidadorCaracteres.ValidacionEmail.PERMITIDOS, maxLength = 50) String email,
            final @ParameterLayout(named="Teléfono") @Parameter(regexPattern = domainapp.dom.modules.validador.ValidadorCaracteres.ValidacionTel.PERMITIDOS, maxLength = 30) String telefono
            
    		) {
        final Cliente obj = container.newTransientInstance(Cliente.class);	
        final Direccion dire = new Direccion();
        final Localidad loca = new Localidad();
        loca.setNombre(localidad);
        if (calle != null && !calle.isEmpty()) { dire.setCalle(calle.toUpperCase());}
        dire.setNumero(numero);
        if (piso != null && !piso.isEmpty()) { dire.setPiso(piso);}
        if (departamento != null && !departamento.isEmpty()) { dire.setDepartamento(departamento);}
        if (loca != null) { dire.setLocalidad(loca);}
        obj.setSexo(sexo);
        obj.setNombre(nombre.toUpperCase());
        obj.setApellido(apellido.toUpperCase());
        obj.setDni(dni);
        obj.setFechaNacimiento(nacimiento);
        obj.setDireccion(dire);
        obj.setEmail(email);
        obj.setTelefono(telefono);        
        obj.setApellido(apellido);
        container.persistIfNotAlready(obj);
        container.informUser("El cliente a sido cargado correctamente");
        
        return obj;
    }

    //end region
    
    //region > listAll (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "2")
    public List<Cliente> listarTodos() {
        return container.allInstances(Cliente.class);
    }
    //endregion

    //region > findByApellido (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "3")
    public List<Cliente> buscarPorApellidoNombre(
            @ParameterLayout(named="Apellido") @Parameter(optionality=Optionality.OPTIONAL)    String apellido,
            @ParameterLayout(named="Nombre") @Parameter(optionality=Optionality.OPTIONAL)   String nombre
    ) {

        return container.allMatches(
                new QueryDefault<>(
                        Cliente.class,
                        "findByApellidoNombre",
                        "apellido", (apellido==null)?"":apellido,"nombre",(nombre==null)?"":nombre));
    }
    //endregion

    //region > injected services

	@Programmatic
	public static List<Cliente> ultimosClientes() {
		final List<Cliente> lista = container
				.allMatches(new QueryDefault<Cliente>(Cliente.class,
						"ultimosClientes"));
		if (lista.isEmpty())
			container.informUser("No hay clientes cargados");
		return lista;
	}
	/**
	 * Inyección del Contenedor.
	 */
	@javax.inject.Inject
	private static DomainObjectContainer container;
	

    //endregion
}
