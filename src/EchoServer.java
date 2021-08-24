import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EchoServer extends Thread {

    public static void main(String[] args)
    {
        new EchoServer().start();
    }

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf; // = new byte[256];

    public EchoServer() {
        try {
            socket = new DatagramSocket(30007);
        } catch (Exception e)
        {
            e.toString();
        }
    }

    public void run() {
        running = true;

        while (running) {
            try {
                buf = new byte[256];
                DatagramPacket packet
                        = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                String received
                        = new String(packet.getData(), 0, packet.getLength());

                if (received.equals("end")) {
                    running = false;
                    continue;
                }

                received = trimZeros(received);
                System.out.println(received + " , " + received.length());
                byte[] outbuf =  received.getBytes();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
            } catch (Exception e)
            {
                e.toString();
            }
        }
        socket.close();
    }

    static String trimZeros(String str) {
        int pos = str.indexOf(0);
        return pos == -1 ? str : str.substring(0, pos);
    }
}