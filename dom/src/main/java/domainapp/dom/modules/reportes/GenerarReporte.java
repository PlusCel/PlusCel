/*
 * This is a software made for highschool management 
 * 
 * Copyright (C) 2014, Fourheads
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * 
 * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */


package domainapp.dom.modules.reportes;


import javax.swing.JOptionPane;






import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.activation.MimeType;
import javax.inject.Named;
import javax.swing.JFrame;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.value.Blob;



public class GenerarReporte {
	
	public static void generarReporte(String jrxml, List<Object> parametros, E_formato formato, String nombreArchivo)  throws JRException{
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//Levanta el jrxml
		File file = new File(nombreArchivo,jrxml);
			
			
			  JOptionPane.showMessageDialog(null, "¡Este es el jrxml!" + jrxml);
			  JOptionPane.showMessageDialog(null, "¡Este es el nombreArchivo!" + nombreArchivo );
			  JOptionPane.showMessageDialog(null, "¡Este es el file!" + file);
			  JOptionPane.showMessageDialog(null, "¡Este es el parametro!" + parametros);
			 

		
		//Almacena el array de datos
		JRBeanArrayDataSource jArray= new JRBeanArrayDataSource(parametros.toArray());
		
		InputStream input = null;
		try{
			input = new FileInputStream(file);
			JOptionPane.showMessageDialog(null, "¡Entro al input!" + input);

		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		//Levanta el modelo del reporte
		//Verifico si esta null
		if (input == null) {
			
				  JOptionPane.showMessageDialog(null, "¡No tiene nada el input!");
				 
			} else {
			// la otra cosa
				JOptionPane.showMessageDialog(null, "¡/Levanta el modelo del reporte!");
			}
		
		
		JasperDesign jd = JRXmlLoader.load(input);
		
		
		
		//Compila el reporte
		JasperReport reporte = JasperCompileManager.compileReport(jd);
		JOptionPane.showMessageDialog(null, "Compila el reporte" + reporte );
		
		//map.put("empleados",jArray.toString());
		//Lo llena con los datos del datasource
		JasperPrint print = JasperFillManager.fillReport(reporte, map, jArray);
//		JOptionPane.showMessageDialog(null, "¡Lo llena con los datos del datasource el PRINT" + print.toString()	);
//		JOptionPane.showMessageDialog(null, "¡Lo llena con los datos del datasource el REPORTE" + reporte.toString() );
		JOptionPane.showMessageDialog(null, "¡Lo llena con los datos del datasource el MAP" + map.toString() );
		JOptionPane.showMessageDialog(null, "¡Lo llena con los datos del datasource el JARRAY" + jArray.toString() );
		
		//Lo muestra con el jasperviewer
		JasperViewer.viewReport(print, false);
//		JOptionPane.showMessageDialog(null, "¡Lo muestra con el jasperviewer");
					
		//nombreArchivo = reportes/(calificaciones o asistencia/(nombre)
		
		if(formato == E_formato.HojadeCálculo){
			
				JRXlsExporter exporterXLS = new JRXlsExporter();
				
				exporterXLS.setExporterInput(new SimpleExporterInput(print));
				exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(nombreArchivo + ".xls"));
				
				SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
				configuration.setOnePagePerSheet(true);
				configuration.setDetectCellType(true);
				configuration.setCollapseRowSpan(false);
				
				exporterXLS.setConfiguration(configuration);
				exporterXLS.exportReport();
				
		}else{
			if(formato == E_formato.PDF){				
				JasperExportManager.exportReportToPdfFile(print, nombreArchivo + ".pdf" );
				JOptionPane.showMessageDialog(null, "¡lo exporta a PDF en :" + "" + print + " " + nombreArchivo);
				
			}
		}
		
		
		//Muestra el reporte en otra ventana
		//JasperExportManager.exportReportToHtmlFile(print, "reportemp/nuevo.html");
	
		
	}	
	
	
	@javax.inject.Inject	
	public ReportContext reportContext;
	
	@javax.inject.Inject
	DomainObjectContainer container;
	
}
