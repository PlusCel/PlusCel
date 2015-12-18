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
