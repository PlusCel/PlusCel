/*
 * This is a software made for highschool management 
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

import java.util.ArrayList;
import java.util.List;

import domainapp.dom.modules.atencion.Tecnico;
import domainapp.dom.modules.atencion.TecnicoRepositorio;
import domainapp.dom.modules.servicios.E_nacionalidad;
import domainapp.dom.modules.servicios.E_sexo;
import domainapp.dom.modules.servicios.Localidad.E_localidades;
import domainapp.fixture.modules.GenericData;

import domainapp.fixture.modules.TecnicoTearDown;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.joda.time.LocalDate;

public class TecnicoFixture extends FixtureScript {

    public TecnicoFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    @Override
    protected void execute(ExecutionContext executionContext) {

        // prereqs
    	BorrarDBTecnico(executionContext);
        
        int Cantidad=GenericData.ObtenerCantidad();
        
        List<Tecnico> listAl=new ArrayList<Tecnico>();
        
        // create
        for(int x=0; x<=Cantidad;x++)
        {
        	Tecnico al=new Tecnico();
        	al.setNombre(GenericData.ObtenerNombre());
        	al.setApellido(GenericData.ObtenerApellido());
        	al.setLocal(GenericData.ObtenerLocal());
        	listAl.add(al);
        	
        }
        for(Tecnico al:removerrepetidos(listAl))
        create(al.getApellido(),al.getNombre(),GenericData.Random(40000000, 60000000),E_sexo.MASCULINO,LocalDate.now(),E_nacionalidad.ARGENTINA, E_localidades.NEUQUEN,GenericData.ObtenerCalle(), GenericData.Random(1, 9999),null,null,null,String.valueOf(GenericData.Random(10000000, 88888888)),al.getLocal(), executionContext);
    }

    // //////////////////////////////////////

	private List<Tecnico> removerrepetidos(List<Tecnico> listaTecnico)
	{
		
		for(int x=0;x<listaTecnico.size()-1;x++)
		{
				for(int y=x+1;y<listaTecnico.size();y++)
				{
					
					if(listaTecnico.get(x).getNombre().equals(listaTecnico.get(y).getNombre()) && listaTecnico.get(x).getApellido().equals(listaTecnico.get(y).getApellido()))
					{
						listaTecnico.remove(y);
					}
					
				}
		}
		
		return listaTecnico;
	}
    
    @SuppressWarnings("deprecation")
	private Tecnico create(final String apellido, String nombre,int dni,E_sexo sexo,LocalDate nacimiento,E_nacionalidad nacionalidad, E_localidades localidad, String calle, int numero, String piso,String departamento,String  email,String telefono, String local, ExecutionContext executionContext) {
        return executionContext.add(this, Tecnico.altaTecnico(apellido, nombre, dni, sexo, nacimiento, nacionalidad, localidad, calle, numero, piso, departamento,email, telefono, local));
    }

    // //////////////////////////////////////

    @SuppressWarnings("deprecation")
	public void BorrarDBTecnico(ExecutionContext executionContext)
    {
        execute(new TecnicoTearDown(), executionContext);
    	
       return;
    }
    
    @javax.inject.Inject
    private TecnicoRepositorio Tecnico;
//    @javax.inject.Inject
//    private IsisJdoSupport isisJdoSupport; 

}
