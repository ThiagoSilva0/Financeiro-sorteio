/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dre.relatorio;

import br.com.dre.domain.PlanoDeContas;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;


/**
 *
 * @author thiag
 */
public class RelatorioPlanoDeContas {

     private HttpServletResponse response;
    private FacesContext context;
    private ByteArrayOutputStream baos;
    private InputStream stream;
  

    public RelatorioPlanoDeContas() {
        this.context = FacesContext.getCurrentInstance();
        this.response = (HttpServletResponse) context.getExternalContext().getResponse();
    }
    
    /*
    defina um parametro: List<Objeto> lista, se usar JavaBean DataSource
    */
    public void getRelatorio(List<PlanoDeContas> planoDeContases){
        stream = this.getClass().getResourceAsStream("/report/VendaEdicao.jasper");
        Map<String, Object> params = new HashMap<String, Object>();
        baos = new ByteArrayOutputStream();
        for (PlanoDeContas planoDeContase : planoDeContases) {
            System.out.println(planoDeContases.toString());
        }
        try {
            
            JasperReport report = (JasperReport) JRLoader.loadObject(stream);
            
            /*Para usar JavaBeanDataSource defina: new JRBeanCollectionDataSource(lista)
            mude a string do getResourceAsStream("/report/reportPessoaJavaBeanDS.jasper")
            */
            JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(planoDeContases));
            JasperExportManager.exportReportToPdfStream(print, baos);
            
            response.reset();
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            response.setHeader("Content-disposition", "inline; filename=relatorio.pdf");
            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().flush();
            response.getOutputStream().close();
            
            context.responseComplete();
            
            
        } catch (JRException ex) {
            Logger.getLogger(RelatorioPlanoDeContas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RelatorioPlanoDeContas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
