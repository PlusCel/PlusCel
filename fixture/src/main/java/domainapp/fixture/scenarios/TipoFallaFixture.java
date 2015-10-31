package domainapp.fixture.scenarios;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScript.Discoverability;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;

import domainapp.dom.modules.atencion.TipoFalla;
import domainapp.dom.modules.atencion.TipoFallaRepositorio;
import domainapp.fixture.modules.GenericData;
import domainapp.fixture.modules.MarcaTearDown;
import domainapp.fixture.modules.TipoFallaTearDown;

public class TipoFallaFixture extends FixtureScript {
	
	  public TipoFallaFixture() {
	        withDiscoverability(Discoverability.DISCOVERABLE);
	    }
	  
	  
	  
	  @Override
	    protected void execute(ExecutionContext executionContext) {

	      
	    	BorrarDBTipoFalla(executionContext);
	        
	        int Cantidad=GenericData.ObtenerCantidad();
	        
	        List<TipoFalla> listAl=new ArrayList<TipoFalla>();
	        
	      
	        for(int x=0; x<=Cantidad;x++)
	        {
	        	TipoFalla al=new TipoFalla();	        	
	        	al.setDescripcion(GenericData.ObtenerDescripcionFalla());
	        	
	        	listAl.add(al);
	        	
	        }
	        for(TipoFalla al:removerrepetidos(listAl))
	        create(al.getDescripcion(), executionContext);
	    }
	  
	  
	  
	// //////////////////////////////////////

		private List<TipoFalla> removerrepetidos(List<TipoFalla> listaTipoFalla)
		{
			
			for(int x=0;x<listaTipoFalla.size()-1;x++)
			{
					for(int y=x+1;y<listaTipoFalla.size();y++)
					{
						
						if(listaTipoFalla.get(x).getDescripcion().equals(listaTipoFalla.get(y).getDescripcion()))
						{
							listaTipoFalla.remove(y);
						}
						
					}
			}
			
			return listaTipoFalla;
		}
		
		
		 @SuppressWarnings("deprecation")
			private TipoFalla create(final  String descripcion, ExecutionContext executionContext) {
		        return executionContext.add(this, TipoFalla.altaTipoFalla(descripcion));
		    }

		    // //////////////////////////////////////

		    @SuppressWarnings("deprecation")
			public void BorrarDBTipoFalla(ExecutionContext executionContext)
		    {
		        execute(new TipoFallaTearDown(), executionContext);
		    	
		       return;
		    }
		    
		    
		    @javax.inject.Inject
		    private TipoFallaRepositorio TipoFalla;
	  
	  

}
