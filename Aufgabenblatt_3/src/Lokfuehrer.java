import java.util.Random;

public class Lokfuehrer extends Thread {

    private int aktuellGleis; //Zufallsnummer für ein Gleis
    private final int _anzahlGleise;
    Rangierbahnhof rangierbahnhof;

    /**
     * Konstruktor der Klasse Lokführer
     */
    public Lokfuehrer(Rangierbahnhof rangierbahnhof, int anzahlGleise)
    {
        _anzahlGleise = anzahlGleise;
        this.rangierbahnhof = rangierbahnhof;
    }

    @Override
    public void run()
    {
        aufgabeGleis();
        if(aufgabe())
        {
            rangierbahnhof.zugEinfahren(neuerZug(), aktuellGleis);
        }
        else
        {
            rangierbahnhof.zugAusfahren(aktuellGleis);
        }
    }

    /**
     * Ermittelt (zufällig) aus den Gleisen ein potentielles Aufgabengleis
     * für den Lokfüherer, initialisiert int aktuellGleis
     * Wertebereich 0 bis _anzahlGleise
     */
    private void aufgabeGleis()
    {
        aktuellGleis = new Random().nextInt(_anzahlGleise);
    }

    /**
     * Ermittelt eine Zufällige Aufgabe; true oder false
     * @return true = zug einfahren
     * @return false = zug ausfahren
     */
    private boolean aufgabe()
    {
        return new Random().nextBoolean();
    }

    /**
     * Ertellt einen neuen Zug, der auf ein Gleis gefahren werden kann
     * @return Zug
     */
    private Zug neuerZug()
    {
        return new Zug();
    }

}
