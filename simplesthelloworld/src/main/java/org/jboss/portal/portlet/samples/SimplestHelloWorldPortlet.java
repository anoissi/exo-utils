
package org.jboss.portal.portlet.samples;

import javax.portlet.GenericPortlet;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class SimplestHelloWorldPortlet extends GenericPortlet {
    public void doView(RenderRequest request, RenderResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.write("Hello World !");
        writer.close();
    }
}
