/*
 * This is software for administration and management of mobile equipment repair
 *
 * Copyright ( C ) 2015 , Pluscel
 *
 * This program is free software ; you can redistribute it and / or
 * Modify it under the terms of the GNU General Public License
 * As published by the Free Software Foundation ; either version 2
 * Of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * But WITHOUT ANY WARRANTY; without even the implied warranty
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. Boil
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * Along with this program ; if not, write to the Free Software
 *
 *
 * Foundation , Inc. , 51 Franklin Street, Fifth Floor , Boston, MA 02110-1301 , USA .
 */
package domainapp.dom.modules.reportes;

import org.joda.time.LocalDate;


/**
 * @author PlusCel
 *
 */


public class PresupuestoReporte {
	
	
	
	private long numero;

	public long getNumero() {
		return numero;
	}

	public void setNumero(final long numero) {
		this.numero = numero;
	}
	
	

	private String cliente;	
	

	public String getCliente() {
		return cliente;
	}


	public void setCliente(final String cliente) {
		this.cliente = cliente;
	}
	
	// {{ Equipo (property)
	private String equipo;


	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(final String equipo) {
		this.equipo =equipo;
	}
	
 
	
 // {{ FechaHora (property)
 	private String fecha;

 	public String getFecha() {
 		return fecha;
 	}

 	public void setFecha(final String fecha) {
 		this.fecha = fecha;
 	}

 	// }}
 	
    
 	//{{ Diagnostico (property)
    private String diagnostico;

   
    public String getDiagnostico(){
        return diagnostico;
    }
    public void setDiagnostico(final String diagnostico) {
        this.diagnostico = diagnostico;
    }      
   //}}
 			
  //{{ Reparacion Requerida(property)
    private String requerida;

  
    public String getRequerida(){
        return requerida;
    }
    public void setRequerida(final String requerida) {
        this.requerida = requerida;
    }      
   //}}
    
 // {{  (property)
  	private double importe;

  	public double getImporte() {
  		return importe;
  	}
  
  	public void setImporte(final double importe) {
  		this.importe = importe;
  	}
  	

 	//{{ Observacion (property)
    private String observacion;


    public String getObservacion(){
        return observacion;
    }
    public void setObservacion(final String observacion) {
        this.observacion = observacion;
    }      
   //}}
    
		
}
