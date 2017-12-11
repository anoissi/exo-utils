package com.exo.abdest;

/**
 * Created by exo on 22/09/16.
 */


import org.picocontainer.Startable;

import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;


public class MyService implements Startable {

    private static final Log log = ExoLogger.getLogger(MyService.class);

    public void start() {
        log.warn("---------------------------------------------------------------------------------------------------------------------------------");
        log.info("My Service  - Start ");
        log.warn("---------------------------------------------------------------------------------------------------------------------------------");
    }

    public void stop() {
        log.warn("---------------------------------------------------------------------------------------------------------------------------------");
        log.info("My Service - Stop ");
        log.warn("---------------------------------------------------------------------------------------------------------------------------------");
    }

}