/**
 * ESA 6, REK-E1 - Labyrinth
 * Testklasse für die Klasse Labyrinth
 *
 * @author Jessica Charzynski (s42891@bht-hochschule.de)
 * @version 1.0, 06/2022
 */

public class Testklasse {

    public static void main(String[] args) {
    	// erstellt ein neues Objekt
        Labyrinth lab = new Labyrinth();
        // ruft die Methode start auf und übergibt den Startpunkt
        lab.start(4,4);
        // gibt das gelöste Labyrinth aus
        System.out.println(lab.toString());
    }

}