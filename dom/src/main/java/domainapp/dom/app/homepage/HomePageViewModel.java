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
package domainapp.dom.app.homepage;

import domainapp.dom.modules.atencion.Cliente;
import domainapp.dom.modules.atencion.ClienteRepositorio;
import domainapp.dom.modules.servicios.E_estado;
import domainapp.dom.modules.servicios.E_estadoPresupuesto;
import domainapp.dom.modules.servicios.EnvioCorreo;
import domainapp.dom.modules.atencion.OrdenServicio;
import domainapp.dom.modules.atencion.OrdenServicioRepositorio;

import java.util.List;

import org.apache.isis.applib.annotation.ViewModel;


@ViewModel
public class HomePageViewModel {
	
	
    //region > title
    public String title() {
    	if (getObjects().size() > 1 ){
    		 return getObjects().size() + " Ordenes de servicio sin reparación ";
    	}else if (getObjects().size() == 1) {
    		return getObjects().size() + " Orden de servicio sin reparación";
    	}else{
    		return "No hay ordenes de servicio sin reparación ";
    	}
       
    }
    //endregion
    
  

    //region > object (collection)
    //@org.apache.isis.applib.annotation.HomePage   
   
   public List<OrdenServicio> getObjects() {
        return OrdenServicioRepositorio.buscarPorEstado(E_estado.NO_SE_REPARA);
        
   }

    
    @javax.inject.Inject
    OrdenServicioRepositorio OrdenServicioRepositorio;
   //ClienteRepositorio ClienteRepositorio;
    

    //endregion
}
