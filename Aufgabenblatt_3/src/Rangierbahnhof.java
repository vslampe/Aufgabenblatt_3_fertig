import java.util.ArrayList;
import java.util.List;

public class Rangierbahnhof extends Thread implements Beobachtbar {

    private final int _anzahlGleise;
    private Zug[] gleise;
    private List<Beobachter> beobachter = new ArrayList<Beobachter>();


    /**
     * Konstruktor der Klasse Rangierbahnhof
     */
    public Rangierbahnhof(int anzahlGleise) {
        _anzahlGleise = anzahlGleise;       //Anzahl der Gleise wird festgelegt
        gleise = new Zug[_anzahlGleise];    //Anlegen eines Zug Arrays mit Anzahl der Gleise
    }

    public synchronized void zugEinfahren(Zug zug, int gleisnummer) {
        while (gleise[gleisnummer] != null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();    //?
            }
        }
        gleise[gleisnummer] = zug;
        gibZustand();
        System.out.println("Das Gleis Nr." + (gleisnummer + 1) + " ist belegt; ein Zug ist eingefahren");
        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();        //?
        }
        notifyAll();
    }

    public synchronized void zugAusfahren(int gleisnummer) {
        while (gleise[gleisnummer] == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();    //?
            }
        }
        gleise[gleisnummer] = null;
        gibZustand();
        System.out.println("Das Gleis" + (gleisnummer + 1) + " ist wieder frei; ein Zug ist ausgefahren");
        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();        //?
        }
        notifyAll(); // wakes up all threads that are waiting on this object's monitor
        benachrichtigen();
    }
 public void benachrichtigen(){
     for(int i = 0; i < beobachter.size(); i++)
     {
         beobachter.get(i).aktualisieren(this);
     }
 }
    @Override
    public void anmelden(Beobachter b) {
        beobachter.add(b);
    }

    @Override
    public void abmelden(Beobachter b) {
        beobachter.remove(b);
    }

    @Override
    public Zug [] gibZustand() {
        return gleise;
    }
}
