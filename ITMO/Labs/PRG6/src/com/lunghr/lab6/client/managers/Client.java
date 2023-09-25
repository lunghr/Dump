package com.lunghr.lab6.client.managers;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Client {
    private static final int TIMEOUT = 3000;

    private DatagramSocket socket;

    public Client() {}

    public void start() throws SocketException {
        socket = new DatagramSocket();
        socket.setSoTimeout(TIMEOUT); // Тайм-аут в 3 секунды
    }

    public void stop() {
        socket.close();
    }

    public DatagramSocket getSocket() {
        return socket;
    }
}
