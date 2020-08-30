import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public ListView<String> listView;
    public ListView<String> listView1;
    public TextField text;
    public TextField log;
    public Button upload;
    public Button download;
    private Socket socket;
    private static DataInputStream is;
    private static DataOutputStream os;
    private String clientPath = "client/ClientStorage";
    private String serverPath = "server/ServerStorage";
    private File dir = new File(clientPath);
    private File dirServer = new File(serverPath);

    public static void stop() {
        try {
            os.writeUTF("quit");
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void downloadFile(ActionEvent actionEvent) {
        String name = listView1.getSelectionModel().getSelectedItem();
        String[] names = name.split(" ");
        for (String finalName : names){
            File dst = new File("client/ClientStorage/" + finalName);
            File src = new File("server/ServerStorage/" + finalName);
            try {
                FileReadWrite.copyFrom(src, dst);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
        listView.getItems().clear();
        for (File file : dir.listFiles())
            listView.getItems().add(file.getName() + "   |  " + file.length() + "bytes");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.setText("File copied to clients path");
    }

   public void uploadFile(ActionEvent actionEvent) {
       String name = listView.getSelectionModel().getSelectedItem();
       String[] names = name.split(" ");
       for (String finalName : names){
           File src = new File("client/ClientStorage/" + finalName);
           File dst = new File("server/ServerStorage/" + finalName);
           try {
           FileReadWrite.copyFrom(src, dst);
       } catch (IOException e) {
           e.printStackTrace();
       }
           break;
   }
       listView1.getItems().clear();
       for (File file : dirServer.listFiles())
           listView1.getItems().add(file.getName() + "   |  " + file.length() + "bytes");
       try {
           Thread.sleep(2000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       log.setText("File copied on server");
    }

    public void initialize(URL location, ResourceBundle resources) {
        text.setOnAction(this::uploadFile);
        for (File file : dir.listFiles())
            listView.getItems().add(file.getName() + "   |  " + file.length() + "bytes");
        try {
            socket = new Socket("localhost", 8189);
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        text.setOnAction(this::downloadFile);
        for (File file : dirServer.listFiles())
            listView1.getItems().add(file.getName() + "   |  " + file.length() + "bytes");
        try {
            socket = new Socket("localhost", 8189);
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
