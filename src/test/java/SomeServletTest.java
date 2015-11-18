import com.peterservice.pstest.SomeServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Andrey.Shilov on 18.11.2015.
 */
@FixMethodOrder(MethodSorters.JVM)
public class SomeServletTest {
    private static Server customersServer = new Server(8088);
    private static String customerPost = "{\n" +
      "\"name\": \"CLIENT\",\n" +
      "\"city\": \"Galle\",\n" +
      "\"subscribers\":[\n" +
      "{\n" +
      "\"msisdn\": \"333\",\n" +
      "\"ratePlan\": \"RP\"\n" +
      "}\n" +
      "]\n" +
      "}";
    private static String customerGet = "{\n" +
      "\"id\": 1,\n" +
      "\"name\": \"CLIENT\",\n" +
      "\"city\": \"Galle\",\n" +
      "\"subscribers\":[\n" +
      "{\n" +
      "\"msisdn\": \"333\",\n" +
      "\"ratePlan\": \"RP\"\n" +
      "}\n" +
      "]\n" +
      "}";

    @BeforeClass
    public static void setup() throws Exception {
        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(SomeServlet.class, "/*");
        customersServer.setHandler(servletHandler);
        customersServer.start();
    }

    @AfterClass
    public static void teardown() throws Exception {
        customersServer.stop();
    }

    @Test
    public void testPost() throws IOException {

        HttpURLConnection http = (HttpURLConnection)new URL("http://localhost:8088/pstest-1.0-SNAPSHOT").openConnection();
        http.setDoOutput(true);
        OutputStreamWriter out = new OutputStreamWriter(http.getOutputStream());
        out.write(customerPost);
        out.close();

        http.connect();

        assertEquals(http.getResponseCode(), 201);

        http.disconnect();
    }

    @Test
    public void testGet() throws IOException {
        HttpURLConnection http = (HttpURLConnection)new URL("http://localhost:8088/pstest-1.0-SNAPSHOT?customerId=1").openConnection();
        http.setDoOutput(true);
        http.connect();

        StringBuilder sb = new StringBuilder();
        if (http.getResponseCode() == 200) {
            String s;
            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
            while ((s = in.readLine()) != null) {
                sb.append(s);
            }
            in.close();
        }

        assertEquals(sb.toString(), customerGet.replaceAll("\\r","").replaceAll("\\n","").replaceAll("\\t","").replaceAll(" ",""));

        http.disconnect();
    }

}
