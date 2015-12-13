
package domainapp.dom.modules.atencion;
import domainapp.dom.modules.atencion.Modelo;
import domainapp.dom.modules.atencion.Marca;
import domainapp.dom.modules.atencion.OrdenServicio;
import domainapp.dom.modules.atencion.ModeloRepositorio;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.query.QueryDefault;
import domainapp.dom.modules.servicios.E_estado;
import domainapp.dom.modules.atencion.TipoFalla;
import domainapp.dom.modules.atencion.TipoFallaRepositorio;
import domainapp.dom.modules.atencion.EquipoRepositorio;
import domainapp.dom.modules.atencion.OrdenServicio;
import domainapp.dom.modules.atencion.OrdenServicioRepositorio;
import domainapp.dom.modules.atencion.Tecnico;
import org.apache.isis.applib.annotation.Programmatic;

import java.util.ArrayList;
import java.util.List;
@DomainService(repositoryFor = FallaEquipoTecnico.class)
@DomainServiceLayout(menuOrder = "5" , named="Tecnico")
public class FallaEquipoTecnicoRepositorio {
	
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
	
	//region > listarTodos (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )

    //region > create (action)
    @MemberOrder(sequence = "1")
    public FallaEquipoTecnico IngresarRegistroDeReparacion(
    		final @ParameterLayout(named="Número de orden") OrdenServicio orden,
    		final @ParameterLayout(named="Tipo de Falla") TipoFalla tipoFalla,
            final @ParameterLayout(named="Equipo") Equipo equipo,
            final @ParameterLayout(named="Tecnico") Tecnico tecnico,
            final @ParameterLayout(named="Falla descripcion", multiLine=10) String fallaDesc,
            final @ParameterLayout(named="Estado") E_estado estado) {
    	
        final FallaEquipoTecnico obj = container.newTransientInstance(FallaEquipoTecnico.class);
        obj.setOrdenServicio(orden);
        final Falla fallaCel = new Falla();
        fallaCel.setTipoFalla(tipoFalla);
        fallaCel.setDescripcion(fallaDesc);
        obj.setFalla(fallaCel);
        obj.setEquipo(equipo);
        obj.setTecnico(tecnico);
        obj.setEstado(estado);   
        container.persistIfNotAlready(obj);
        orden.setEstado(estado);
        container.persistIfNotAlready(orden);
        
        
        
        return obj;
    }
    

   /* 
    @Programmatic
	public List<TipoFalla> choices1FallaPorEquipoPorTecnico(final OrdenServicio orden)
	{
    	List<TipoFalla> ListaTipoFalla=new ArrayList<TipoFalla>();
    	 System.out.println("ssss");
    	for(TipoFalla oTipoFalla: tipoFallaRepositorio.listarTodosLosTiposFallas())
		{
			if(oTipoFalla.getDescripcion().equals("Display")){
    		 //if(oTipoFalla.getDescripcion().equals(orden.getTipoFalla().getDescripcion())){
				// System.out.println("uno");
				ListaTipoFalla.add(oTipoFalla);
				
			}
		}
    	return ListaTipoFalla;
	}
	*/	
/*	public List<Equipo> choices1altaFallaPorEquipoPorTecnico(final OrdenServicio orden)
	{/*
    	
    	List<LibroDiario> listalibro=new ArrayList<LibroDiario>();
		for(LibroDiario librodia: listaLibroDiarioDelCurso())
		{
			if(librodia.getCurso().getHabilitado()=='S')
				listalibro.add(librodia);
		}
		return listalibro;
			return EquipoRepositorio.buscarEquipoXOrden(orden);
	}*/
    //orden.getTipoFalla().getDescripcion()

    /* public List<Equipo> choices2altaFallaPorEquipoPorTecnico(final OrdenServicio orden)
	{
			return Equipo.buscarEquipoXOrden(orden);
	}
    public List<Tecnico> choices3altaFallaPorEquipoPorTecnico(final OrdenServicio orden)
	{
			return Tecnico.buscarTecnicoXOrden(orden);
	}
    
    
    public List<E_estado> choices4altaFallaPorEquipoPorTecnico(final OrdenServicio orden)
	{
			return orden.buscarOrdenXOrden(orden);
	}
    
    */
    @MemberOrder(sequence = "3")
    public List<FallaEquipoTecnico> listarTodos() {
        return container.allInstances(FallaEquipoTecnico.class);
    }
    //endregion
    
    @MemberOrder(sequence = "4")
    public List<FallaEquipoTecnico> registroDeMovimientoPorOrden(final OrdenServicio orden)
  {
        return container.allMatches(
                new QueryDefault<>(
                		FallaEquipoTecnico.class,
                        "registroDeMovimientoPorOrden",
                        "orden", orden));
    }

	
    //region > injected services

    @javax.inject.Inject 
    DomainObjectContainer container;        

	@javax.inject.Inject
	TipoFallaRepositorio tipoFallaRepositorio;
	
	@javax.inject.Inject
	TipoFalla tipoFalla;
	
	@javax.inject.Inject
	OrdenServicioRepositorio ordenRepositorio;
	
    //endregion
//endregion

 
}
