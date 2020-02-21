

/**
 * Main klasse
 *
 * @author Liza Verhaert, edited by Ketura Seedorf, Duygu Tas
 */
public class MagicColour {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        /* Stuur een commando naar de arduino
        Mogelijke commando's:
            kleurEND        haal de waarden van de kleursensor op
            stopEND         zet de RFID-, kleur- en gewichtinformatiestroom stop
         */

        // De uiteindelijke functie die het hele programma gaat uitvoeren!
//        while (true) {
            if (SerialConnector.afvalSysteemGereed()) {
                SerialConnector.scanCard();
            }
//        }

    }
}

