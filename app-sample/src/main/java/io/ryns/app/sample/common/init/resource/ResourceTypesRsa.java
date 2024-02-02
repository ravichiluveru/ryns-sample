package io.ryns.app.sample.common.init.resource;

import io.ryns.app.sample.common.init.http.HttpRoutesRsa;
import io.ryns.app.sample.common.init.authorization.AuthorizationPoliciesRsa;
import io.ryns.platform.common.init.ResourceTypesRsw;
import io.ryns.platform.core.impl.resource.ResourceTypeImpl;
import io.ryns.platform.core.impl.resource.ResourceTypes;
import io.ryns.platform.core.request.RequestContext;
import io.ryns.platform.core.resource.ResourceManager;
import io.ryns.platform.core.resource.ResourceType;
import io.ryns.platform.core.util.SetCollector;

import java.util.function.Function;

import static io.ryns.app.sample.common.init.ApplicationsRsa.SAMPLE;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-01-25
 */
public interface ResourceTypesRsa {

    class I {

        private static ResourceTypeImpl.Builder type(String iId, Class<?> cls) {
            return type(iId, cls, null);
        }

        private static ResourceTypeImpl.Builder type(String iId, Class<?> cls, Function<RequestContext, ResourceManager<?>> resourceManagerFunction) {
            return ResourceTypes.type(SAMPLE, iId, cls, resourceManagerFunction);
        }

        private static ResourceTypeImpl.Builder type(ResourceType resourceType, Class<?> cls) {
            return ResourceTypes.type(SAMPLE, resourceType, cls, null);
        }
    }

    // Internal temp object to collect all the instances to a collection to return through resource manager.
    SetCollector<ResourceType> ALL = new SetCollector<>();

    ResourceType HTTP_REQ_MAPPINGS_RSA = ALL.add(I.type(ResourceTypesRsw.HTTP_ROUTES_RSW, HttpRoutesRsa.class));
    ResourceType AUTHORIZATION_POLICIES_RSA = ALL.add(I.type(ResourceTypes.AUTHORIZATION_POLICIES, AuthorizationPoliciesRsa.class));
    ResourceType AUTHORIZATION_POLICY_STATEMENTS_RSA = ALL.add(I.type(ResourceTypes.AUTHORIZATION_POLICY_STATEMENTS, AuthorizationPoliciesRsa.class));
//    ResourceType USERS_ALB = ALL.add(ResourceTypes.USER, UsersAlb.class);


    static void init() {
        //Intentional empty method for loading this class during startup with above @LoadOnStartup annotation.
    }
}