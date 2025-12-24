package prestashop.util;

import com.microsoft.playwright.TimeoutError;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TimeoutRetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY = 3;

    @Override
    public boolean retry(ITestResult result) {
        Throwable throwable = result.getThrowable();
        if (throwable == null) {
            return false;
        }
        Throwable rootCause = getRootCause(throwable);
        if (rootCause instanceof TimeoutError && retryCount < MAX_RETRY) {
            retryCount++;
            return true;
        }
        return false;
    }

    private Throwable getRootCause(Throwable t) {
        Throwable cause = t;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        return cause;
    }
}
