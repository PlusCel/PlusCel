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
package domainapp.fixture;

import domainapp.fixture.modules.GenericTearDownFixture;
import domainapp.fixture.scenarios.ClientesFixture;
import domainapp.fixture.scenarios.MarcaFixture;
import domainapp.fixture.scenarios.ModeloFixture;
import domainapp.fixture.scenarios.TecnicoFixture;
import domainapp.fixture.scenarios.TipoFallaFixture;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.fixturescripts.FixtureResult;
import org.apache.isis.applib.fixturescripts.FixtureScripts;

/**
 * Enables fixtures to be installed from the application.
 */
@DomainService
@DomainServiceLayout(
        named="Datos Ejemplos",
        menuBar = DomainServiceLayout.MenuBar.SECONDARY,
        menuOrder = "22"
)
public class DomainAppFixturesService extends FixtureScripts {

    public DomainAppFixturesService() {
        super("domainapp.fixture");
    }
       
    @MemberOrder(sequence="20")
    public Object instalarFixturesTecnico() {
        final List<FixtureResult> Tecnico = findFixtureScriptFor(TecnicoFixture.class).run(null);
        return Tecnico.get(0).getObject();
    }
    
    @MemberOrder(sequence="30")
    public Object instalarFixturesCliente() {
        final List<FixtureResult> Cliente = findFixtureScriptFor(ClientesFixture.class).run(null);
        return Cliente.get(0).getObject();
    }

    @MemberOrder(sequence="40")
    public Object instalarFixturesMarca() {
        final List<FixtureResult> Marca = findFixtureScriptFor(MarcaFixture.class).run(null);
        return Marca.get(0).getObject();
    }
    
    @MemberOrder(sequence="50")
    public Object instalarFixturesTipoFalla() {
        final List<FixtureResult> TipoFalla = findFixtureScriptFor(TipoFallaFixture.class).run(null);
        return TipoFalla.get(0).getObject();
    }
    
    @MemberOrder(sequence="60")
    public Object instalarFixturesModelo() {
        final List<FixtureResult> Modelo = findFixtureScriptFor(ModeloFixture.class).run(null);
        return Modelo.get(0).getObject();
    }
    
    
	@MemberOrder(sequence="70")
    public String BorrarBD()
    {
		final List<FixtureResult> Borrar = findFixtureScriptFor(GenericTearDownFixture.class).run(null);
		
		return "Se ha completado la operacion. Toda la DB ah sido borrada.";
    }
	
	  @MemberOrder(sequence="99")
	    public String IntstalarTodosLosFixtures()
	    {
	    	this.instalarFixturesTecnico();
	    	
	    	this.instalarFixturesCliente();

	    	this.instalarFixturesMarca(); 
	    	
	    	this.instalarFixturesTipoFalla();
	    	
	    	this.instalarFixturesModelo();
	    		    	
	    	return "Todos los fixtures instalados";
	    }
 
}