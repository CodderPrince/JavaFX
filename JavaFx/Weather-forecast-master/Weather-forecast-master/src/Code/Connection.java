package Code;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;

/**
 * THis class is used for establishing an Https connection to the API servers.
 */
public class Connection {

    /**
     *
     * @param httpsUrl Contains the URL to establish a connection.
     * @return Returns the HttpsURLConnection connection.
     * @throws IOException
     */
    public static HttpsURLConnection createConnection(String httpsUrl) throws IOException {
        URL url = new URL(httpsUrl);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }

}