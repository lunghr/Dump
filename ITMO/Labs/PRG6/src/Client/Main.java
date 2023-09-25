package Client;

import Client.Managers.Client;
import Client.Managers.ClientManager;
import Common.Consoles.BaseConsole;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws UnknownHostException, SocketException {
        BaseConsole console = new BaseConsole();
        Client client = new Client();
        int port = 6789;
        InetAddress inetAddress = InetAddress.getByName("localhost");


        ClientManager clientManager = new ClientManager(client, console, port, inetAddress);
        clientManager.run();
    }
}
