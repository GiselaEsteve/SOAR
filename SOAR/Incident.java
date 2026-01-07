import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Incident {
    private final String id;
    private IncidentState state;
    private IncidentType type;
    private Severity severity;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String sourceIP;
    private String username;
    private final List<SecurityEvent> events = new ArrayList<>();
    private final List<ResponseAction> actions = new ArrayList<>();

    public Incident(String id, String sourceIP, String username) {
        this.id = Objects.requireNonNull(id, "id no pot ser null");
        this.sourceIP = sourceIP;
        this.username = username;
        this.state = IncidentState.CREAT;
        this.type = IncidentType.OTHER;
        this.severity = Severity.BAIXA;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    //Getters i setters

    public String getId() {
        return id;
    }

    public IncidentState getState() {
        return state;
    }

    public IncidentType getType() {
        return type;
    }

    public Severity getSeverity() {
        return severity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getSourceIP() {
        return sourceIP;
    }

    public String getUsername() {
        return username;
    }

    public List<SecurityEvent> getEvents() {
        return Collections.unmodifiableList(events);
    }

    public List<ResponseAction> getActions() {
        return Collections.unmodifiableList(actions);
    }

    public void setState(IncidentState state) {
        this.state = state;
    }

    public void setType(IncidentType type) {
        this.type = type;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public void addEvent(SecurityEvent event) {
        events.add(event);
    }

    public void addAction(ResponseAction action) {
        actions.add(action);
    }

    public void close() {
        this.state = IncidentState.TANCAT;
    }

    public void mitigated() {
        this.state = IncidentState.MITIGAT;
        this.updatedAt = LocalDateTime.now();
    }

    public void escalate() {
        this.state = IncidentState.ESCALAT;
        this.updatedAt = LocalDateTime.now();
    }
}