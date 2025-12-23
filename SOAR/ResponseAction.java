import java.util.Objects;

public abstract class ResponseAction {
    private final String name;

    protected ResponseAction(String name) {
        this.name = Objects.requireNonNull(name, "name no pot ser null");
    }

    public String getName() {
        return name;
    }

    public abstract ActionResult execute(Incident incident);

    @Override
    public String toString() {
        return "ResponseAction{name='" + name + "'}";
    }
}
