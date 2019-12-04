
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Visualisierung extends Application implements Beobachter
{

    private CheckBox[] gleisBox;
    private Stage _primaryStage;

    @Override
    public void start(Stage primaryStage)
    {
        _primaryStage = primaryStage;
        Scanner gleiseScan = new Scanner(System.in); //code allows a user to read a number from System.in
        System.out.print("Bitte geben Sie die Anzahl der Gleise ein. \n");
        int gleise = gleiseScan.nextInt();
        Rangierbahnhof rangierbahnhof = new Rangierbahnhof(gleise);

        Simulation simulation = new Simulation(rangierbahnhof, gleise);
        simulation.start();
        gleiseScan.close();

        gleisBox = new CheckBox[gleise];
        primaryStage.setTitle("Rangierbahnhof");

        VBox fenster = new VBox();
        for (int i = 0; i < gleise; i++)
        {
            {
                gleisBox[i] = new CheckBox("Gleis " + (i+1));
            }
            fenster.getChildren().add(gleisBox[i]);
        }
        rangierbahnhof.anmelden(this);

        primaryStage.setScene(new Scene(fenster, 200, 1000));
        primaryStage.show();
    }




    public static void main(String[] args)
    {
        launch(args);
    }




    @Override
    public void aktualisieren(Beobachtbar beobachtbar)
    {
       Zug [] gleise =  beobachtbar.gibZustand();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < gleise.length; i++) {
                    if (gleise[i] != null) {
                        gleisBox[i].setSelected(true);


                    } else {
                        gleisBox[i].setSelected(false);
                    }
                }

            }
        });

    }
}
