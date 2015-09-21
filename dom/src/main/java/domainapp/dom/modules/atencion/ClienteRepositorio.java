/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
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
    public List<Cliente> buscarPorApellido(
            @ParameterLayout(named="Apellido")
            final String apellido
    ) {
        return container.allMatches(
                new QueryDefault<>(
                        Cliente.class,
                        "findByApellido",
                        "apellido", apellido));
    }
    //endregion

    //region > create (action)
    @MemberOrder(sequence = "1")
    public Cliente altaCliente(
            final  @ParameterLayout(named="Apellido") String apellido,
            final @ParameterLayout(named="Nombre") String nombre,
            final @ParameterLayout(named="Dni") int dni,
            final @ParameterLayout(named="Sexo") E_sexo sexo,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Fecha Nacimiento")  LocalDate nacimiento,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Nacionalidad") E_nacionalidad nacionalidad,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Localidad") E_localidades localidad,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Calle") String calle,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Numero") java.lang.Integer numero,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Piso") String piso,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Departamento") String departamento,
            final @Parameter(optionality=Optionality.OPTIONAL) @ParameterLayout(named="Email") String email,
            final @ParameterLayout(named="Teléfono") String telefono
            
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
        obj.setHabilitado('-');
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
