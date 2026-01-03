import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Classifier {

    private final List<ClassificationRule> rules = new ArrayList<>();
    private final Logger logger;

    public Classifier(Logger logger) {
        this.logger = Objects.requireNonNull(logger);
    }

    // Afegir regles (des de Main o SOARSystem)
    public void addRule(ClassificationRule rule) {
        rules.add(rule);
    }

    public void classify(Incident incident) {
        Objects.requireNonNull(incident, "incident no pot ser null");

        logger.info("Classificant incident " + incident.getId());
        incident.setState(IncidentState.ANALITZANT);

        for (ClassificationRule rule : rules) {
            if (rule.matches(incident)) {
                rule.apply(incident);
                logger.info("Regla aplicada: " + rule);
                incident.setState(IncidentState.CLASSIFICAT);
                return;
            }
        }

        // Cap regla aplicada → OTHER / BAIXA
        logger.warn("Cap regla aplicada. Classificació per defecte.");
        incident.setState(IncidentState.CLASSIFICAT);
    }
}
