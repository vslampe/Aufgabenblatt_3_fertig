public interface Beobachtbar {
    public void anmelden(Beobachter b);
    public void abmelden(Beobachter b);
    public Zug [] gibZustand();
}
