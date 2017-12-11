import exo.training.jcr.AddNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 */

/**
 * @author exo
 *
 */
public class Test{

  /**
   * @param args
   */
  public static void main(String[] args) {
    try {
      URL url = new URL("http://localhost:8080/portal/rest/demo/addNode/1/reded/collaboration/nodeName");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      System.out.println( conn.getResponseCode());
      conn.disconnect();
      } catch (MalformedURLException e) {
      e.printStackTrace();
      } catch (IOException e) {
      e.printStackTrace();
      }
    }
  }
