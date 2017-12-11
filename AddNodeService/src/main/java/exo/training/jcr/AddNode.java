package exo.training.jcr;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.resource.ResourceContainer;

import javax.annotation.security.RolesAllowed;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Rest User Service!
 */

@Path("/demo")
@Produces("application/json")
public class AddNode implements ResourceContainer {
  private static final Log log = ExoLogger.getLogger(AddNode.class);

  @GET
  @Path("/addNode/{id}/{name}/{systemSession}/{nodeName}")
  @RolesAllowed({ "administrators" })
  public void addNodeService_(@PathParam("name") String name,
                              @PathParam("id") String id,
                              @PathParam("systemSession") String systemSession,
                              @PathParam("nodeName") String nodeName) throws RepositoryException {
    ExoContainer container = ExoContainerContext.getCurrentContainer();
    RepositoryService repositoryService = (RepositoryService) container.getComponentInstance(RepositoryService.class);
    Session session = repositoryService.getCurrentRepository().getSystemSession(systemSession);
    Node root = session.getRootNode();
    Node addNodeService = root.addNode(nodeName, "tn:training");
    addNodeService.setProperty("tn:id", session.getValueFactory().createValue(id));
    addNodeService.setProperty("tn:name", session.getValueFactory().createValue(name));
    log.warn("**************************************************************************");
    log.info("add new node ! ");
    log.warn("**************************************************************************");
    session.save();
  }
}
