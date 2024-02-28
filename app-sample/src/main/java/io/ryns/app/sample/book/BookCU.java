package io.ryns.app.sample.book;

import io.ryns.platform.core.buildable.AbstractBuildable;
import io.ryns.platform.core.buildable.IntegerParam;
import io.ryns.platform.core.buildable.StringParam;
import io.ryns.platform.core.resource.ResourceParam;
import io.ryns.platform.core.user.User;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-02-01
 */
public abstract class BookCU extends AbstractBuildable {

    public final StringParam author = new StringParam(this);
    public final ResourceParam<User> owner = new ResourceParam<>(this, User.PC_USER_BY_ID);
    public final IntegerParam pages = new IntegerParam(this);
}