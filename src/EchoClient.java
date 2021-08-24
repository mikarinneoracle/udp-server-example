import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EchoClient {
    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buf;

    public static void main(String[] args)
    {
        EchoClient client = new EchoClient(args.length > 1 ? args[1] : "localhost");
        String echo = client.sendEcho(args[0], args.length > 2 ? args[2] : "4445");
        System.out.println(echo + " , " + echo.length());
    }

    public EchoClient(String port) {
        try
        {
            socket = new DatagramSocket();
            address = InetAddress.getByName(port);
        } catch (Exception e)
        {
            e.toString();
        }
    }

    public String sendEcho(String msg, String port) {
        try
        {
            buf = msg.getBytes();
            DatagramPacket packet
                    = new DatagramPacket(buf, buf.length, address, (int) Integer.valueOf(port));
            socket.send(packet);
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            String received = new String(
                    packet.getData(), 0, packet.getLength());
            return received;
        } catch (Exception e)
        {
            e.toString();
            return "did not work ..";
        }
    }

    public void close() {
        socket.close();
    }
}