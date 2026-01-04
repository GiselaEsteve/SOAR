public class SOARSystem {
    private final IncidentManager incidentManager;
    private final PlaybookEngine playbookEngine;
    private final Responder responder;

    public SOARSystem(IncidentManager incidentManager, PlaybookEngine playbookEngine, Responder responder, Logger logger) {
        this.incidentManager = incidentManager;
        this.playbookEngine = playbookEngine;
        this.responder = responder;
    }

    public void processEvent(SecurityEvent event) {

        System.out.println("Nou event rebut");
        
        // Crea, classifica l'incident i selecciona les accions
        Incident incident = incidentManager.processEvent(event);
        System.out.println("Incident creat: " + incident.getId());

        Playbook playbook = playbookEngine.selectPlaybook(incident);
        System.out.println("Accions seleccionades");

        // Executem les accions
        responder.executeActions(incident, playbook.getActions());
        System.out.println("Procés finalitzat per incident " + incident.getId());
    }
}
