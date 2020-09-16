import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.logging.SocketHandler;

public class ClientHandler extends ChannelHandlerAdapter {

    //private Controller controller;
    private Path clientdir;

    public ClientHandler (Path clientdir){
        this.clientdir = clientdir;
    }

    public void writeToChannel (Object msg){
        if (msg instanceof FilesListRequest) {
            String name = ((FilesListRequest) msg).getUserName();
        }
    }



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof FilesListResponse) {
            handlerviewListFiles(ctx, (FilesListResponse) msg);
        }
        }


    private void handlerviewListFiles(ChannelHandlerContext ctx, FilesListResponse msg){
       // for (String file : msg.getFilesList(){

        }
    }
