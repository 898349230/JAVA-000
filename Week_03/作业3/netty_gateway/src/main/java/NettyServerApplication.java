import com.ab.gateway.inbound.HttpInboundServer;

/**
 * @classname: NettyServerApplication
 * @description:
 * @author: sunxinbo
 * @time: 2020/11/3、22:08
 */
public class NettyServerApplication {

    public static void main(String[] args) {
        String proxyServer = System.getProperty("proxyServer","http://localhost:8788");
        String portPro = System.getProperty("port","9999");

        int port = Integer.parseInt(portPro);
        HttpInboundServer server = new HttpInboundServer(port, proxyServer);
        server.run();
    }
}
