public class Correlator {
    public Incident correlate(SecurityEvent e) {
        Incident incident = new Incident(
                "INC-" + System.currentTimeMillis(),
                e.getSourceIP(),
                e.getUsername()
        );

        incident.addEvent(e);
        incident.setState(IncidentState.VALIDAT);
        return incident;
    }
}
