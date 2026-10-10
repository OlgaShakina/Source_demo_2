package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult r) {
        System.out.printf("==== STARTING TEST %s ====%n", r.getName());
    }

    @Override
    public void onTestSuccess(ITestResult r) {
        System.out.printf("==== FINISHED TEST %s Duration: %ss ====%n",
                r.getName(), getExecutionTime(r));
    }

    @Override
    public void onTestFailure(ITestResult r) {
        System.out.printf("==== FAILED TEST %s Duration: %ss ====%n",
                r.getName(), getExecutionTime(r));
    }

    @Override
    public void onTestSkipped(ITestResult r) {
        System.out.printf("==== SKIPPING TEST %s ====%n", r.getName());
    }

    private long getExecutionTime(ITestResult r) {
        return TimeUnit.MILLISECONDS.toSeconds(r.getEndMillis() - r.getStartMillis());
    }
}