import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

public class Controller  implements Initializable {

    public ListView<String> listView;
    public ListView<String> listView1;
    public TextField text;
    public TextField log;
    public Button upload;
    public Button download;
    private Socket socket;
    private static ObjectDecoderInputStream is;
    private static ObjectEncoderOutputStream os;
    private String clientPath = "client/ClientStorage/";
    private File dir = new File(clientPath);
    private byte[] buffer;


    public void downloadFile(ActionEvent actionEvent) {
        String name = listView1.getSelectionModel().getSelectedItem();
        String[] names = name.split(" ");
        for (String finalName : names) {
            name = finalName;
            File file = new File(serverPath + name);
            try {
                FileInputStream is = new FileInputStream(file);
                buffer = new byte[is.available()];
                is.read(buffer, 0, buffer.length);
                ObjectEncoderOutputStream os = new ObjectEncoderOutputStream(socket.getOutputStream());
                os.writeObject(FileMessageSend.builder()
                        .name(name)
                        .data(buffer)
                        .build());
                os.flush();
                is.close();

                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            listView.getItems().clear();
            for (File file : dir.listFiles())
                listView.getItems().add(file.getName() + "   |  " + file.length() + "bytes");

            log.setText("File '" + names[0] + "' copied to clients path");
        }


    public void uploadFile(ActionEvent actionEvent) {
        String name = listView.getSelectionModel().getSelectedItem();
        String[] names = name.split(" ");
        for (String finalName : names) {
            name = finalName;
            File file = new File(clientPath + name);
            try {
                FileInputStream is = new FileInputStream(file);
                buffer = new byte[is.available()];
                is.read(buffer, 0, buffer.length);
                ObjectEncoderOutputStream os = new ObjectEncoderOutputStream(socket.getOutputStream());
                os.writeObject(FileMessage.builder()
                        .name(name)
                        .data(buffer)
                        .build());
                os.flush();
                is.close();

                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        listView1.getItems().clear();
        for (File file : dirServer.listFiles())
            listView1.getItems().add(file.getName() + "   |  " + file.length() + "bytes");
             log.setText("File '" + names[0] + "' copied on server");
    }

    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new Socket("localhost", 8189);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (File file : dir.listFiles())
            listView.getItems().add(file.getName() + "   |  " + file.length() + "bytes");

    }
}
