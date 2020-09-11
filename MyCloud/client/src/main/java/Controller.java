import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
    public TextField text;
    public Button upload;
    private Socket socket;
    private static DataInputStream is;
    private static DataOutputStream os;
    private String clientPath = "client/ClientStorage";
    private File dir = new File(clientPath);

    public static void stop() {
        try {
            os.writeUTF("quit");
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
   }}

    public void initialize(URL location, ResourceBundle resources) {
        text.setOnAction(this::uploadFile);
        for (File file : dir.listFiles())
            listView.getItems().add(file.getName() + "   |  " + file.length() + "bytes");
        try {
            socket = new Socket("localhost", 8189);
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());
//            new Task<String>() {
//                @Override
//                protected String call() throws Exception {
//                    return is.readUTF();
//                }
//
//                @Override
//                protected void succeeded() {
//                    try {
//                        listView.getItems().add(get());
//                    } catch (InterruptedException | ExecutionException e) {
//                        e.printStackTrace();
//                    }
//                }
//            };
            new Thread(() -> {
                while (true) {
                    try {
                        String message = is.readUTF();
                        if (message.equals("quit")) {
                            break;
                        }
                        Platform.runLater(() -> listView.getItems().add(message));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
