import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class SOARSystem {

    private final Logger logger;
    private final IncidentManager incidentManager;
    private final Correlator correlator;
    private final Classifier classifier;
    private final PlaybookEngine playbookEngine;
    private final Responder responder;

    public SOARSystem(Logger logger,
                      IncidentManager incidentManager,
                      Correlator correlator,
                      Classifier classifier,
                      PlaybookEngine playbookEngine,
                      Responder responder) {
        this.logger = Objects.requireNonNull(logger);
        this.incidentManager = Objects.requireNonNull(incidentManager);
        this.correlator = Objects.requireNonNull(correlator);
        this.classifier = Objects.requireNonNull(classifier);
        this.playbookEngine = Objects.requireNonNull(playbookEngine);
        this.responder = Objects.requireNonNull(responder);
    }

    public ExecutionResult processEvent(SecurityEvent event) {
        Objects.requireNonNull(event, "event no pot ser null");

        logger.info("Rebut esdeveniment: " + event.getId() + " [" + event.getEventType() + "]");

        if (!event.isValid()) {
            logger.warn("Esdeveniment invàlid. Es descarta: " + event.getId());
            return new ExecutionResult(false, "Event invàlid. Descartat.", List.of("Validation failed"));
        }

        Optional<Incident> related = correlator.findRelatedIncident(event, incidentManager.getAllIncidents());
        Incident incident;

        if (related.isPresent()) {
            incident = related.get();
            logger.info("Event correlacionat amb incident existent: " + incident.getId());
        } else {
            String newId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            incident = new Incident(newId, event.getSourceIP(), event.getUsername());
            incidentManager.addIncident(incident);
            logger.info("Nou incident creat: " + incident.getId());
        }

        incident.setState(IncidentState.VALIDAT);
        incident.addEvent(event);

        if (!incident.isValid()) {
            logger.warn("Incident invàlid després d'afegir event. Es descarta: " + incident.getId());
            incident.discard();
            return new ExecutionResult(false, "Incident invàlid. Descartat.", List.of("Incident validation failed"));
        }

        classifier.classify(incident);

        List<ResponseAction> actions = playbookEngine.selectActions(incident);

        if (actions.isEmpty()) {
            logger.warn("Sense accions automàtiques. Escalat per revisió humana: " + incident.getId());
            incident.escalate();
            return new ExecutionResult(false, "Sense playbook aplicable. Incident escalat.", List.of("No playbook"));
        }

        return responder.executeActions(incident, actions);
    }
}
