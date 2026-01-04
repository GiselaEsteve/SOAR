import java.util.List;

public class Playbook {
    
    private final List<ResponseAction> actions;

    public Playbook(List<ResponseAction> actions) {
        this.actions = actions;
    }

    public List<ResponseAction> getActions() {
        return actions;
    }
}
