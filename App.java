import java.awt.Font;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.Runnable;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application{
    //tout les variables et les instance afin que ce soit accessible n'importe  ou
    Thread ThreadP;
    Timer time;
    VBox box;
    Label label;
    String temp;
    String temp2;
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Pour facilité l'acces au données j'ai créé un objet pour les donné des heures
        object obj = new object();
        temp2 = obj.getMinute();
        temp = obj.getHeure();
        //Interface initiale
         label = new Label(temp +":"+ temp2);
        label.setFont(new javafx.scene.text.Font(50));
        box = new VBox();
        box.getChildren().addAll(label);
        box.setAlignment(Pos.CENTER);
        Scene root = new Scene(box);
    
        primaryStage.setScene(root);
        primaryStage.setResizable(false);
        primaryStage.show();
        //Le  thread principale est fait pour l'interface  et  du coup on a créé un second thread pour récuperer les donner à tout moment
        Runnable myRunnable = new Runnable() {
            // @Override
            public void run(){
                //Pour récupere lesdonnes tout les 1/2  de seconde set intervale  en JS *ù
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    // @Override
                    public void run(){
                        if (temp != Integer.toString(new Date().getHours())|| temp2 != Integer.toString(new Date().getMinutes())) {
                            temp = Integer.toString(new Date().getHours());
                            temp2 = Integer.toString(new Date().getMinutes());
                            //revenir dans le thread principale pour afficher le resultat
                            Platform.runLater(new Runnable(){
                                // @Override
                                public void run(){
                                    label.setText(temp + ":" + temp2);
                                }
                            });
                            //Mbola mila amboarina le  ref chiffre en dessous de 0 de length 1 fotsiny nefa tokony atao 2;
                            //tokony  hiverina @thread principale de avy eo apetaka le nouelle element en le label;
                        }
                    }
                }, 0, 500);
            }            
        };
        //initier le second thread pour le traitement en parallele
      ThreadP = new Thread(myRunnable);
      ThreadP.start();
    }
    public static void main(String args){      
        launch(args);      
        //cool 
    }

public class object{
    String minute = Integer.toString(new Date().getMinutes());
    String heure = Integer.toString(new Date().getHours());

    public String getMinute(){
        return this.minute;
    }

    public String getHeure(){
        return this.heure;
    }
    public void setMinute(String x){
        this.minute = x;
    }

    public void setHeure(String x){
        this.heure = x;
    }
}

    

}
//Pour le lancer utilise la commande javac module-path "route de votre sdk /lib" --add-modules javafx.controls App.java
//Puis il y a ça qui s'affiche Note: App.java uses or overrides a deprecated API.
                            //Note: Recompile with -Xlint:deprecation for details.
//Enfin la commande java module-path "route de votre sdk /lib" --add-modules javafx.controls App
//C'est tout