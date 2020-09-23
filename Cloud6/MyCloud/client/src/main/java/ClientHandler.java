import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import javafx.scene.control.ListView;

import java.awt.*;
import java.io.*;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ClientHandler extends ChannelHandlerAdapter {

    public static String filename;
    public static List<String> filesList;

    public ClientHandler(Path dir, String userName) {
    }

    public ClientHandler(){

    }


       @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FilesListResponse) {
            handlerFilesList((FilesListResponse) msg);
            System.out.println(ctx.channel().isActive());
        }
        if (msg instanceof DownloadResponse) {
            ByteBuf buffer = (ByteBuf) msg;
            byte[] bytes = new byte[buffer.readableBytes()];
            buffer.readBytes(bytes);
           // handlerDownloadFile((DownloadResponse) msg);
        }
    }

    /*private File handlerDownloadFile (DownloadResponse msg) {

    }*/

    private List<String> handlerFilesList(FilesListResponse msg) {
        filesList = msg.getFilesList();
        return filesList;
    }
}

