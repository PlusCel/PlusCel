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

package domainapp.fixture.scenarios;

import domainapp.fixture.modules.MarcaCreate;
import domainapp.fixture.modules.MarcaTearDown;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.isis.applib.fixturescripts.FixtureScript;

public class RecreateMarcaFixture extends FixtureScript {

    public final List<String> ABREVIATURAS = Collections.unmodifiableList(Arrays.asList(
            "Foo", "Bart", "Baz", "Frodo", "Froyo", "Fizz", "Bip", "Bop", "Bang", "Boo", "Nacho"));
    
    public final List<String> DESCRIPCION = Collections.unmodifiableList(Arrays.asList(
            "ooF", "traB", "zaB", "1", "2", "3", "4", "5", "6", "7", "8"));

    public RecreateMarcaFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    //region > number (optional input)
    private Integer number;

    /**
     * The number of objects to create, up to 10; optional, defaults to 3.
     */
    public Integer getNumber() {
        return number;
    }

    public RecreateMarcaFixture setNumber(final Integer number) {
        this.number = number;
        return this;
    }
    //endregion

    @Override
    protected void execute(final ExecutionContext ec) {

        // defaults
        final int number = defaultParam("number", ec, 5);

        // validate
        if(number < 0 || number > ABREVIATURAS.size()) {
            throw new IllegalArgumentException(String.format("number must be in range [0,%d)", ABREVIATURAS.size()));
        }
        
        if(number < 0 || number > DESCRIPCION.size()) {
            throw new IllegalArgumentException(String.format("number must be in range [0,%d)", DESCRIPCION.size()));
        }

        //
        // execute
        //
        ec.executeChild(this, new MarcaTearDown());

        for (int i = 0; i < number; i++) {
            final MarcaCreate fs = new MarcaCreate().setAbreviatura(ABREVIATURAS.get(i));
            ec.executeChild(this, fs.getAbreviatura(), fs);
            
            final MarcaCreate fs1 = new MarcaCreate().setDescripcion(DESCRIPCION.get(i));
            ec.executeChild(this, fs1.getDescripcion(), fs1);
        }
    }
}
