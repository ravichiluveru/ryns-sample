package io.ryns.app.sample.common.http;

import io.ryns.app.sample.book.BookCreate;
import io.ryns.app.sample.book.BookUpdate;
import io.ryns.app.sample.common.action.ActionsRsa;
import io.ryns.app.sample.common.resource.ResourceTypesRsa;
import io.ryns.platform.common.init.HttpRoutesRsw;
import io.ryns.platform.core.impl.request.http.ContentTypes;
import io.ryns.platform.core.impl.request.http.HttpMethods;
import io.ryns.platform.core.impl.request.http.HttpRouteImpl;
import io.ryns.platform.core.loader.LoadOnStartup;
import io.ryns.platform.core.request.http.HttpRoute;
import io.ryns.platform.core.resource.ResourceType;
import io.ryns.platform.core.util.ListCollector;

import static io.ryns.platform.core.impl.request.http.ContentTypes.APPLICATION_JSON;
import static io.ryns.platform.core.impl.request.http.ContentTypes.TEXT_HTML;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-01-25
 */
@LoadOnStartup
public interface HttpRoutesRsa {

    ResourceType RT = ResourceTypesRsa.HTTP_REQ_MAPPINGS_RSA;
    ListCollector<HttpRoute> ALL = new ListCollector<>();

    HttpRoute BOOK_CREATE = ALL.add(HttpRouteImpl.builder(RT, "1")
            .setAction(ActionsRsa.BOOK_CREATE)
            .setRequestClass(BookCreate.In.class)
            .setAppPrefixPath("book-create")
            .setContentTypes(APPLICATION_JSON, TEXT_HTML)
            .setMethods(HttpMethods.POST));
    HttpRoute BOOK_UPDATE = ALL.add(HttpRouteImpl.builder(RT, "2")
            .setAction(ActionsRsa.BOOK_UPDATE)
            .setRequestClass(BookUpdate.In.class)
            .setAppPrefixPath("book-update")
            .setContentTypes(APPLICATION_JSON,TEXT_HTML)
            .setMethods(HttpMethods.POST));

    static void init() {
        ALL.addAll(HttpRoutesRsw.AUTHENTICATION_ROUTES);
        ALL.addAll(HttpRoutesRsw.COMMON_STATIC_RESOURCES);
        ALL.addAll(HttpRoutesRsw.HEALTH_ROUTES);
        ALL.addAll(HttpRoutesRsw.COMMON_DEBUG_ROUTES);
        ALL.addAll(HttpRoutesRsw.DEV_ADMIN_ROUTES);
        //Intentional empty method for loading this class during startup with above @LoadOnStartup annotation.
    }
}