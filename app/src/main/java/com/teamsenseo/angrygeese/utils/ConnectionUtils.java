package com.teamsenseo.angrygeese.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Useful things for connecting to websites
 *
 * @author Robert
 */
public final class ConnectionUtils {

    /**
     * Creates a new connection
     */
    public static final HttpURLConnection openConnection(final URL url, final String agent) throws IOException {
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", agent);
        connection.setRequestProperty("Charset", "UTF-8");

        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        connection.setInstanceFollowRedirects(true);
        connection.setUseCaches(true);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        return connection;
    }

    /**
     * Creates a new connection
     */
    public static final HttpURLConnection openConnection(final URL url) throws IOException {
        return openConnection(url, "Angry Geese application (Android)");
    }
}
