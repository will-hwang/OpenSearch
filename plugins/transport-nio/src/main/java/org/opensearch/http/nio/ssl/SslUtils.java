/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 *
 * Modifications Copyright OpenSearch Contributors. See
 * GitHub history for details.
 */
package org.opensearch.http.nio.ssl;

import org.opensearch.OpenSearchSecurityException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

import java.security.NoSuchAlgorithmException;

public class SslUtils {
    private static final String[] DEFAULT_SSL_PROTOCOLS = { "TLSv1.3", "TLSv1.2", "TLSv1.1" };

    private SslUtils() {

    }

    public static SSLEngine createDefaultServerSSLEngine() {
        try {
            final SSLEngine engine = SSLContext.getDefault().createSSLEngine();
            engine.setEnabledProtocols(DEFAULT_SSL_PROTOCOLS);
            engine.setUseClientMode(false);
            return engine;
        } catch (final NoSuchAlgorithmException ex) {
            throw new OpenSearchSecurityException("Unable to initialize default server SSL engine", ex);
        }
    }

    public static SSLEngine createDefaultClientSSLEngine() {
        try {
            final SSLEngine engine = SSLContext.getDefault().createSSLEngine();
            engine.setEnabledProtocols(DEFAULT_SSL_PROTOCOLS);
            engine.setUseClientMode(true);
            return engine;
        } catch (final NoSuchAlgorithmException ex) {
            throw new OpenSearchSecurityException("Unable to initialize default client SSL engine", ex);
        }
    }
}
