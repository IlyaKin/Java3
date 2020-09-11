import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileHandlerClient extends SimpleChannelInboundHandler<AbstractMessage> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, AbstractMessage abstractMessage) throws Exception {
//        ByteBuf buf = ctx.alloc().directBuffer(100500);
        if (abstractMessage instanceof FileMessage) {
            FileMessage message = (FileMessage) abstractMessage;
            if (!Files.exists(Paths.get("client/ClientStorage/" + message.getName()))) {
                Files.createFile(Paths.get("client/ClientStorage/" + message.getName()));
                Files.write(
                        Paths.get("client/ClientStorage/" + message.getName()),
                        message.getData(),
                        StandardOpenOption.APPEND);
               // System.out.printf("Received file from %s: %s\n", userName, message.getName());
            } else {
                System.out.println("file exists");
            }
        }
    }
}