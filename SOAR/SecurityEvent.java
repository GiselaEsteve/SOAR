import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SecurityEvent {
    private final String id;
    private final LocalDateTime timestamp;
    private final String sourceIP;
    private final String username;
    private final EventType eventType;
    private final Map<String, String> details;

    public SecurityEvent(String id, LocalDateTime timestamp, String sourceIP, String username, EventType eventType, Map<String, String> details) {
        this.id = Objects.requireNonNull(id, "id no pot ser null");
        this.timestamp = Objects.requireNonNull(timestamp, "timestamp no pot ser null");
        this.sourceIP = sourceIP;     // pot ser null en alguns casos simulats
        this.username = username;     // pot ser null
        this.eventType = Objects.requireNonNull(eventType, "eventType no pot ser null");
        this.details = (details == null) ? new HashMap<>() : new HashMap<>(details);
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getSourceIP() {
        return sourceIP;
    }

    public String getUsername() {
        return username;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Map<String, String> getDetails() {
        return Collections.unmodifiableMap(details);
    }

    public boolean isValid() {
        if (sourceIP == null || sourceIP.isBlank()) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "SecurityEvent{" + "id='" + id + '\'' + ", timestamp=" + timestamp + ", sourceIP='" + sourceIP + '\'' + ", username='" + username + '\'' + ", eventType=" + eventType + ", details=" + details + '}';
    }
}
