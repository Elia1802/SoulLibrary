package de.elia.api.discord;

import de.elia.api.Main;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.StatusLine;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

public class DiscordIntegration {

  private static URL url;
  private static HttpsURLConnection connection;

  public static void connect(URL webhookURL) throws IOException {
    url = webhookURL;
    connection = (HttpsURLConnection) url.openConnection();
    connection.addRequestProperty("Content-Type", "application/json");
    connection.addRequestProperty("User-Agent", "SoulLibrary-DiscordIntegration");
    connection.setDoOutput(true);
    connection.setRequestMethod("POST");
  }

  /**
   * Send a message to discord
   * @param message Requires the message for discord
   * @throws IOException
   */
  public static void sendMessageToDiscord(String message) throws IOException {
    if (message == null) throw new NullPointerException("message is null");
    if (connection == null) throw new NullPointerException("connection is null");
    JSONObject json = new JSONObject();
    json.put("content", message);
    OutputStream output = connection.getOutputStream();
    output.write(json.toJSONString().getBytes());
    output.flush();
    output.close();
  }

  /**
   * This methode sends a file to Discord
   * @param file Requires the file
   * @throws IOException Create an error if the
   */
  public static void sendFileToDiscord(File file) throws IOException {
    if (!file.exists()) throw new NullPointerException("file is null");
    CloseableHttpClient client = HttpClients.createDefault();
    HttpPost post = new HttpPost(url.toString());
    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
    builder.addBinaryBody("file", file, ContentType.APPLICATION_OCTET_STREAM, file.getName());
    builder.addTextBody("payload_json", "{\"content\": \"Test!\"}", ContentType.APPLICATION_JSON);
    HttpEntity multipart = builder.build();
    post.setEntity(multipart);
    CloseableHttpResponse response = client.execute(post, response1 -> {
      Main.getSoulLibrary().logger().logInfo("----------------------------------------");
      Main.getSoulLibrary().logger().logInfo(post + "->" + new StatusLine(response1));
      EntityUtils.consume(response1.getEntity());
      return null;
    });
    // print result
    client.close();
  }

  public static void disconnect() throws IOException {
    if (connection == null) throw new NullPointerException("connection is null");
    connection.getInputStream().close();
    connection.disconnect();
  }

  @NotNull
  public static HttpsURLConnection getConnection() {
    return connection;
  }

  @NotNull
  public static URL getUrl() {
    return url;
  }
}
