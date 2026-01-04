import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class PlaybookEngine {
    private final List<Playbook> playbooks = new ArrayList<>();
    private final Logger logger;

    public PlaybookEngine(Logger logger) {
        this.logger = Objects.requireNonNull(logger);
    }

    public void addPlaybook(Playbook playbook) {
        playbooks.add(Objects.requireNonNull(playbook));
    }

    public List<ResponseAction> selectActions(Incident incident) {
        Objects.requireNonNull(incident, "incident no pot ser null");

        logger.info("Seleccionant playbook per incident " + incident.getId());
        incident.setState(IncidentState.PLANIFICANT_RESPOSTA);

        return playbooks.stream()
                .filter(p -> p.isApplicable(incident))
                // escollim el més “específic”: minSeverity més alta primer
                .sorted(Comparator.comparing(Playbook::getMinSeverity).reversed())
                .findFirst()
                .map(p -> {
                    logger.info("Playbook seleccionat: " + p.getName());
                    return p.getActions();
                })
                .orElseGet(() -> {
                    logger.warn("Cap playbook aplicable. No s'executaran accions automàtiques.");
                    return List.of();
                });
    }
}
