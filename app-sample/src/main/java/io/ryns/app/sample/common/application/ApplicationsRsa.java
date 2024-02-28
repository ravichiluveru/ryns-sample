package io.ryns.app.sample.common.application;

import io.ryns.platform.core.application.Application;
import io.ryns.platform.core.impl.application.ApplicationImpl;
import io.ryns.platform.core.loader.LoadOnStartup;
import io.ryns.platform.core.util.ListCollector;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-01-25
 */
@LoadOnStartup
public interface ApplicationsRsa {

    ListCollector<Application> ALL = new ListCollector<>();

    Application SAMPLE = ALL.add(ApplicationImpl.builder("101")
            .setSlug("sample")
            .setCode("rsa")
            .setName("Sample")
            .build());

    static void init() {
        //Intentional empty method for loading this class during startup with above @LoadOnStartup annotation.
    }
}