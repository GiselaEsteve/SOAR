import java.util.ArrayList;
import java.util.List;

public class PlaybookEngine {
    
    public Playbook selectPlaybook(Incident incident) {

        List<ResponseAction> actions = new ArrayList<>();

        if (incident.getType() == IncidentType.BRUTE_FORCE) {
            actions.add(new BlockIPAction());
            actions.add(new BlockUserAction());
        }

        actions.add(new NotifyAdminAction());

        return new Playbook(actions);
    }
}