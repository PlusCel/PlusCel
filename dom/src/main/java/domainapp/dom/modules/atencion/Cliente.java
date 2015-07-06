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

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.util.ObjectContracts;
import domainapp.dom.modules.servicios.Persona;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="id")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "find", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.dom.modules.atencion.Cliente "),
        @javax.jdo.annotations.Query(name = "findByDni", language = "JDOQL", value = "SELECT "
                    			+ "FROM dom.modules.atencion.Cliente " + "WHERE dni == :dni"),
        @javax.jdo.annotations.Query(
                name = "findByApellido", language = "JDOQL",
                value = "SELECT "
                        + "FROM domainapp.dom.modules.atencion.Cliente "
                        + "WHERE apellido.startsWith(:apellido)")
})

@javax.jdo.annotations.Unique(name="Cliente_name_UNQ", members = {"apellido"})
@DomainObject(
		bounded=true,
        objectType = "CLIENTE"
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)
public class Cliente extends Persona implements Comparable<Cliente> {


				
		public DomainObjectContainer getContainer() {
			return container;
		}

		public void setContainer(DomainObjectContainer container) {
			this.container = container;
		}

		@Override
		public int compareTo(Cliente cliente) {

			return ObjectContracts.compare(this, cliente, "apellido, nombre");
		}

		public String title() {
			return getApellido() + ", " + getNombre();
		}

		@javax.inject.Inject
	
		private DomainObjectContainer container;

	}
