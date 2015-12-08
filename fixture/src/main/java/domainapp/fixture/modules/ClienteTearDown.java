package domainapp.fixture.modules;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;


public class ClienteTearDown extends FixtureScript {
	
	 @Override
	    protected void execute(ExecutionContext executionContext) {
	        isisJdoSupport.executeUpdate("delete from \"Cliente\"");
	    }

	    @javax.inject.Inject
	    private IsisJdoSupport isisJdoSupport;

}
