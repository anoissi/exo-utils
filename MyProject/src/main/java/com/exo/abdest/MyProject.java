package com.exo.abdest;

/**
 * Created by exo on 28/09/16.
 */
import javax.portlet.*;
import java.io.IOException;


public class MyProject extends GenericPortlet {

    @RenderMode(name = "view")

    public void Hello(RenderRequest request, RenderResponse response) throws IOException, PortletException {

        PortletRequestDispatcher prDispatcher = getPortletContext().getRequestDispatcher("/jsp/index.jsp");

        prDispatcher.include(request, response);

    }
}