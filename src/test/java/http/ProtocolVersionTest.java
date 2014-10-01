package http;

import http.message.ProtocolVersion;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Ivan on 24.09.2014 in 12:32.
 */
public class ProtocolVersionTest {
    private ProtocolVersion protocolVersion;
    private final String protocol = "HTTP/1.1";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testConstructor() {
        protocolVersion = new ProtocolVersion(protocol);
        Assert.assertEquals(protocol, protocolVersion.toString());
    }
}
