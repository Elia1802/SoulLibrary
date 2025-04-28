package de.elia.api.discord;

import org.jetbrains.annotations.NotNull;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public interface Discord {


  static void connect(URL webhookURL) throws IOException {
    DiscordIntegration.connect(webhookURL);
  }

  static void sendMessageToDiscord(String message) throws IOException {
    DiscordIntegration.sendMessageToDiscord(message);
  }

  static void sendFileToDiscord(File file) throws IOException {
    DiscordIntegration.sendFileToDiscord(file);
  }

  static void disconnect() throws IOException {
    DiscordIntegration.disconnect();
  }

  @NotNull
  static HttpsURLConnection getConnection() {
    return DiscordIntegration.getConnection();
  }

  @NotNull
  static URL getUrl() {
    return DiscordIntegration.getUrl();
  }

}
