public class CreateTicketAction extends ResponseAction {

    public CreateTicketAction() {
        super("Create Ticket");
    }

    @Override
    public ActionResult execute(Incident incident) {
        if (incident == null) {
            return ActionResult.fail("No s'ha pogut crear el ticket: incident null.");
        }
        // Simulació (Jira/ServiceNow/etc.)
        String ticketId = "TCK-" + incident.getId();
        return ActionResult.ok("Ticket creat: " + ticketId);
    }
}
