public class Simulator {

    public static void main(String[] args)
    {
        final int gleise = 50;
        Rangierbahnhof rangierbahnhof = new Rangierbahnhof(gleise);
        while (true)
        {
            Lokfuehrer lokfuehrer = new Lokfuehrer(rangierbahnhof, gleise);
            lokfuehrer.start();
        }
    }
}
