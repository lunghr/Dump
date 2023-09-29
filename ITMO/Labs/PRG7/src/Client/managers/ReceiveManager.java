package Client.managers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import static Client.managers.SendManager.socket;

public class ReceiveManager {
    public ReceiveManager (){}

    public static String getMessage() throws IOException {
        try {
            int MAX_PACKET_SIZE = 65507;
            byte[] byteMessage = new byte[0];

            while (true) {
                byte[] buffer = new byte[MAX_PACKET_SIZE];
                DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
                //System.out.println("message was get");
                socket.receive(inPacket);

                byte[] fragment = Arrays.copyOf(inPacket.getData(), inPacket.getLength());
                byteMessage = Arrays.copyOf(byteMessage, byteMessage.length + fragment.length);
                System.arraycopy(fragment, 0, byteMessage, byteMessage.length - fragment.length, fragment.length);

                if (fragment.length < MAX_PACKET_SIZE) {
                    break;
                }
            }
            ByteBuffer buffer = ByteBuffer.wrap(byteMessage);
            ByteArrayInputStream in = new ByteArrayInputStream(buffer.array());
            ObjectInputStream oIn = new ObjectInputStream(in);

            String response = (String) oIn.readObject();
            oIn.close();
            buffer.clear();

            return response;

        } catch (SocketTimeoutException e) {
            return "The server is not available, try again later";
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

