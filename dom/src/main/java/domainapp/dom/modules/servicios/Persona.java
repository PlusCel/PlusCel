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
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import org.apache.isis.applib.annotation.MemberOrder;
import org.joda.time.LocalDate;

import javax.jdo.annotations.InheritanceStrategy;

///Gestion Servicios

@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.SUBCLASS_TABLE)
public class Persona {
	
	// {{ Dni (property)
	private int dni;

	@Persistent
	@MemberOrder(sequence = "3")
	@javax.jdo.annotations.Column(allowsNull="false")
	public int getDni() {
		return dni;
	}

	public void setDni(final int dni) {
		this.dni = dni;
	}
	// }}


	// {{ Apellido (property)
	private String apellido;

	@Persistent
	@MemberOrder(sequence = "1")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getApellido() {
		return apellido;
	}

	public void setApellido(final String apellido) {
		this.apellido = apellido;
	}
	// }}


	// {{ Nombre (property)
	private String nombre;

	@Persistent
	@MemberOrder(sequence = "2")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}
	// }}


	// {{ Direccion (property)
	private Direccion direccion;

	//@Hidden(where = Where.ALL_TABLES)
	@Persistent
	@MemberOrder(sequence = "7")
	@javax.jdo.annotations.Column(allowsNull="false", name = "DIRECCION_ID")
	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(final Direccion direccion) {
		this.direccion = direccion;
	}
	// }}


	// {{ FechaNacimiento (property)
	private LocalDate fechaNacimiento;

	@Persistent
	@MemberOrder(sequence = "6")
	@javax.jdo.annotations.Column(allowsNull="false")
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(final LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	// }}

	
	// {{ Nacionalidad (property)
	private E_nacionalidad nacionalidad;

	@Persistent
	@MemberOrder(sequence = "5")
	@javax.jdo.annotations.Column(allowsNull="false")
	public E_nacionalidad getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(final E_nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	// }}


	// {{ Sexo (property)
	private E_sexo sexo;

	
	@Persistent
	@MemberOrder(sequence = "4")
	@javax.jdo.annotations.Column(allowsNull="false")
	//@Hidden(where = Where.ALL_TABLES)
	public E_sexo getSexo() {
		return sexo;
	}

	public void setSexo(final E_sexo sexo) {
		this.sexo = sexo;
	}
	// }}


	// {{ Telefono (property)
	private String telefono;

	@Persistent
	@MemberOrder(sequence = "8")
	@javax.jdo.annotations.Column(allowsNull="true")
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(final String telefono) {
		this.telefono = telefono;
	}
	// }}


	private char habilitado;
	
	@Persistent
	@javax.jdo.annotations.Column(allowsNull = "true")
	//@Hidden
	public char getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(char habilitado) {
		this.habilitado = habilitado;
	}
	
	

	public enum E_sexo{
		
		MASCULINO, FEMENINO
	}
	
	public enum E_nacionalidad{
		
		ARGENTINA, BRASIL, CHILE, 
		COLOMBIA, ECUADOR, PARAGUAY, PERU,
		URUGUAY, VENEZUELA, OTRO
	}

}
