import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MagicColour implements SerialPortEventListener {

    private static final int SLEEP_TIME = 100;
    private static final String[] PORT_NAMES = {"COM5", "COM4", "COM3", "COM6"};
    private static OutputStream outputStream;
    private BufferedReader inputStream;
    private static int r;
    private static int g;
    private static int b;

    public static void main(String[] args) throws Exception {
        MagicColour main = new MagicColour();
        if (main.initialize()) {
            Thread.sleep(SLEEP_TIME);
        }
        outputStream.close();
        while (true) {
            scanCard();
        }
    }

    private static void scanCard() throws Exception {
        Thread.sleep(500);

        boolean typeFound = false;
        while (!typeFound) {
            Thread.sleep(SLEEP_TIME);
            if (isBlauw()) {
                System.out.println("Basic Land - Island");
                typeFound = true;
            } else if (isZwart()) {
                System.out.println("Basic Land - Swamp");
                typeFound = true;
            } else if (isGeel()) {
                System.out.println("Basic Land - Plains");
                typeFound = true;
            } else if (isGroen()) {
                System.out.println("Basic Land - Forest");
                typeFound = true;
            } else if (isRood()) {
                System.out.println("Basic Land - Mountain");
                typeFound = true;
            }
        }
    }

    private static boolean isBlauw() {
        return r >= 58 && r <= 66 && g >= 88 && g <= 96 && b >= 92 && b <= 100;
    }

    private static boolean isZwart() {
        return r >= 80 && r <= 88 && g >= 86 && g <= 94 && b >= 76 && b <= 84;
    }

    private static boolean isGroen() {
        return r >= 73 && r <= 81 && g >= 94 && g <= 102 && b >= 71 && b <= 79;
    }

    private static boolean isGeel() {
        return r >= 82 && r <= 90 && g >= 90 && g <= 98 && b >= 67 && b <= 75;
    }

    private static boolean isRood() {
        return r >= 100 && r <= 108 && g >= 80 && g <= 88 && b >= 60 && b <= 68;
    }

    public synchronized void serialEvent(SerialPortEvent oEvent) {
        try {
            if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                String inputLine = inputStream.readLine();
                if (inputLine.startsWith("k")) {
                    inputLine = inputLine.substring(1);
                    Matcher matcher = Pattern.compile("R:\\d+,G:\\d+,B:\\d+").matcher(inputLine);
                    Matcher matcherR = Pattern.compile("R:\\d+").matcher(inputLine);
                    Matcher matcherG = Pattern.compile("G:\\d+").matcher(inputLine);
                    Matcher matcherB = Pattern.compile("B:\\d+").matcher(inputLine);

                    while (matcher.find()) {
                        while (matcherR.find()) {
                            r = Integer.parseInt(matcherR.group().substring(2));
                        }
                        while (matcherG.find()) {
                            g = Integer.parseInt(matcherG.group().substring(2));
                        }
                        while (matcherB.find()) {
                            b = Integer.parseInt(matcherB.group().substring(2));
                        }
                    }
                }
            }
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException ex) {
            Logger.getLogger(MagicColour.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.err.println("1 " + e.getMessage());
        }
    }

    private boolean initialize() throws Exception {
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
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
        SerialPort serialPort = (SerialPort) portId.open(this.getClass().getName(), 1000);
        serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        Thread.sleep(SLEEP_TIME);
        inputStream = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
        outputStream = serialPort.getOutputStream();
        serialPort.addEventListener(this);
        serialPort.notifyOnDataAvailable(true);
        return true;
    }
}

