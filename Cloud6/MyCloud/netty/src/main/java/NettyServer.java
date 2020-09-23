import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


import java.nio.file.Path;
import java.nio.file.Paths;



public class NettyServer implements AutoCloseable {
    private final EventLoopGroup auth = new NioEventLoopGroup(1);
    private final EventLoopGroup worker = new NioEventLoopGroup();
    private final ServerBootstrap servBootstrap = SettingsServer();
   // private Path dirServ;

    public NettyServer(){

    }

    public ServerBootstrap SettingsServer() {
        return new ServerBootstrap()
                .group(auth, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new ObjectEncoder(),
                                        new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                                        new FileHandler(Paths.get("netty/NettyServerStorage"))
                                );
                    }
                });
    }

    public void start(){
        try {
            ChannelFuture future = servBootstrap.bind(8198).sync();
            System.out.println("Server started");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (NettyServer server = new NettyServer()) {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        worker.shutdownGracefully();
        auth.shutdownGracefully();
    }
}