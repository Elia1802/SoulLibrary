package de.elia.api.discord;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class TestDiscord {

  public static void test() throws IOException {
    File file = new File("Hi.txt");
    if (file.createNewFile()) {
      System.out.println("Datei erstellt: " + file.getName());
    } else {
      System.out.println("Datei existiert bereits.");
    }
    URI uri = URI.create("https://discord.com/api/webhooks/1334901457807347743/isWn1NFnMFcKKbuZmBBesSuFU3kk6nlmfCTZjaIRsqVlC0QsC9fqjlI9Mq1BuWQYFOxg");
    Discord.connect(uri.toURL());
    Discord.sendMessageToDiscord("Hii, ich sende dir hier eine datei!");
    try {
      Discord.sendFileToDiscord(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
    Discord.disconnect();
  }

}
