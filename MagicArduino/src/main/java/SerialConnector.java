

import gnu.io.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mechjesus, edited by Liza Verhaert, Ketura Seedorf, Duygu Tas
 */
public class SerialConnector implements SerialPortEventListener {

    // The port we're normally going to use.
    private static SerialPort serialPort;
    // Possible port names
    private static final String PORT_NAMES[] = {
            "COM5", // Windows
            "COM4", // Windows
            "COM3", // Windows
            "COM6", // Windows
    };

    private static int rood;
    private static int groen;
    private static int blauw;
    private static int startRood;
    private static int startGroen;
    private static int startBlauw;
    private static boolean isDetermined = true;

    // Standard baud rate
    protected static final int BAUD_RATE = 9600;
    // A BufferedReader which will be fed by an InputStreamReader converting the
    // bytes into characters making the displayed results codepage independent
    protected BufferedReader inputStream;
    // The output stream to the port
    protected static OutputStream outputStream;
    // Wait-time
    protected static final int TIME_OUT = 1000;

    public boolean initialize(int DATA_RATE) {

        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        // First, find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null) {
            System.out.println("Could not find COM port.");
            return false;
        }

        try {
            // Open serial port
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);

            // Set port parameters
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            Thread.sleep(TIME_OUT);

            // Open the streams
            inputStream = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            outputStream = serialPort.getOutputStream();

