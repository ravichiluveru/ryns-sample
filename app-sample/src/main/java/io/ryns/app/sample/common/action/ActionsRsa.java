package io.ryns.app.sample.common.action;

import io.ryns.app.sample.common.resource.ResourceTypesRsa;
import io.ryns.platform.core.action.Action;
import io.ryns.platform.core.impl.action.ActionImpl;
import io.ryns.platform.core.impl.authorization.AuthorizationPolicies;
import io.ryns.platform.core.loader.LoadOnStartup;
import io.ryns.platform.core.resource.ResourceType;
import io.ryns.platform.core.util.ListCollector;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-02-01
 */
@LoadOnStartup
public interface ActionsRsa {

    ResourceType RT = ResourceTypesRsa.ACTIONS_RSA;
    ListCollector<Action> ALL = new ListCollector<>();

    Action BOOK_CREATE = ALL.add(ActionImpl.builder(RT, "1")
                    .setPolicies(AuthorizationPolicies.ANY_USER_ACCESS)
//            .setAnonymousAccess(true)
            .setName("book-create"));
    Action BOOK_UPDATE = ALL.add(ActionImpl.builder(RT, "2")
//            .setAnonymousAccess(true)
            .setPolicies(AuthorizationPolicies.ANY_USER_ACCESS)
            .setName("book-update"));

    static void init() {
        //Intentional empty method for loading this class during startup with above @LoadOnStartup annotation.
    }
}