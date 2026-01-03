import java.util.Objects;

public class ClassificationRule {

    private final EventType triggerEvent;
    private final IncidentType incidentType;
    private final Severity severity;

    public ClassificationRule(EventType triggerEvent,
                              IncidentType incidentType,
                              Severity severity) {
        this.triggerEvent = Objects.requireNonNull(triggerEvent);
        this.incidentType = Objects.requireNonNull(incidentType);
        this.severity = Objects.requireNonNull(severity);
    }

    // Comprova si algun event de l'incident activa la regla
    public boolean matches(Incident incident) {
        return incident.getEvents().stream()
                .anyMatch(e -> e.getEventType() == triggerEvent);
    }

    // Aplica la classificació
    public void apply(Incident incident) {
        incident.setType(incidentType);
        incident.setSeverity(severity);
    }

    @Override
    public String toString() {
        return "Rule{" + triggerEvent + " -> " + incidentType + " (" + severity + ")}";
    }
}

