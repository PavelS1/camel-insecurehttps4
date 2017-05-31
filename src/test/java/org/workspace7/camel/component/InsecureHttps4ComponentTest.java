package org.workspace7.camel.component;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class InsecureHttps4ComponentTest extends CamelTestSupport {

    @Test
    public void testInsecurehttps4() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(1);

        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("timer://foo?repeatCount=1")
                    .to("insecurehttps4://localhost:8443/api/calculator/add/1/2")
                    .log("${body}")
                    .to("mock:result");
            }
        };
    }
}
