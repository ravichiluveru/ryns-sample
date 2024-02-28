package io.ryns.app.sample.common.http;

import java.util.List;

import io.ryns.platform.core.impl.request.http.HttpRouteManagerImpl;
import io.ryns.platform.core.repository.ValueRepository;
import io.ryns.platform.core.request.http.HttpRouteManager;
import io.ryns.platform.core.resource.ResourceManagerBinding;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-02-01
 */
@ResourceManagerBinding(value = HttpRouteManager.class)
public class HttpRouteManagerRsa extends HttpRouteManagerImpl {

    public HttpRouteManagerRsa() {
        super(() -> List.of(
                 ValueRepository.of(HttpRoutesRsa.ALL.makeImmutableAndGet())
                // Utils.castOrNull(PersistenceRepository.of(HttpRoutePersistable.class), ResourceRepository.class)
        ));
    }
}