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
package domainapp.dom.modules.servicios;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.MemberOrder;

@PersistenceCapable
public class Localidad {

	// {{ Nombre (property)
	private E_localidades nombre;

	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1")
	@Persistent
	public E_localidades getNombre() {
		return nombre;
	}

	public void setNombre(final E_localidades nombre) {
		this.nombre = nombre;
	}
	// }}

	
	public enum E_localidades{	
		NEUQUEN, CIPOLLETTI, PLOTTIER, CENTENARIO, ROCA, ALLEN;
	}
	
	@Column(allowsNull = "true")
	public String title(){
		return nombre.toString();
	}

	@Override
	@Column(allowsNull = "true")
	public String toString() {
		return nombre.toString();
	}
	
	
	
}
