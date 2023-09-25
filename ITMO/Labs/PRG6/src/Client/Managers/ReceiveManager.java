package Client.Managers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class ReceiveManager {
    private DatagramSocket socket;
    public ReceiveManager(DatagramSocket socket){
        this.socket = socket;
    }
    public String getMessage() throws IOException {
        try {

            int maxPacketSize = 65507; // Максимальный размер пакета
            byte[] receivedData = new byte[0]; // Собранные данные

            // Получение пакетов и сборка данных
            while (true) {
                byte[] buffer = new byte[maxPacketSize];
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                //System.out.println("message was get");
                socket.receive(receivePacket);

                byte[] fragment = Arrays.copyOf(receivePacket.getData(), receivePacket.getLength());
                receivedData = Arrays.copyOf(receivedData, receivedData.length + fragment.length);
                System.arraycopy(fragment, 0, receivedData, receivedData.length - fragment.length, fragment.length);

                if (fragment.length < maxPacketSize) {
                    break;
                }
            }

            // преобразовываем полученные данные в ByteBuffer
            ByteBuffer buffer = ByteBuffer.wrap(receivedData);

            ByteArrayInputStream in = new ByteArrayInputStream(buffer.array());
            ObjectInputStream oIn = new ObjectInputStream(in);

            // десериализуем
            String response = (String) oIn.readObject();
            oIn.close();
            buffer.clear();

            return response;

        } catch (SocketTimeoutException e) {
            return "Сервер временно недоступен. Попробуйте позже.";
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}


