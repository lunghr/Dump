package Server.managers;

import Common.utils.Message;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.atomic.AtomicReference;

public class ReceiveManager {
    private SocketAddress clientSocketAddress;
    private DatagramChannel channel;
    private ByteBuffer buffer;
    private byte[] bufferSize;

    public ReceiveManager(DatagramChannel channel){
        this.channel = channel;
    }

    public Message getMessage(){
        AtomicReference<Message> message = new AtomicReference<>(null);

        Thread receiveThread = new Thread(() -> {
            ByteBuffer buffer = ByteBuffer.allocate(20000);
            try {
                clientSocketAddress = (SocketAddress) channel.receive(buffer);

                if (clientSocketAddress != null) {
                    buffer.flip();
                    // Получаем байты из буфера
                    byte[] data = new byte[buffer.remaining()];
                    buffer.get(data);
                    // Десериализуем данные от клиента
                    ByteArrayInputStream in = new ByteArrayInputStream(data);
                    ObjectInputStream oin = new ObjectInputStream(in);

                    Object obj = oin.readObject();
                    message.set((Message) obj);
                    oin.close();
                    System.out.println("Message was received: " + message.get().getCommand());

                    buffer.clear();
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Something bad happened and server didn't handle the message");
            }
        });

        receiveThread.start();

        try {
            receiveThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return message.get();
    }

//    public Message getMessage() {
//        Message message;
//        ByteBuffer buffer = ByteBuffer.allocate(20000);
//        try {
//            clientSocketAddress = (SocketAddress) channel.receive(buffer);
//
//            if (clientSocketAddress != null) {
//                buffer.flip();
//                // getting bytes from buffer
//                byte[] data = new byte[buffer.remaining()];
//                buffer.get(data);
//                //getting and deserialize data from client
//                ByteArrayInputStream in = new ByteArrayInputStream(data);
//                ObjectInputStream oin = new ObjectInputStream(in);
//
//                Object obj = oin.readObject();
//                message = (Message) obj;
//                /*System.out.println(message.getCommand());
//                if (message.getSpaceMarine()!= null) {
//                    System.out.println(message.getSpaceMarine().toString());
//                }*/
//                oin.close();
//                System.out.println("Message was received: " + message.getCommand());
//
//                buffer.clear();
//
//                return message;
//            }
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Something bad happened and server didn't get handle message");
//        }
//        return null;
//    }

    public SocketAddress getSocketAddress(){
        return clientSocketAddress;
    }
}
