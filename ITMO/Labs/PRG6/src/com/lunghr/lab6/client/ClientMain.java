package com.lunghr.lab6.client;

import com.lunghr.lab6.client.managers.Client;
import com.lunghr.lab6.client.managers.ClientManager;
import com.lunghr.lab6.common.consoles.BaseConsole;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientMain {

    public static void main(String[] args) {
        BaseConsole console = new BaseConsole();
        Client client = new Client();
        int port = 6789;

        ClientManager clientManager = null;
        try {
            InetAddress inetAddress = InetAddress.getByName("localhost");
            clientManager = new ClientManager(client, console, port, inetAddress);
            clientManager.run();
        } catch (UnknownHostException ex) {
            System.err.println("Host name is incorrect");
        } catch (SocketException ex) {
            System.err.println("Internet error");
        } finally {
            if (clientManager != null) {
                clientManager.stop();
            }
        }

    }
}
