package com.fiona.phone2word;

import org.junit.rules.TestName;

public class TestCaseName extends TestName {
    private String className = "unknown";

    public TestCaseName(Object callingClass) {
        super();
        if (callingClass != null) {
            this.className = callingClass.getClass().getSimpleName();
        }
    }

    public String getCompleteTestName() {
        return String.format("%s.%s", className, this.getMethodName());
    }

    public String getClassName() {
        return className;
    }
}
