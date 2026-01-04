import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Logger logger = new Logger();

        // Classifier i regles
        Classifier classifier = new Classifier(logger);
        classifier.addRule(new ClassificationRule(EventType.LOGIN_FAIL, IncidentType.BRUTE_FORCE, Severity.ALTA));

        Correlator correlator = new Correlator();

        IncidentManager incidentManager = new IncidentManager(correlator, classifier);

        PlaybookEngine playbookEngine = new PlaybookEngine();

        Responder responder = new Responder(logger);

        SOARSystem soar = new SOARSystem(incidentManager, playbookEngine, responder, logger);

        Map<String, String> details = new HashMap<>();
        details.put("reason", "5 intents fallits");

        SecurityEvent event = new SecurityEvent("EVT-1", LocalDateTime.now(), "192.168.1.10", "admin", EventType.LOGIN_FAIL, details);

        soar.processEvent(event);

    }
}