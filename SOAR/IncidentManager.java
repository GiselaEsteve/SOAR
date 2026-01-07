public class IncidentManager {
    private final Correlator correlator;
    private final Classifier classifier;

    public IncidentManager(Correlator correlator, Classifier classifier) {
        this.correlator = correlator;
        this.classifier = classifier;
    }

    public Incident processEvent(SecurityEvent event) {
        Incident incident = correlator.correlate(event);
        classifier.classify(incident);
        return incident;
    }
}
