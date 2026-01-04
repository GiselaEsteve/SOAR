import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ExecutionResult {
    private final boolean success;
    private final String message;
    private final List<String> failedActions;

    public ExecutionResult(boolean success, String message, List<String> failedActions) {
        this.success = success;
        this.message = Objects.requireNonNullElse(message, "");
        this.failedActions = (failedActions == null) ? new ArrayList<>() : new ArrayList<>(failedActions);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getFailedActions() {
        return Collections.unmodifiableList(failedActions);
    }
}
