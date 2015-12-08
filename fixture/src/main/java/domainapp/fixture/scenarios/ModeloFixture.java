
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
import domainapp.dom.modules.atencion.Modelo;
import domainapp.dom.modules.atencion.ModeloRepositorio;
import domainapp.fixture.modules.GenericData;
import domainapp.fixture.modules.ModeloTearDown;

import java.util.ArrayList;
import java.util.List;
import org.apache.isis.applib.fixturescripts.FixtureScript;


public class ModeloFixture extends FixtureScript {

    public ModeloFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    @Override
    protected void execute(ExecutionContext executionContext) {
      
    	BorrarDBModelo(executionContext);
        
        int Cantidad=GenericData.ObtenerCantidad();
        
        List<Modelo> listAl=new ArrayList<Modelo>();
        
        // create
        for(int x=0; x<=Cantidad;x++)
        {
        	Modelo al=new Modelo();
        	al.setAbreviatura(GenericData.ObtenerModeloAbreviatura());
        	al.setAbreviatura(GenericData.ObtenerModeloDescripcion());
        	al.setDescripcion(GenericData.ObtenerModeloId());
        	
        	listAl.add(al);
        	
        }
        for(Modelo al:removerrepetidos(listAl))
        create(al.getMarca(),al.getDescripcion(),al.getAbreviatura(), executionContext);
    }

    // //////////////////////////////////////

	private List<Modelo> removerrepetidos(List<Modelo> listaModelo)
	{
		
		for(int x=0;x<listaModelo.size()-1;x++)
		{
				for(int y=x+1;y<listaModelo.size();y++)
				{
					
					if(listaModelo.get(x).getAbreviatura().equals(listaModelo.get(y).getAbreviatura()) && listaModelo.get(x).getDescripcion().equals(listaModelo.get(y).getDescripcion()))
					{
						listaModelo.remove(y);
					}
					
				}
		}
		
		return listaModelo;
	}
    
    @SuppressWarnings("deprecation")
	private Modelo create(final Marca marca, String abreviatura, String descripcion,ExecutionContext executionContext) {
        return executionContext.add(this, Modelo.ingresarModelo(descripcion, abreviatura,marca, null));
    }

    ////////////////////////////////////////

    @SuppressWarnings("deprecation")
	public void BorrarDBModelo(ExecutionContext executionContext)
    {
        execute(new ModeloTearDown(), executionContext);
    	
       return;
    }
      
    @javax.inject.Inject
    private ModeloRepositorio Modelo;

    }

