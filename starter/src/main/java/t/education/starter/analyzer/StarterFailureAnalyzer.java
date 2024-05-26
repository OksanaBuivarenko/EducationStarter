package t.education.starter.analyzer;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import t.education.starter.exception.StartupException;

public class StarterFailureAnalyzer extends AbstractFailureAnalyzer<StartupException> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, StartupException cause) {
        return new FailureAnalysis(cause.getMessage(),
                "Property 'starter.enabled'  must be true or false!", cause);
    }
}
