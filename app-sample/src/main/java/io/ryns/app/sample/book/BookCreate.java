package io.ryns.app.sample.book;

import io.ryns.platform.core.buildable.AbstractBuildable;
import io.ryns.platform.core.buildable.StringParam;
import io.ryns.platform.core.impl.ioh.ValidationTypes;
import io.ryns.platform.core.ioh.HandlerBinding;
import io.ryns.platform.core.ioh.Input;
import io.ryns.platform.core.ioh.InputType;
import io.ryns.platform.core.ioh.Output;
import io.ryns.platform.core.ioh.SimpleHandler;
import io.ryns.platform.core.persist.PersistenceService;
import io.ryns.platform.core.request.RequestContext;
import io.ryns.platform.core.resource.ResourceParam;
import io.ryns.platform.core.user.User;
import io.ryns.platform.core.util.Strings;
import io.ryns.platform.core.util.Utils;

/**
 * @author ravi.chiluveru (Ravi Kumar Chiluveru)
 * @since 2024-02-01
 */
public interface BookCreate {
    class In extends BookCU implements Input<Book> {
        public static final InputType<In> TYPE = InputType.of(In.class);

        public final StringParam name = new StringParam(this);
        @Override
        public InputType<In> getInputType() {
            return TYPE;
        }
    }

    @HandlerBinding(value = In.class)
    class Handler implements SimpleHandler<In, Book> {
        @Override
        public void doValidate(RequestContext rc, In in, Output.Buildable<Book> res) {
            if (Strings.isAnyNullOrEmpty(in.name, in.author)) {
                res.addValidationResponseCode(rc, in, ValidationTypes.Enum.MISSING_REQUIRED_FIELD, "name, author");
            }
            if ( in.pages.get() <=0) {
                res.addValidationResponseCode(rc, in, ValidationTypes.Enum.MISSING_REQUIRED_FIELD, "pages");
            }
//            if(Utils.isNullOrEmpty(in.owner)){
//                res.addValidationResponseCode(rc, in, ValidationTypes.Enum.MISSING_REQUIRED_FIELD, "owner");
//            }
        }

        @Override
        public void doProcess(RequestContext rc, In in, Output.Buildable<Book> res) {
            final BookPersistable persisted = PersistenceService.saveIt(rc, new BookPersistable(in));
            res.body.set(persisted);
            res.addSuccessResponseCode(rc, in);
        }

    }
}