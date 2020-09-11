import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FileHandler  extends SimpleChannelInboundHandler<AbstractMessage> {

    private static final ConcurrentLinkedQueue<SocketChannel> channels = new ConcurrentLinkedQueue<SocketChannel>();
    private static int cnt = 0;
    private String userName = "user#";

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, AbstractMessage abstractMessage) throws Exception {

        if (abstractMessage instanceof FileMessage) {
            FileMessage message = (FileMessage) abstractMessage;
            if (!Files.exists(Paths.get("netty/NettyServerStorage/" + message.getName()))) {
                Files.createFile(Paths.get("netty/NettyServerStorage/" + message.getName()));
                Files.write(
                        Paths.get("netty/NettyServerStorage/" + message.getName()),
                        message.getData(),
                        StandardOpenOption.APPEND);
                System.out.printf("Received file from %s: %s\n", userName, message.getName());
            } else System.out.println("File already exists");
        }
            if (abstractMessage instanceof FileMessageSend)  {
                FileMessageSend message = (FileMessageSend) abstractMessage;
                if (!Files.exists(Paths.get("client/ClientStorage/" + message.getName()))) {
                    Files.createFile(Paths.get("client/ClientStorage/" + message.getName()));
                    Files.write(
                            Paths.get("client/ClientStorage/" + message.getName()),
                            message.getData(),
                            StandardOpenOption.APPEND);
                    System.out.printf("Send file to %s: %s\n", userName, message.getName());
                } else System.out.println("File already exists");
            }
            }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        cnt++;
        userName += cnt;
        System.out.printf("Client %s connected\n", userName);
        channels.add((SocketChannel) ctx.channel());

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.printf("Client %s disconnected\n", userName);
        channels.remove((SocketChannel) ctx.channel());
    }
}

