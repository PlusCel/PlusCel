//package domainapp.dom.modules.atencion;
//import java.util.List;
//
//import org.apache.isis.applib.DomainObjectContainer;
//import org.apache.isis.applib.annotation.Action;
//import org.apache.isis.applib.annotation.ActionLayout;
//import org.apache.isis.applib.annotation.BookmarkPolicy;
//import org.apache.isis.applib.annotation.DomainService;
//import org.apache.isis.applib.annotation.DomainServiceLayout;
//import org.apache.isis.applib.annotation.MemberOrder;
//import org.apache.isis.applib.annotation.ParameterLayout;
//import org.apache.isis.applib.annotation.SemanticsOf;
//import org.apache.isis.applib.query.QueryDefault;
//
//
//@DomainService(repositoryFor = Equipo.class)
//@DomainServiceLayout(menuOrder = "10" , named="Marca")
//
//public class MarcaRepositorio {
//	
//	//region > listAll (action)
//    @Action(
//            semantics = SemanticsOf.SAFE
//    )
//    @ActionLayout(
//            bookmarking = BookmarkPolicy.AS_ROOT
//    )
//    @MemberOrder(sequence = "1")
//    public List<Marca> listAll() {
//        return container.allInstances(Marca.class);
//    }
//    //endregion
//
//   // region > findByDescripcion (action)
//    @Action(
//            semantics = SemanticsOf.SAFE
//    )
//    @ActionLayout(
//            bookmarking = BookmarkPolicy.AS_ROOT
//    )
//    @MemberOrder(sequence = "2")
//    public List<Marca> findByDescripcion(
//            @ParameterLayout(named="Descripcion")
//            final String descripcion
//    ) {
//        return container.allMatches(
//                new QueryDefault<>(
//                        Marca.class,
//                        "findByDescripcion",
//                        "descripcion", descripcion));
//    }
//    //endregion
//
//    //region > create (action)
//    @MemberOrder(sequence = "3")
//    public Marca create(
//            final @ParameterLayout(named="Descripcion") String descripcion
//          
//    		) {
//        final Marca obj = container.newTransientInstance(Marca.class);
//        obj.setDescripcion(descripcion);
//   
//        container.persistIfNotAlready(obj);
//        return obj;
//    }
//
//    //endregion
//
//    //region > injected services
//
//    @javax.inject.Inject 
//    DomainObjectContainer container;
//
//    //endregion
//
//}