            // Add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (PortInUseException e) {
            //          System.err.println(e.toString());
        } catch (IOException e) {
//            System.err.println(e.toString());
        } catch (UnsupportedCommOperationException e) {
            System.err.println("5" + e.toString());
        } catch (Exception e) {
            System.err.println("3" + e.toString());
        }
        return true;
    }

    /**
     * This should be called when you stop using the port. This will prevent
     * port locking on platforms like Linux.
     */
    public static synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    /**
     * Handle an event on the serial port. Read the data and print it.
     */
    public synchronized void serialEvent(SerialPortEvent oEvent) {
        try {
            if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                try {
                    String inputLine = inputStream.readLine();
                    if (inputLine.startsWith("k")) {
                        processKleur(inputLine.substring(1));
//                        System.out.println(inputLine);
                    } else {
                        System.out.println("    ARDUINO: " + inputLine);
                    }
                } catch (Exception e) {
                    //                  System.err.println(e.toString());
                }
            }
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(SerialConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendOutput(String outputMessage) {
        SerialConnector main = new SerialConnector();
        if (main.initialize(BAUD_RATE)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SerialConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                outputStream.write(outputMessage.getBytes());
            } catch (IOException e) {
                System.err.println("1" + e.getMessage());
            }
            System.out.print("Sending \"" + outputMessage + "\"...     ");

            try {
                outputStream.close();
                System.out.println("Message sent.");
            } catch (IOException e) {
                System.out.println();
                System.err.println("2" + e.getMessage());
            }

        }
    }


    private void processKleur(String inputLine) {
        String REGEX = "R:\\d+,G:\\d+,B:\\d+";
        String REGEXR = "R:\\d+";
        String REGEXG = "G:\\d+";
        String REGEXB = "B:\\d+";
        String REGEXSTART = "startwaarde R:\\d+,G:\\d+,B:\\d+";
        Pattern pattern = Pattern.compile(REGEX);
        Pattern patternR = Pattern.compile(REGEXR);
        Pattern patternG = Pattern.compile(REGEXG);
        Pattern patternB = Pattern.compile(REGEXB);
        Pattern patternStart = Pattern.compile(REGEXSTART);
        Matcher matcher = pattern.matcher(inputLine); // laat de reguliere expressie los op de inputLine
        Matcher matcherR = patternR.matcher(inputLine);
        Matcher matcherG = patternG.matcher(inputLine);
        Matcher matcherB = patternB.matcher(inputLine);
        Matcher matcherstart = patternStart.matcher(inputLine);
        while (matcherstart.find()) {

            while (matcherR.find()) { // zolang er matches gevonden worden...
                String startR = matcherR.group(); // wil ik deze opslaan in de variabele startR    
                startRood = Integer.parseInt(startR.substring(2)); // haal ik 2 waarden weg

            }
            while (matcherG.find()) {
                String startG = matcherG.group();
                startGroen = Integer.parseInt(startG.substring(2));

            }
            while (matcherB.find()) {
                String startB = matcherB.group();
                startBlauw = Integer.parseInt(startB.substring(2));

            }

        }
        while (matcher.find()) {
            while (matcherR.find()) {
                String roodString = matcherR.group();
                rood = Integer.parseInt(roodString.substring(2));
            }
            while (matcherG.find()) {
                String groenString = matcherG.group();
                groen = Integer.parseInt(groenString.substring(2));
            }
            while (matcherB.find()) {
                String blauwString = matcherB.group();
                blauw = Integer.parseInt(blauwString.substring(2));
            }
        }
        System.out.println(inputLine);
    }
//    geen 91-93, 107-111, 102-105      92, 109, 103
//    geen 95-106, 110-114, 103-110     100, 112, 106
/*

 */
//    blauw 52-62, 88-91, 101-113     -40 -30, -21 -18, -2 +10
//    blauw 55-66, 89-93, 95-109      60, 91, 102     -40, -20, -4
    public static boolean isBlauw() {
        if (rood >= startRood - 42 && rood <= startRood - 30
                && groen >= startGroen - 22 && groen <= startGroen - 18
                && blauw >= startBlauw - 6 && blauw <= startBlauw + 10) {
            return true;
        }
        return false;
    }

//    zwart 78-84, 90-91, 82-84       -14 -8, -19 -18, -21 -19
//    zwart 78-86, 87-92, 76-85       82, 90, 80      -18, -22, -26
    public static boolean isZwart() {
    if (rood >= startRood - 20 && rood <= startRood - 8
            && groen >= startGroen - 24 && groen <= startGroen - 18
            && blauw >= startBlauw - 28 && blauw <= startBlauw - 19) {
        return true;
    }
    return false;
}
//    groen 69-81, 100-103, 76-85     -23 -11, -9 -6, -27 -18
//    groen 71-78, 94-99, 70-78       75, 96, 74      -25, -16, -32
public static boolean isGroen() {
    if (rood >= startRood - 27 && rood <= startRood - 11
            && groen >= startGroen - 18 && groen <= startGroen - 6
            && blauw >= startBlauw - 34 && blauw <= startBlauw - 18) {
        return true;
    }
    return false;
}
//    geel 77-80, 92-94, 70-75        -15 -12, -17 -15, -33 -28
//    geel 76-84, 92-94, 67-76        80, 93, 72      -20, -19, -34
public static boolean isGeel() {
    if (rood >= startRood - 22 && rood <= startRood - 12
            && groen >= startGroen - 21 && groen <= startGroen - 15
            && blauw >= startBlauw - 36 && blauw <= startBlauw - 28) {
        return true;
    }
    return false;
}
//    rood 112-118, 75-80, 64-68      +20 +26, -34 -29, -39 -35
//    rood 101-115, 76-83, 62-67      108, 80, 64     +8, -32, -42
public static boolean isRood() {
    if (rood >= startRood + 6 && rood <= startRood + 26
            && groen >= startGroen - 34 && groen <= startGroen - 29
            && blauw >= startBlauw - 44 && blauw <= startBlauw - 35) {
        return true;
    }
    return false;
}

//    public static boolean isWit() { // bepaalt wanneer een glas afvalitem wit is
//        if (rood <= startRood - 8 && rood >= startRood - 40
//                && groen >= startGroen - 5 && groen <= startGroen + 25
//                && blauw >= startBlauw + 5 && blauw <= startBlauw + 30) {
//            return true;
//        }
//        return false;
//    }
//
//    public static boolean isKleur() { // bepaalt wanneer een glas afvalitem kleur is
//        if ((rood <= startRood - 28 || rood >= startRood + 40
//                && groen <= startGroen - 25 || groen >= startGroen + 5
//                && blauw <= startBlauw || blauw >= startBlauw + 35)) {
//            return true;
//
//        }
//        return false;
//    }

    public static void scanCard() {
        isDetermined = false; // bezig met deze functie (nodig ivm multithreading)

        sendOutput("kleurEND");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SerialConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean typeFound = false;
        while (!typeFound) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SerialConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (isBlauw()) {
                System.out.println("Blauw");
                typeFound = true;
                sendOutput("stopEND");
            } else if (isZwart()) {
                System.out.println("Zwart");
                typeFound = true;
                sendOutput("stopEND");
            } else if (isGeel()) {
                System.out.println("Geel");
                typeFound = true;
                sendOutput("stopEND");
            } else if (isGroen()) {
                System.out.println("Groen");
                typeFound = true;
                sendOutput("stopEND");
            } else if (isRood()) {
                System.out.println("Rood");
                typeFound = true;
                sendOutput("stopEND");
            }
            System.out.println("Kleur: R:" + rood + " G:" + groen + " B:" + blauw); // en wil ik deze laten zien in de output
            System.out.println("Startkleur: R:" + startRood + " G:" + startGroen + " B:" + startBlauw); // en wil ik deze laten zien in de output

        }

        isDetermined = true; // klaar met deze functie (nodig ivm multithreading)

    }

    public static boolean afvalSysteemGereed() {
        return isDetermined;

    }
}
