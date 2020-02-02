
package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.IOException;

/**
 * @author rahul.kumar
 * @version $Id: ExtentReport.java, v 0.1 2020-02-01 11:43 rahul.kumar Exp $$
 */
public class ExtentReport implements Report{


    private final ExtentReports extent = Extent.REPORT;
    private final ThreadLocal<ExtentTest> test = Extent.TEST;



    public ExtentReport(final File file) {
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(file.getPath());
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(false);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(file.getName());
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(file.getName());
        this.extent.attachReporter(htmlReporter);
    }


    @Override
    public void step(String s, Object... valuesToBeReplacePlaceholders) {
        this.test.get().info(formatLoggingString("<b>" + s, valuesToBeReplacePlaceholders));
    }

    @Override
    public void info(String s, Object... valuesToBeReplacePlaceholders) {
        this.test.get().info(formatLoggingString(s, valuesToBeReplacePlaceholders));
    }

    @Override
    public void warn(String s, Object... valuesToBeReplacePlaceholders) {
        this.test.get().warning(formatLoggingString(s, valuesToBeReplacePlaceholders));
    }

    @Override
    public void error(String s, Object... valuesToBeReplacePlaceholders) {
        this.test.get().error(formatLoggingString(s, valuesToBeReplacePlaceholders));
    }


    private String formatLoggingString(String s, Object... valuesToBeReplacePlaceholders) {
        for (Object o : valuesToBeReplacePlaceholders) {
            s = s.replaceFirst("\\{\\}", o.toString());
        }
        return s;
    }
}