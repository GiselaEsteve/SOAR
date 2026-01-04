import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Responder {

    private final Logger logger;

    public Responder(Logger logger) {
        this.logger = Objects.requireNonNull(logger, "logger no pot ser null");
    }

    public ExecutionResult executeActions(Incident incident, List<ResponseAction> actions) {
        Objects.requireNonNull(incident, "incident no pot ser null");
        Objects.requireNonNull(actions, "actions no pot ser null");

        logger.info("Iniciant resposta per incident " + incident.getId() + " (" + incident.getType() + ", " + incident.getSeverity() + ")");
        incident.setState(IncidentState.RESPONENT);

        List<String> failed = new ArrayList<>();
        boolean allOk = true;

        for (ResponseAction action : actions) {
            if (action == null) continue;

            logger.info("Executant acció: " + action.getName());
            ActionResult result = action.execute(incident);

            if (result.isSuccess()) {
                logger.info("OK - " + result.getDetails());
                incident.addAction(action); // queda registrat a l'incident (composició)
            } else {
                allOk = false;
                String failMsg = action.getName() + ": " + result.getDetails();
                failed.add(failMsg);
                logger.warn("FAIL - " + failMsg);
            }
        }

        if (allOk) {
            incident.mitigated(); // diagrama d'estats: després de respondre -> mitigat (si tot ok)
            logger.info("Incident " + incident.getId() + " mitigat.");
            return new ExecutionResult(true, "Totes les accions executades correctament.", failed);
        } else {
            // si falla alguna, ho escalam (demanar revisió humana)
            incident.escalate();
            logger.warn("Incident " + incident.getId() + " escalat per falles en accions.");
            return new ExecutionResult(false, "Algunes accions han fallat. Incident escalat.", failed);
        }
    }
}
