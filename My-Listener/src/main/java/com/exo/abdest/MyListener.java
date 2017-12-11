package com.exo.abdest;

/**
 * Created by exo on 23/09/16.
 */

import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.organization.User;
import org.exoplatform.services.organization.UserEventListener;

public class MyListener extends UserEventListener {
  private static final Log log = ExoLogger.getLogger(MyListener.class);
  public void postSave(User m, boolean isNew) throws Exception {

    log.warn("---------------------------------------------------------------------------------------------------------------------------------");
    log.info("After user is added into database ");
    log.warn("---------------------------------------------------------------------------------------------------------------------------------");
  }
}
