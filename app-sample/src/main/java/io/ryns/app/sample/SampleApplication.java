package io.ryns.app.sample;

import io.ryns.platform.core.persist.MemoryPersistenceService;
import io.ryns.platform.core.persist.PersistenceService;
import io.ryns.platform.core.persist.PostgresPersistenceService;
import io.ryns.platform.core.util.Utils;
import io.ryns.platform.web.ApplicationStarter;

import static io.ryns.platform.core.util.ReflectUtils.BINDINGS_DEFAULT_PACKAGES_CK;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2023-12-30
 */
public class SampleApplication {
    public static final String APP_NAME = "sample";
    public static final String APP_CODE = "rsa"; //Ryns Template Application

    public static void main(String[] args) {
        Utils.nullSafeConsume(System.getProperties(), p -> {
            p.putIfAbsent(BINDINGS_DEFAULT_PACKAGES_CK, "io.ryns");
        });

        final ApplicationStarter appStarter = new ApplicationStarter(APP_NAME, APP_CODE);
        appStarter.setHttpServer(true)
//                .setJobServer(true)
                .start();
    }
}