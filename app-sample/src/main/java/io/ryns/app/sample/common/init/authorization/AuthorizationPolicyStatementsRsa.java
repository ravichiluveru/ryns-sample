package io.ryns.app.sample.common.init.authorization;

import io.ryns.app.sample.common.init.resource.ResourceTypesRsa;
import io.ryns.platform.core.authorization.AuthorizationPolicyStatement;
import io.ryns.platform.core.loader.LoadOnStartup;
import io.ryns.platform.core.resource.ResourceType;
import io.ryns.platform.core.util.ListCollector;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-01-26
 */
@LoadOnStartup
public interface AuthorizationPolicyStatementsRsa {

    ResourceType RT = ResourceTypesRsa.AUTHORIZATION_POLICY_STATEMENTS_RSA;
    ListCollector<AuthorizationPolicyStatement> ALL = new ListCollector<>();

//    AuthorizationPolicyStatement FIRST = ALL.add(builder(RT, "1")
//            .setLeftField(PolicyFields.)
//
//            .build());

    static void init() {
        //Intentional empty method for loading this class during startup with above @LoadOnStartup annotation.
    }
}