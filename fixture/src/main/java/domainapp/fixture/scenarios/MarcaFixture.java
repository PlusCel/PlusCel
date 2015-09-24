
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

import domainapp.dom.modules.atencion.Marca;
import domainapp.dom.modules.atencion.MarcaRepositorio;
import domainapp.fixture.modules.GenericData;
import domainapp.fixture.modules.MarcaTearDown;

import java.util.ArrayList;
import java.util.List;
import org.apache.isis.applib.fixturescripts.FixtureScript;


public class MarcaFixture extends FixtureScript {


    public MarcaFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    @Override
    protected void execute(ExecutionContext executionContext) {

        // prereqs
    	BorrarDBMarca(executionContext);
        
        int Cantidad=GenericData.ObtenerCantidad()*14;
        
        List<Marca> listAl=new ArrayList<Marca>();
        
        // create
        for(int x=0; x<=Cantidad;x++)
        {
        	Marca al=new Marca();
        	al.setAbreviatura(GenericData.ObtenerAbreviatura());
        	al.setDescripcion(GenericData.ObtenerDescripcion());
        	
        	listAl.add(al);
        	
        }
        for(Marca al:removerrepetidos(listAl))
        create(al.getAbreviatura(),al.getDescripcion(), executionContext);
    }

    // //////////////////////////////////////

	private List<Marca> removerrepetidos(List<Marca> listaMarca)
	{
		
		for(int x=0;x<listaMarca.size()-1;x++)
		{
				for(int y=x+1;y<listaMarca.size();y++)
				{
					
					if(listaMarca.get(x).getAbreviatura().equals(listaMarca.get(y).getAbreviatura()) && listaMarca.get(x).getDescripcion().equals(listaMarca.get(y).getDescripcion()))
					{
						listaMarca.remove(y);
					}
					
				}
		}
		
		return listaMarca;
	}
    
    @SuppressWarnings("deprecation")
	private Marca create(final String abreviatura, String descripcion, ExecutionContext executionContext) {
        return executionContext.add(this, Marca.altaMarca(abreviatura, descripcion));
    }

    // //////////////////////////////////////

    @SuppressWarnings("deprecation")
	public void BorrarDBMarca(ExecutionContext executionContext)
    {
        execute(new MarcaTearDown(), executionContext);
    	
       return;
    }
    
    
    @javax.inject.Inject
    private MarcaRepositorio Marca;
//    @javax.inject.Inject
//    private IsisJdoSupport isisJdoSupport; 

    }

