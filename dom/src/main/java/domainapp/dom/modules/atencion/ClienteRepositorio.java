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
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;
import domainapp.dom.modules.servicios.Direccion;
import domainapp.dom.modules.servicios.Localidad.E_localidades;
import domainapp.dom.modules.servicios.Persona.E_nacionalidad;
import domainapp.dom.modules.servicios.Persona.E_sexo;
import domainapp.dom.modules.servicios.Localidad;

@DomainService(repositoryFor = Cliente.class)
@DomainServiceLayout(menuOrder = "10" , named="Cliente")
public class ClienteRepositorio {

    //region > listAll (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "1")
    public List<Cliente> listAll() {
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
    @MemberOrder(sequence = "2")
    public List<Cliente> findByApellido(
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
    @MemberOrder(sequence = "3")
    public Cliente create(
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
        final Cliente obj = container.newTransientInstance(Cliente.class);
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
        obj.setHabilitado('S');
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
