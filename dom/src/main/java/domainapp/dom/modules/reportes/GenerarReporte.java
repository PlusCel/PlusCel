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


import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.isis.applib.DomainObjectContainer;



public class GenerarReporte {
	
	public static void generarReporte(String jrxml, List<Object> parametros, String nombreArchivo)  throws JRException{
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//Levanta el jrxml
		
		
		File file = new File(jrxml);

		
		//Almacena el array de datos
		JRBeanArrayDataSource jArray= new JRBeanArrayDataSource(parametros.toArray());
		
		InputStream input = null;
		try{
			input = new FileInputStream(file);

		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		//Levanta el modelo del reporte			
		JasperDesign jd = JRXmlLoader.load(input);
						
		//Compila el reporte
		JasperReport reporte = JasperCompileManager.compileReport(jd);
		
		//Lo llena con los datos del datasource
		JasperPrint print = JasperFillManager.fillReport(reporte, map, jArray);
		
		//Lo muestra con el jasperviewer
		//JasperViewer.viewReport(print, false);
		
		
		JasperExportManager.exportReportToPdfFile(print, nombreArchivo + ".pdf");
		
		//Abre el reporte recien generado
		try {
		     File path = new File (nombreArchivo + ".pdf");
		     Desktop.getDesktop().open(path);
		}catch (IOException ex) {
		     ex.printStackTrace();
		}

							

		
	}	
	
	
	@javax.inject.Inject	
	public ReportContext reportContext;
	
	@javax.inject.Inject
	DomainObjectContainer container;
	
}
