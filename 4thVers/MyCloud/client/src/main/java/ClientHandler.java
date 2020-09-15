import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.nio.file.Path;

public class ClientHandler extends ChannelHandlerAdapter {

    private Path clientdir;

    public ClientHandler (Path clientdir){
        this.clientdir = clientdir;
    }

    @Override
    public void write (ChannelHandlerContext ctx, Object msg){

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof FilesListResponse) {
            handlerviewListFiles(ctx, (FilesListResponse) msg);
        }
    }

    private void handlerviewListFiles(ChannelHandlerContext ctx, FilesListResponse msg){





           /* if (msg instanceof DownloadRequest) {
                handlerDownloadResponse();
            }
            if (msg instanceof UploadRequest) {
                handlerUploadResponse();
            }*/
        }
    }

