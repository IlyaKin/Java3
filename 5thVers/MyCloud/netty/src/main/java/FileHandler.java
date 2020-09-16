import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class FileHandler  extends ChannelHandlerAdapter {

    private Path servdir;

    public FileHandler(Path servdir){
        this.servdir = servdir;
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof FilesListRequest) {
            handlerFilesList(ctx, (FilesListRequest) msg);
        }
        if (msg instanceof DownloadRequest) {
            handlerDownloadResponse();
        }
        if (msg instanceof UploadRequest) {
            handlerUploadResponse();
        }
    }

    private void  handlerFilesList(ChannelHandlerContext ctx, FilesListRequest msg){
        String username = msg.getUserName();
        sendFilesList(ctx, username);

    }

    private void sendFilesList(ChannelHandlerContext ctx, String username) {
        try {
            Path clientsList = Paths.get(servdir + username);
            if (!Files.exists(Paths.get(String.valueOf(clientsList)))) {

                Files.createDirectory(clientsList);
            }
            List<String> listFiles = Files
                    .list(clientsList)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
            FilesListResponse flr = new FilesListResponse(listFiles);
            ctx.writeAndFlush(flr);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void handlerDownloadResponse(){

    }
    private void handlerUploadResponse(){

    }

}

