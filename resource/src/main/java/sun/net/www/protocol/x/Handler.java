package sun.net.www.protocol.x;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * X 协议{@link URLStreamHandler}demo
 *
 * @Author:Kevin
 * @Date:Created in 16:31 2021/1/2
 */
public class Handler extends URLStreamHandler {
    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new XURLConnection(u);
    }
}
