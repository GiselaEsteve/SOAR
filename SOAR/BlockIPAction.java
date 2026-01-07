public class BlockIPAction extends ResponseAction {

    public BlockIPAction() {
        super("Block IP");
    }

    @Override
    public ActionResult execute(Incident incident) {
        if (incident == null || incident.getSourceIP() == null || incident.getSourceIP().isBlank()) {
            return ActionResult.fail("No s'ha pogut bloquejar la IP: incident o IP invàlida.");
        }
        // Simulació (no toca firewall real)
        return ActionResult.ok("IP bloquejada: " + incident.getSourceIP());
    }
}

