import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Playbook {

    private final String name;
    private final IncidentType supportedType;
    private final Severity minSeverity;
    private final List<ResponseAction> actions;

    public Playbook(String name, IncidentType supportedType, Severity minSeverity, List<ResponseAction> actions) {
        this.name = Objects.requireNonNull(name, "name no pot ser null");
        this.supportedType = Objects.requireNonNull(supportedType, "supportedType no pot ser null");
        this.minSeverity = Objects.requireNonNull(minSeverity, "minSeverity no pot ser null");
        this.actions = (actions == null) ? new ArrayList<>() : new ArrayList<>(actions);
    }

    public String getName() {
        return name;
    }

    public IncidentType getSupportedType() {
        return supportedType;
    }

    public Severity getMinSeverity() {
        return minSeverity;
    }

    public List<ResponseAction> getActions() {
        return Collections.unmodifiableList(actions);
    }

    public boolean isApplicable(Incident incident) {
        if (incident == null) return false;

        if (incident.getType() != supportedType) return false;

        // Severitat: si incident >= minSeverity
        return incident.getSeverity().ordinal() >= minSeverity.ordinal();
    }

    @Override
    public String toString() {
        return "Playbook{" +
                "name='" + name + '\'' +
                ", supportedType=" + supportedType +
                ", minSeverity=" + minSeverity +
                ", actions=" + actions.size() +
                '}';
    }
}
