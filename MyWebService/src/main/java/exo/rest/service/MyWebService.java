package exo.rest.service;

import org.exoplatform.commons.utils.ListAccess;
import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.organization.User;
import org.exoplatform.services.organization.UserHandler;
import org.exoplatform.services.organization.impl.UserImpl;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.services.security.Identity;
import org.exoplatform.services.security.IdentityRegistry;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Random;

/**
 * Rest User Service!
 */

@Path("/demo")

@Produces("application/json")

public class MyWebService implements ResourceContainer {
  private static final Log log = ExoLogger.getLogger(MyWebService.class);

  @GET

  @Path("/hello/{name}")

  @RolesAllowed({ "administrators" })

  public String hello(@PathParam("name")

  String name) {

    return "Hello " + name;

  }

  @GET

  @Path("/listusers/{offset}")

  public Response getListUserName(@Context SecurityContext sc, @PathParam("offset") Integer offset) {

    JSONArray list = new JSONArray();

    JSONObject jsonObject = new JSONObject();

    String groupToCheck = "/platform/administrators";

    CacheControl cacheControl = new CacheControl();
    cacheControl.setNoCache(true);

    cacheControl.setNoStore(true);

    if (sc.getUserPrincipal() == null || !this.isMemberOf(sc.getUserPrincipal().getName(), groupToCheck)) {

      jsonObject.put("rights", "NOT-ALLOWED");

      list.put(jsonObject);

    } else {

      OrganizationService organizationService = (OrganizationService) ExoContainerContext.getCurrentContainer()

                                                                                         .getComponentInstanceOfType(OrganizationService.class);

      UserHandler userHandler = organizationService.getUserHandler();

      try {

        ListAccess<User> allUsers = userHandler.findAllUsers();

        if (offset == null || offset < 0)

          offset = 0;

        int limit = 1000;

        int total = limit + offset;

        int totalUsers = allUsers.getSize();

        if (offset < totalUsers && total > totalUsers) {

          total = totalUsers;

        }

        User[] users = null;

        for (int i = offset; i < total; i++) {

          users = allUsers.load(i, 1);

          jsonObject = new JSONObject();

          jsonObject.put("username", users[0].getUserName());

          list.put(jsonObject);

        }

      } catch (Exception e) {

        // TODO Auto-generated catch block

        e.printStackTrace();

      }

    }

    return Response.ok(list.toString(), MediaType.APPLICATION_JSON).cacheControl(cacheControl).build();

  }

  private boolean isMemberOf(String username, String role) {

    ExoContainer container = ExoContainerContext.getCurrentContainer();

    IdentityRegistry identityRegistry = (IdentityRegistry) container.getComponentInstanceOfType(IdentityRegistry.class);

    Identity identity = identityRegistry.getIdentity(username);

    return identity.isMemberOf(role);

  }
  
  @GET

  @Path("/add")

  @RolesAllowed({ "administrators" })

  public String addUser(
                        @QueryParam("username") String username,
                        @QueryParam("firstName") String firstName,
                        @QueryParam("lastName") String lastName,
                        @QueryParam("pswd") String pswd,
                        @QueryParam("email") String email) {

    OrganizationService organizationService =
                                            (OrganizationService) ExoContainerContext.getCurrentContainer()
                                                                                     .getComponentInstanceOfType(OrganizationService.class);

    try {
            UserImpl userImp = new UserImpl(username);
            userImp.setFirstName(firstName);
            userImp.setLastName(lastName);
            userImp.setEmail(email);
            userImp.setPassword(pswd);
            UserHandler userHandler = organizationService.getUserHandler();
            userHandler.createUser(userImp, true);

    } catch (Exception e) {
      log.warn("Exception [MyWebService]");
    }
    return "http://localhost:8080/portal/intranet/connexions";
  }

}
