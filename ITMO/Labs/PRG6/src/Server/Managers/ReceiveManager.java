package Server.Managers;

import Common.Utils.Message;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;

public class ReceiveManager {
    private SocketAddress clientSocketAddress;
    private DatagramChannel channel;
    private ByteBuffer buffer;
    private byte[] bufferSize;

    public ReceiveManager(DatagramChannel channel){
        this.channel = channel;
    }

    public Message getMessage() {
        Message message ;
        ByteBuffer buffer = ByteBuffer.allocate(20000);
        try {
            clientSocketAddress = (SocketAddress) channel.receive(buffer);

            if (clientSocketAddress != null) {
                buffer.flip();

                byte[] data = new byte[buffer.remaining()];
                buffer.get(data);

                // получаем байтовое представление сериализованного класса
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream oin = new ObjectInputStream(in);



                // десериализуем
                Object obj= oin.readObject();
                message = (Message) obj;
                System.out.println(message.getCommand());
                if (message.getSpaceMarine()!= null) {
                    System.out.println(message.getSpaceMarine().toString());
                }
                oin.close();
                System.out.println("Message was received: " + message.getCommand());

                buffer.clear();
                return message;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Something bad happened and server didn't get handle message");
        }
        return null;
    }

    public SocketAddress getSocketAddress(){
        return clientSocketAddress;
    }




}
