public class NotifyAdminAction extends ResponseAction {

    public NotifyAdminAction() {
        super("Notify Admin");
    }

    @Override
    public ActionResult execute(Incident incident) {
        if (incident == null) {
            return ActionResult.fail("No s'ha pogut notificar: incident null.");
        }
        // Simulació (mail/slack/etc.)
        return ActionResult.ok("Admin notificat (simulat) sobre incident: " + incident.getId());
    }
}
