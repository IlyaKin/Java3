import io.netty.buffer.ByteBuf;
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
import java.util.List;
import java.util.ResourceBundle;

public class Controller extends FileMessage implements Initializable {

    public ListView<String> listView;
    public ListView<String> listView1;
    public TextField text;
    public TextField log;
    public TextField username;
    /*public Button upload;
    public Button download;*/
    public Button login;
    private Socket socket;
    private static ObjectDecoderInputStream is;
    private static ObjectEncoderOutputStream os;
    private String clientPath = "client/ClientStorage/";
   //
    private File dir = new File(clientPath);

    private byte[] buffer;


    public static void stop() {
        try {
            os.writeUTF("quit");
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


   /* public void downloadFile(ActionEvent actionEvent) {
        String name = listView1.getSelectionModel().getSelectedItem();
        String[] names = name.split(" ");
        for (String finalName : names) {
            File dst = new File(clientPath + finalName);

        }
    }

    public void uploadFile(ActionEvent actionEvent) {
        String name = listView.getSelectionModel().getSelectedItem();
        String[] namessss = name.split(" ");
        for (String finalName : namessss) {
            name = finalName;
        }
    }*/

    public void login (ActionEvent actionEvent){
        String name = username.getText();
     //   listView1.getItems().add()
        FilesListRequest request = new FilesListRequest(name);
    }

    public void initialize(URL location, ResourceBundle resources) {

        try {
            socket = new Socket("localhost", 8199);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (File file : dir.listFiles())
            listView.getItems().add(file.getName() + "   |  " + file.length() + "bytes");

           }
}