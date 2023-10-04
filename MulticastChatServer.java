package abdillah41023;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.HashMap;
import java.util.Map;

public class MulticastChatServer {
    public static final String address = "230.0.0.1";
    public static final int port = 12346;
    private static final int max_user = 10;

    private static Map<String, InetAddress> users = new HashMap<>();

    // buat grafiknya

    public static void main(String[] args) {

        try {

            InetAddress group = InetAddress.getByName(address);
            MulticastSocket multicastSocket = new MulticastSocket(port);
            multicastSocket.joinGroup(group);
            System.out.println("MulticastChatServer running.. ");
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                multicastSocket.receive(packet);

                String message = new String(packet.getData(), 0, packet.getLength());
                String[] parts = message.split(" : ", 2);
                if (parts.length == 2) {
                    String username = parts[0].trim();
                    String content = parts[1].trim();
                    if (!users.containsKey(username)) {
                        if (users.size() >= max_user) {
                            System.out.println("user penuh. " + username + " tidak bisa join");
                            continue;
                        }
                        users.put(username, packet.getAddress());
                        System.out.println(username + " Joining");
                    }

                    for (Map.Entry<String, InetAddress> entry : users.entrySet()) {
                        if (!entry.getKey().equals(username)) {
                            InetAddress userAddress = entry.getValue();
                            DatagramPacket sendPacket = new DatagramPacket(
                                    message.getBytes(),
                                    message.length(),
                                    userAddress,
                                    port);
                            multicastSocket.send(sendPacket);
                        }
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}