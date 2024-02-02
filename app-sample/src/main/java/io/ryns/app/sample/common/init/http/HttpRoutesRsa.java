package io.ryns.app.sample.common.init.http;

import io.ryns.app.sample.common.init.resource.ResourceTypesRsa;
import io.ryns.platform.common.init.HttpRoutesRsw;
import io.ryns.platform.core.loader.LoadOnStartup;
import io.ryns.platform.core.request.http.HttpRoute;
import io.ryns.platform.core.resource.ResourceType;
import io.ryns.platform.core.util.ListCollector;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-01-25
 */
@LoadOnStartup
public interface HttpRoutesRsa {

    ResourceType RT = ResourceTypesRsa.HTTP_REQ_MAPPINGS_RSA;
    ListCollector<HttpRoute> ALL = new ListCollector<>();

    // HttpRoute FIRST = ALL.add(HttpRouteImpl.builder(RT, "1")
    //    .build());

    static void init() {
        ALL.addAll(HttpRoutesRsw.COMMON_STATIC_RESOURCES);
        ALL.addAll(HttpRoutesRsw.HEALTH_ROUTES);
        ALL.addAll(HttpRoutesRsw.COMMON_DEBUG_ROUTES);
        ALL.addAll(HttpRoutesRsw.DEV_ADMIN_ROUTES);
        //Intentional empty method for loading this class during startup with above @LoadOnStartup annotation.
    }
}