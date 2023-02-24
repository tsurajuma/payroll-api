package com.umasuraj.payroll.api.utility;

import com.umasuraj.payroll.api.entity.MonthlyPay;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

/**
 *
 * @author umasuraj
 */
@Service
public class MonthlyPayReportAsPDF {
    
    public boolean generatePDF(List<MonthlyPay> monthlyPayList,
            Map<String, Object> parameters,
            String reportTemplateFile,
            String outputFile) {

        boolean flag = false;

        try {

            JRBeanCollectionDataSource dataSource
                    = new JRBeanCollectionDataSource(monthlyPayList);

            JasperReport report = JasperCompileManager.compileReport(reportTemplateFile);

            JasperPrint print
                    = JasperFillManager.fillReport(report, parameters, dataSource);

            JasperExportManager.exportReportToPdfFile(print, outputFile);

            System.out.println("Report Generated...");

            flag = true;

        } catch (Exception e) {
            System.out.println("Exception while generating Report");
            System.err.println(e);

            flag = false;
        }

        return flag;

    }
    // end generatePDF() method
    
}
