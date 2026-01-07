public class BlockUserAction extends ResponseAction {

    public BlockUserAction() {
        super("Block User");
    }

    @Override
    public ActionResult execute(Incident incident) {
        if (incident == null || incident.getUsername() == null || incident.getUsername().isBlank()) {
            return ActionResult.fail("No s'ha pogut bloquejar l'usuari: usuari invàlid.");
        }
        // Simulació (no toca AD/SO real)
        return ActionResult.ok("Usuari bloquejat temporalment: " + incident.getUsername());
    }
}
