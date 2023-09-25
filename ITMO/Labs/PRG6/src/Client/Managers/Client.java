package Client.Managers;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Client {
    private DatagramSocket socket;

    public Client(){}

    public void start() throws SocketException {
        socket = new DatagramSocket();
        socket.setSoTimeout(3000); // Тайм-аут в 3 секунды
    }

    public void stop(){
        socket.close();
    }

    public DatagramSocket getSocket(){
        return socket;
    }

}
