package io.ryns.app.sample.common.authorization;

import io.ryns.app.sample.common.resource.ResourceTypesRsa;
import io.ryns.platform.core.authorization.AuthorizationPolicy;
import io.ryns.platform.core.impl.authorization.AuthorizationPolicyImpl;
import io.ryns.platform.core.impl.authorization.AuthorizationResults;
import io.ryns.platform.core.impl.policy.PolicyTypes;
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

    AuthorizationPolicy BOOK_ACCESS = ALL.add(AuthorizationPolicyImpl.builder(RT, "1")
            .setPolicyResult(AuthorizationResults.ALLOW)
            .setPolicyType(PolicyTypes.AUTHORIZATION_POLICY)
            .setEnabled(true)
            .setPolicyStatement(AuthorizationPolicyStatementsRsa.IS_BOOK_OWNER)
            .setPolicyables(ResourceTypesRsa.BOOK_PERSISTABLE)
            .build());

    static void init() {
        //Intentional empty method for loading this class during startup with above @LoadOnStartup annotation.
    }
}