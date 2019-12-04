public class Simulation extends Thread {

    private Rangierbahnhof rangierbahnhof;
    private int _anzahlGleise;

    /**
     * Konstruktor für die Simulation
     */
    public Simulation (Rangierbahnhof rangierbahnhof, int anzahlGleise)
    {
        this.rangierbahnhof = rangierbahnhof;
        _anzahlGleise = anzahlGleise;
    }

    @Override
    public void run()
    {
        int durchlaufNr = 1;
        while(true)
        {
            System.out.println("Durchlauf Nr." + durchlaufNr++);
            Lokfuehrer lokfuehrer = new Lokfuehrer(rangierbahnhof, _anzahlGleise);
            lokfuehrer.start();
            try
            {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();            //?
            }
        }
    }
}
