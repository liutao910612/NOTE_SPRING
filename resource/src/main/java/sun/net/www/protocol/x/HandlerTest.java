package sun.net.www.protocol.x;

import com.kevin.base.utils.PrintUtil;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 *X Handler test case
 *
 * @Author:Kevin
 * @Date:Created in 16:40 2021/1/2
 */
public class HandlerTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("x:///META-INF/default.properties");  //类似于 classpath:/META-INF/default.properties
        InputStream inputStream = url.openStream();
        PrintUtil.print(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));
    }
}
