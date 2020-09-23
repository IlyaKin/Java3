import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTest {
    public static void main(String[] args) throws IOException {
        try(Socket socket = new Socket("localhost", 8080)) {
            ObjectEncoderOutputStream os = new ObjectEncoderOutputStream(socket.getOutputStream());
            ObjectDecoderInputStream is = new ObjectDecoderInputStream(socket.getInputStream());
            os.writeObject(FileMessage.builder()
                    .name("file.txt")
                    .data("12345".getBytes()) // 1GB
                    .build());
            os.flush();
            os.close();
        }
    }



    public static void Test () {
        try (Socket socket = new Socket("localhost", 8080)) {
            ObjectDecoderInputStream is = new ObjectDecoderInputStream(socket.getInputStream());
            byte[] buffer = new byte[is.available()];
            is.read(buffer, 0, buffer.length);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
