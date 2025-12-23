import java.util.Objects;

public class ActionResult {
    private final boolean success;
    private final String details;

    public ActionResult(boolean success, String details) {
        this.success = success;
        this.details = Objects.requireNonNull(details, "");
    }

    public boolean isSuccess() {
        return success;
    }

    public String getDetails() {
        return details;
    }

    public static ActionResult ok(String details) {
        return new ActionResult(true, details);
    }

    public static ActionResult fail(String details) {
        return new ActionResult(false, details);
    }
}
