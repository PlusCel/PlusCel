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

package domainapp.fixture.modules;

import domainapp.dom.modules.atencion.Marca;
import domainapp.dom.modules.atencion.MarcaRepositorio;

import org.apache.isis.applib.fixturescripts.FixtureScript;

public class MarcaCreate extends FixtureScript {

    //region > abreviatura (input)
    private String abreviatura;
    /**
     * Name of the object (required)
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    public MarcaCreate setAbreviatura(final String abreviatura) {
        this.abreviatura = abreviatura;
        return this;
    }
    //endregion
    
    //region > descripcion (input)
    private String descripcion;
    /**
     * Name of the object (required)
     */
    public String getDescripcion() {
        return descripcion;
    }

    public MarcaCreate setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
        return this;
    }
    //endregion

    //region > simpleObject (output)
    private Marca marca;

    /**
     * The created simple object (output).
     * @return
     */
    public Marca getmarca() {
        return marca;
    }
    //endregion

    @Override
    protected void execute(final ExecutionContext ec) {

        String abreviatura = checkParam("abreviatura", ec, String.class);
        String descripcion = checkParam("descripcion", ec, String.class);

        this.marca = MarcaRepositorio.altaMarca(abreviatura,descripcion);

        // also make available to UI
        ec.addResult(this, marca);
    }

    @javax.inject.Inject
    private MarcaRepositorio MarcaRepositorio;

}
