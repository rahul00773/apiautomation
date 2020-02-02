
package report;

import org.testng.Reporter;

/**
 * @author rahul.kumar
 * @version $Id: ReportNGReport.java, v 0.1 2020-02-01 12:09 rahul.kumar Exp $$
 */
public class ReportNGReport implements Report {

    @Override
    public void step(String s, Object... valuesToBeReplacePlaceholders) {
        org.testng.Reporter.log("<b><br><font color=\"blue\">STEP:</font> " + formatLoggingString(s, valuesToBeReplacePlaceholders), true);
    }

    @Override
    public void info(String s, Object... valuesToBeReplacePlaceholders) {
        org.testng.Reporter.log("<br><font color=\"banana yellow\">INFO:</font> " + formatLoggingString(s, valuesToBeReplacePlaceholders), true);
    }

    @Override
    public void warn(String s, Object... valuesToBeReplacePlaceholders) {
        org.testng.Reporter.log("<br><font color=\"orange\">WARN:</font> " + formatLoggingString(s, valuesToBeReplacePlaceholders), true);
    }

    @Override
    public void error(String s, Object... valuesToBeReplacePlaceholders) {
        Reporter.log("<br><font color=\"red\">ERROR:</font> " + formatLoggingString(s, valuesToBeReplacePlaceholders), true);
    }

    private String formatLoggingString(String s, Object... valuesToBeReplacePlaceholders) {
        for (Object o : valuesToBeReplacePlaceholders) {
            s = s.replaceFirst("\\{\\}", o.toString());
        }
        return s;
    }
}