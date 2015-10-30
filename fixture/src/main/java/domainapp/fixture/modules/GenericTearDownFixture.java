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

package domainapp.fixture.modules;

import java.util.ArrayList;
import java.util.List;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.objectstore.jdo.applib.service.support.IsisJdoSupport;



public class GenericTearDownFixture extends FixtureScript {
	
	private String Etable="";
	
	public GenericTearDownFixture(String table)
	{
		withDiscoverability(Discoverability.DISCOVERABLE);
		Etable=table;
	}
	
	public GenericTearDownFixture()
	{
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	@Override
	protected void execute(ExecutionContext executionContext) {
		
		if(Etable!="")
			BorrarContains();
		else
		{
		BorrarDB();
		
		String Data="Se ha completado la operacion. Toda la DB ah ido borrada.";
		create(Data,executionContext);
		}
	}
    
    private String create(final String Data, ExecutionContext executionContext) {
        return executionContext.add(this, Data);
    }
	
    private void BorrarContains()
    {
    	DeleteAndTruncateTable(Etable);
    	
    	for(String tabla:Tablas())
    	{
    		if(tabla.contains(Etable))
    		{
    			DeleteAndTruncateTable(tabla);
    		}
    	}
    	
    }
    
	private void DeleteAndTruncateTable(String table)
	{
    	if(table!=null || table!="")
    	{
    			isisJdoSupport.executeUpdate("TRUNCATE \""+table+"\" CASCADE");
    	}
	}

	private void DeleteTable(String table)
	{
    	if(table!=null || table!="")
    	{
    		isisJdoSupport.executeUpdate("DELETE FROM \""+table+"\"");
    	}
		
	}
	
    private void BorrarDB()
    {
    	for(String tabla:Tablas())
    	{
    		DeleteAndTruncateTable(tabla);
    		DeleteTable(tabla);
    		//RestarTable(tabla);
    	}
    	
    	isisJdoSupport.executeSql("SELECT pg_stat_reset()");
    	
    	//isisJdoSupport.executeUpdate("SELECT tablename FROM pg_tables WHERE schemaname = 'public'");
    }
    
    protected List<String> Tablas()
    {
    	List<String> Tablas=new ArrayList<String>();
    	
    	String tablas="Tecnico,Cliente,Marca";
    	String[] partes = tablas.split(",");
    	
    	for(int x=0;x<partes.length;x++)
    		Tablas.add(partes[x]);

    	return Tablas;
    }
    

    
    @javax.inject.Inject
    private IsisJdoSupport isisJdoSupport;

}
