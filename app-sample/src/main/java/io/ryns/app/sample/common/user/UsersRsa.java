package io.ryns.app.sample.common.user;

import io.ryns.app.sample.common.resource.ResourceTypesRsa;
import io.ryns.platform.core.impl.user.UserImpl;
import io.ryns.platform.core.loader.LoadOnStartup;
import io.ryns.platform.core.resource.ResourceType;
import io.ryns.platform.core.user.User;
import io.ryns.platform.core.util.ListCollector;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-02-14
 */
@LoadOnStartup
public interface UsersRsa {

    ResourceType RT = ResourceTypesRsa.USERS_RSA;
    ListCollector<User> ALL = new ListCollector<>();

     User FIRST = ALL.add(UserImpl.builder(RT, "1")
             .setUserName("rsa1"));

    static void init() {
        //Intentional empty method for loading this class during startup with above @LoadOnStartup annotation.
    }
}