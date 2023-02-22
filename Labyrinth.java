/**
 * REK-E1 - Labyrinth
 * Diese Klasse erzeugt Labyrinthe und löst sie anschließend rekursiv
 *
 * @author Jessica Charzynski (s42891@bht-hochschule.de)
 * @version 1.0, 06/2022
 */

public class Labyrinth {
	
	// erstes 10 * 10 Testlabyrinth
	private char[][] testLabyrinth1 = {
			
            {'X', ' ', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', ' ', 'X', 'X', 'X', ' ', ' ', ' ', 'X', 'X'},
            {'X', ' ', ' ', ' ', 'X', 'X', 'X', ' ', 'X', 'X'},
            {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
            {'X', 'X', 'X', ' ', ' ', ' ', 'X', ' ', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', ' ', 'X', ' ', 'X', 'X'},
            {'X', ' ', ' ', ' ', 'X', ' ', ' ', ' ', 'X', 'X'},
            {'X', ' ', 'X', ' ', 'X', 'X', ' ', 'X', 'X', 'X'},
            {'X', 'X', 'X', ' ', ' ', ' ', ' ', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            
	};
	
	// zweites 10 * 10 Testlabyrinth
	private char[][] testLabyrinth2 = {
			
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', ' ', ' ', ' ', ' ', 'X', 'X'},
            {'X', ' ', ' ', 'X', 'X', ' ', 'X', ' ', 'X', 'X'},
            {'X', ' ', 'X', 'X', 'X', ' ', 'X', ' ', 'X', 'X'},
            {'X', ' ', 'X', 'X', ' ', ' ', 'X', 'X', 'X', 'X'},
            {'X', ' ', ' ', ' ', ' ', 'X', 'X', 'X', 'X', 'X'},
            {'X', 'X', ' ', 'X', ' ', ' ', 'X', 'X', 'X', 'X'},
            {'X', 'X', ' ', 'X', 'X', ' ', ' ', 'X', ' ', ' '},
            {'X', 'x', ' ', ' ', 'X', 'X', ' ', ' ', ' ', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            
    };
	
	// Auswahl eines Testlabyrinths
	private char[][] lab = testLabyrinth1;

    // Initialisierung der Variablen
    private int x;
    private int y;
    private char wand = 'X';
    private char weg = '.';
    private char pfad = '*';
    private int labHoehe = lab.length;
    private int labBreite = lab[0].length;
    
    /**
     * die Methode start erhält als Parameter den Startpunkt
     * und gibt in der Konsole aus, ob es einen Ausgang gibt
     * @param x  die Zeile im Labyrinth
     * @param y  die Spalte im Labyrinth
     */

    public void start(int x, int y) {
            if (walk(x, y)) {
            	// S = Start
                lab[x][y] = 'S';
                System.out.println("Ausgang gefunden! Er wurde mit einem A markiert.\n");
            }
            else {
            	// S = Start
                lab[x][y] = 'S';
                System.out.println("Es gibt keinen Ausgang!\n");
            }
    }

    /**
     * istWand überprüft, ob es sich bei einer Stelle, um eine Wand handelt
     * @param x  die Zeile im Labyrinth
     * @param y  die Spalte im Labyrinth
     * @return true bei Wand, false bei Weg
     */

    public boolean istWand(int x, int y) {
        if(lab[x][y] == wand) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * istUeberprueft vermerkt eine Stelle als bereits überprüft
     * verhindert rekursive Endlosschleife
     * @param x  die Zeile im Labyrinth
     * @param y  die Spalte im Labyrinth
     * @return true, wenn bereits überprüft, ansonsten false
     */
    public boolean istUeberprueft(int x, int y) {
        if(lab[x][y] == weg) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * istAusgang überprüft, ob es sich bei einer Stelle, um einen Ausgang handelt
     * falls ja, wird die Stelle mit einem "A" markiert
     * @param x  die Zeile im Labyrinth
     * @param y  die Spalte im Labyrinth
     * @return true bei einem Ausgang, ansonsten false
     */
    public boolean istAusgang(int x, int y) {
        if ((x == 0 || x == labHoehe-1 || y == 0 || y == labBreite-1) && lab[x][y] == ' ') {
            lab[x][y] = 'A';
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * walk läuft durch das Labyrinth und greift auf sich selbst rekursiv zu
     * @param x  die Zeile im Labyrinth
     * @param y  die Spalte im Labyrinth
     * @return true, wenn ein Pfad durch das Labyrinth gefunden wurde, ansonsten false
     */
    public boolean walk(int x, int y) {
        if ((x >= 0 && x < labHoehe) && (y >= 0 && y < labBreite)) {
        	// überprüft, ob die Stelle eine Wand ist oder bereits überprüft wurde
            if(istWand(x, y) || istUeberprueft(x, y)) {
                return false;
            }
            else {
               // überprüft, ob die Stelle kein Ausgang ist
               if(!istAusgang(x, y)) {
                   // markiert die Stelle als überprüft
                    lab[x][y] = weg;
                    // läuft in alle Richtungen (Norden, Osten, Süden, Westen)
                    if (walk(x-1, y) || walk(x, y+1) || walk(x+1, y) || walk(x, y-1)) {
                        // wenn eine Stelle als Pfad erkannt wurde, wird sie entsprechend markiert
                        lab[x][y] = pfad;
                        // es handelt sich um den Lösungsweg
                        return true;
                    }
                    // Wenn wir in eine Sackgasse gelangen, kann der markierte Weg wieder gelöscht werden
                    lab[x][y] = ' ';
                    return false;
               }
               return true;
            }
        }
        else {
            return false;
        }
    }

    /**
     * toString()-Methode wird überschrieben
     * das gelöste Labyrinth wird Zeile für Zeile ausgegeben
     * @return geloestesLab
     */
    @Override
    public String toString() {
        String geloestesLab = "";

        for (int x = 0; x < labHoehe; x++) {
            for (y = 0; y < labBreite; y++) {
                geloestesLab += lab[x][y] + " ";
            }
            geloestesLab += "\n";
        }

        return geloestesLab;
    }
}
