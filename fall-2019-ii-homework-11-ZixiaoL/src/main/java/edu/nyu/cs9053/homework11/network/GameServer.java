package edu.nyu.cs9053.homework11.network;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.net.InetSocketAddress;
/**
 * User: blangel
 *
 * A NIO implementation of a NetworkGameProvider.
 *
 * The server takes the following commands:
 * <pre>
 *     foes Difficulty
 * </pre>
 * <pre>
 *     move
 * </pre>
 * where the String "foes Easy" would be a call to {@linkplain NetworkGameProvider#getRandomNumberOfNextFoes(String)}
 * with "Easy"
 * and a call using String "move" would be a call to {@linkplain NetworkGameProvider#getRandomNextMove()}
 */
public class GameServer implements NetworkGameProvider, Runnable {

    public static final String SERVER_HOST = "localhost";

    public static final int SERVER_PORT = 8080;

    private static final Charset UTF8 = Charset.forName("UTF-8");

    public static void main(String[] args) throws IOException {
        GameServer server = new GameServer();
        server.run();
    }

    private final Random random = new Random();
    private final ServerSocketChannel serverSocketChannel;
    private final ByteBuffer serverBuffer = ByteBuffer.allocate(2048);
    private final Map<SocketChannel, ByteBuffer> clientBuffers = new HashMap<>();

    public GameServer() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(SERVER_HOST, SERVER_PORT));

    }

    @Override public String getRandomNumberOfNextFoes(String difficulty) {
        String numberOfNextFoes;
        switch (difficulty) {
            case "1":
                numberOfNextFoes = String.valueOf(random.nextInt(2));
                break;
            case "2":
                numberOfNextFoes = String.valueOf(random.nextInt(3));
                break;
            case "3":
                numberOfNextFoes = String.valueOf(random.nextInt(4));
                break;
            default:
                throw new IllegalArgumentException();
        }
        return numberOfNextFoes;
    }

    @Override public String getRandomNextMove() {
        if (random.nextBoolean()) {
            if (random.nextBoolean()) {
                return "Up";
            } else {
                return "Down";
            }
        } else if (random.nextInt(100) < 95) {
            return "Left";
        } else {
            return "Right";
        }
    }


    @Override public void run() {
        try {
            handle();
        } catch (IOException ioe) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ioe);
        }
    }

    public void handle() throws IOException {

        Selector selector = Selector.open();

        while (!Thread.currentThread().isInterrupted()) {
            int readyChannels = selector.select();
            if (readyChannels == 0) {
                continue;
            }

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                String response = "";
                try {
                    if (key.isAcceptable()) {
                        SocketChannel client = serverSocketChannel.accept();
                        client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        clientBuffers.put(client, ByteBuffer.allocate(2048));
                    } else if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        serverBuffer.flip();
                        if (client.read(serverBuffer) == -1) {
                            clientBuffers.remove(client);
                            key.cancel();
                            continue;
                        }
                        for (Map.Entry<SocketChannel, ByteBuffer> entry : clientBuffers.entrySet()) {
                            SocketChannel otherClient = entry.getKey();
                            if (client != otherClient) {
                                ByteBuffer clientBuffer = entry.getValue();
                                serverBuffer.flip();
                                clientBuffer.put(String.format("[%s] ", client.getRemoteAddress().toString()).getBytes());
                                clientBuffer.put(serverBuffer);
                                clientBuffer.put((byte) '\n');
                            }
                        }
                        serverBuffer.clear();
                        CharsetDecoder decoder = UTF8.newDecoder();
                        CharBuffer charBuffer = decoder.decode(serverBuffer);
                        String command = charBuffer.toString();
                        if (command.startsWith("foes")) {
                            response = getRandomNumberOfNextFoes(command.split(" ")[1]);
                        } else if (command.equals("move")) {
                            response = getRandomNextMove();
                        }
                    } else if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        client.write(ByteBuffer.wrap(response.getBytes()));
                    }
                } finally {
                    keyIterator.remove();
                }
            }
        }
    }

}
