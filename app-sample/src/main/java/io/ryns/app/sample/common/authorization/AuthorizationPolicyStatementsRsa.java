package io.ryns.app.sample.common.authorization;

import io.ryns.app.sample.common.resource.ResourceTypesRsa;
import io.ryns.platform.core.authorization.AuthorizationPolicyStatement;
import io.ryns.platform.core.impl.authorization.AuthorizationPolicyFields;
import io.ryns.platform.core.impl.policy.PolicyOps;
import io.ryns.platform.core.loader.LoadOnStartup;
import io.ryns.platform.core.resource.ResourceType;
import io.ryns.platform.core.util.ListCollector;

import static io.ryns.platform.core.impl.authorization.AuthorizationPolicyStatementImpl.builder;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-01-26
 */
@LoadOnStartup
public interface AuthorizationPolicyStatementsRsa {

    ResourceType RT = ResourceTypesRsa.AUTHORIZATION_POLICY_STATEMENTS_RSA;
    ListCollector<AuthorizationPolicyStatement> ALL = new ListCollector<>();
    AuthorizationPolicyStatement IS_BOOK_OWNER = ALL.add(builder(RT, "1")
            .setLeftField(PolicyFieldsRsa.BOOK_OWNER_ID)
            .setRightField(AuthorizationPolicyFields.AUTHENTICATED_USER_ID)
            .setOp(PolicyOps.EQUAL_OR_NULL));

    static void init() {
        //Intentional empty method for loading this class during startup with above @LoadOnStartup annotation.
    }
}