import SystemManager.SystemManager;

public class Main {

    public static void main(String[] args)
    {
        SystemManager sm = SystemManager.getInstance();
        sm.createAirport("AZE");
        sm.createAirport("ART");
        sm.createAirport("ZE");
        sm.createAirport("ART");
    }
}
