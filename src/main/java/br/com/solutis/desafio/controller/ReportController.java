package br.com.solutis.desafio.controller;


import br.com.solutis.desafio.report.Report;
import br.com.solutis.desafio.report.ReportManager;
import br.com.solutis.desafio.service.ReportService;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    ServletContext context;

    public ReportController( JdbcTemplate jdbc){
        Report.jdbc = jdbc;
    }

    @Autowired
    public void setreportService(ReportService reportService) {
        this.reportService = reportService;
    }


    @ResponseBody
    @RequestMapping(value = "/report/{reportName}/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> imprimir(@PathVariable String reportName,@PathVariable Long id,HttpServletResponse response) throws JRException, IOException {

        try {

            String path = context.getContextPath()+"report/";
            Report report = (Report) Class.forName("br.com.solutis.desafio.report."+reportName).newInstance();
            return report.print(reportName,id);
        }catch(Exception e ){
            e.printStackTrace();
        }
        return null;

    }

    @ResponseBody
    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getRpt1(HttpServletResponse response) throws JRException, IOException {
        InputStream template = ReportManager.class.getResourceAsStream("teste.jrxml");
        // compile the report from the stream
        JasperReport report = JasperCompileManager.compileReport(template);
        // fill out the report into a print object, ready for export.
        JasperPrint print = JasperFillManager.fillReport(report, null,  new JREmptyDataSource());
        // export it!
        //File pdf = File.createTempFile("output.", ".pdf");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(print, os);


        byte[] contents = (os.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        //String filename = "Relatorio.pdf";
        //headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]>myResponse = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);

        return myResponse;
    }


}
