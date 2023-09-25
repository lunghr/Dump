package Client.Managers;

import Common.Consoles.BaseConsole;
import Common.Consoles.Console;
import Common.Utils.Message;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class SendManager {
    private DatagramSocket socket;
    private byte[] data;
    private InetAddress address;
    private int port;
    private Console console;
    private int[] timeOuts = {10, 30, 50};

    public SendManager(DatagramSocket socket, InetAddress address, int port, Console console){
        this.port = port;
        this.socket = socket;
        this.address = address;
        this.console = console;
    }

    public void sendMessage(Message message) throws IOException {
            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ObjectOutputStream oOut = new ObjectOutputStream(out);
                Object obj = message;
                oOut.writeObject(obj);
                oOut.flush();

                byte[] data = out.toByteArray();

                DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
                /*System.out.println(message.getCommand());
                if(message.getSpaceMarine()!= null){
                    System.out.println(message.getSpaceMarine().toString());
                }*/

                socket.send(packet);
               // console.writeStr("message was sent");

                oOut.close();
            } catch (IOException e) {
                console.writeStr("Something bad happened and message wasn't sent");
            }
    }
}
