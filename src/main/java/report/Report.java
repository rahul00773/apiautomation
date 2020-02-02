
package report;

/**
 * @author rahul.kumar
 * @version $Id: Report.java, v 0.1 2020-02-01 11:43 rahul.kumar Exp $$
 */
public interface Report {
    void step(String s, Object... valuesToBeReplacePlaceholders);

    void info(String s, Object... valuesToBeReplacePlaceholders);

    void warn(String s, Object... valuesToBeReplacePlaceholders);

    void error(String s, Object... valuesToBeReplacePlaceholders);

}