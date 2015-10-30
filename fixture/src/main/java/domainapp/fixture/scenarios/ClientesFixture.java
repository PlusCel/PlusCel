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

import domainapp.dom.modules.atencion.Cliente;
import domainapp.dom.modules.atencion.ClienteRepositorio;
import domainapp.dom.modules.servicios.E_nacionalidad;
import domainapp.dom.modules.servicios.E_sexo;
import domainapp.dom.modules.servicios.Localidad.E_localidades;
import domainapp.fixture.modules.ClienteTearDown;
import domainapp.fixture.modules.GenericData;


import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.joda.time.LocalDate;

public class ClientesFixture extends FixtureScript {

    public ClientesFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    @Override
    protected void execute(ExecutionContext executionContext) {


    	BorrarDBClientes(executionContext);
        
        int Cantidad=GenericData.ObtenerCantidad()*14;
        
        List<Cliente> listAl=new ArrayList<Cliente>();
        
        // create
        for(int x=0; x<=Cantidad;x++)
        {
        	Cliente al=new Cliente();
        	al.setNombre(GenericData.ObtenerNombre());
        	al.setApellido(GenericData.ObtenerApellido());
        	
        	listAl.add(al);
        	
        }
        for(Cliente al:removerrepetidos(listAl))
        create(al.getApellido(),al.getNombre(),GenericData.Random(40000000, 60000000),E_sexo.MASCULINO,LocalDate.now(),E_nacionalidad.ARGENTINA, E_localidades.NEUQUEN,GenericData.ObtenerCalle(), GenericData.Random(1, 9999),null,null,null,String.valueOf(GenericData.Random(10000000, 88888888)), executionContext);
    }

    // //////////////////////////////////////

	private List<Cliente> removerrepetidos(List<Cliente> listaCliente)
	{
		
		for(int x=0;x<listaCliente.size()-1;x++)
		{
				for(int y=x+1;y<listaCliente.size();y++)
				{
					
					if(listaCliente.get(x).getNombre().equals(listaCliente.get(y).getNombre()) && listaCliente.get(x).getApellido().equals(listaCliente.get(y).getApellido()))
					{
						listaCliente.remove(y);
					}
					
				}
		}
		
		return listaCliente;
	}
    
    @SuppressWarnings("deprecation")
	private Cliente create(final String apellido, String nombre,int dni,E_sexo sexo,LocalDate nacimiento,E_nacionalidad nacionalidad, E_localidades localidad, String calle, int numero, String piso,String departamento,String  email,String telefono, ExecutionContext executionContext) {
        return executionContext.add(this, Clientes.altaCliente(apellido, nombre, dni, sexo, nacimiento, nacionalidad, localidad, calle, numero, piso, departamento,email, telefono));
    }

    // //////////////////////////////////////

    @SuppressWarnings("deprecation")
	public void BorrarDBClientes(ExecutionContext executionContext)
    {
        execute(new ClienteTearDown(), executionContext);
    	
       return;
    }
    
    
    @javax.inject.Inject
    private ClienteRepositorio Clientes;


}
