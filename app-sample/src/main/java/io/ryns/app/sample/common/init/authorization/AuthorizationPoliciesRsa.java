package io.ryns.app.sample.common.init.authorization;

import io.ryns.app.sample.common.init.resource.ResourceTypesRsa;
import io.ryns.platform.core.authorization.AuthorizationPolicy;
import io.ryns.platform.core.loader.LoadOnStartup;
import io.ryns.platform.core.resource.ResourceType;
import io.ryns.platform.core.util.ListCollector;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-01-26
 */
@LoadOnStartup
public interface AuthorizationPoliciesRsa {

    ResourceType RT = ResourceTypesRsa.AUTHORIZATION_POLICIES_RSA;
    ListCollector<AuthorizationPolicy> ALL = new ListCollector<>();

//    AuthorizationPolicy FIRST = ALL.add(AuthorizationPolicyImpl.builder(RT, "1")
//            .setPolicyResult(AuthorizationResults.ALLOW)
//            .setPolicyType(PolicyTypes.AUTHORIZATION_POLICY)
//                    .setEnabled(true)
//                    .setPolicyStatement()
//            .build()
//    );

    static void init() {
        //Intentional empty method for loading this class during startup with above @LoadOnStartup annotation.
    }
}