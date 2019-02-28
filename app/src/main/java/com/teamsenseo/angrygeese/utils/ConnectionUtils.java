package com.teamsenseo.angrygeese.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

public final class ConnectionUtils {

    public static final HttpURLConnection openConnection(final URL url, final Proxy proxy, final String agent) throws IOException {
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
        return connection;
    }

    public static final HttpURLConnection openConnection(final URL url, final String agent) throws IOException {
        return openConnection(url, Proxy.NO_PROXY, agent);
    }
}
