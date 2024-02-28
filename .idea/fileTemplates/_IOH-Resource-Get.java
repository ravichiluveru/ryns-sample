#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import io.ryns.platform.core.buildable.AbstractBuildable;
import io.ryns.platform.core.buildable.BigStringParam;
import io.ryns.platform.core.buildable.StringParam;
import io.ryns.platform.core.impl.ioh.ValidationTypes;
import io.ryns.platform.core.ioh.HandlerBinding;
import io.ryns.platform.core.ioh.Input;
import io.ryns.platform.core.ioh.InputType;
import io.ryns.platform.core.ioh.Output;
import io.ryns.platform.core.ioh.SimpleHandler;
import io.ryns.platform.core.query.QueryFilter;
import io.ryns.platform.core.query.SimpleQueryFilter;
import io.ryns.platform.core.request.RequestContext;
import io.ryns.platform.core.util.Strings;
import io.ryns.platform.core.util.Utils;

import java.util.Collection;
import java.util.Collections;

#parse("File Header.java")

public interface ${ResourceName}Get {
    class In extends AbstractBuildable implements Input<Collection<${ResourceName}>> {
        public static final InputType<In> TYPE = InputType.of(In.class);

        public final StringParam id = new StringParam(this);
        public final BigStringParam filter = new BigStringParam(this);

        @Override
        public InputType<In> getInputType() {
            return TYPE;
        }
    }

    @HandlerBinding(value = In.class)
    class Handler implements SimpleHandler<In, Collection<${ResourceName}>> {

        @Override
        public void doValidate(final RequestContext rc, final In req, final Output.Buildable<Collection<${ResourceName}>> out) {
            if (!Strings.isAnyNonEmpty(req.filter.get(), req.id.get())) {
                out.addValidationResponseCode(rc, req, ValidationTypes.Enum.MISSING_REQUIRED_FIELD, "id or filter");
            }
        }

        @Override
        public void doProcess(final RequestContext rc, final In req, final Output.Buildable<Collection<${ResourceName}>> res) {

            final ${ResourceName}Manager manager = ${ResourceName}Manager.get(rc);
            final Collection<${ResourceName}> resources;
            if (!Utils.isNullOrEmpty(req.id.get())) {
                final ${ResourceName} resource = manager.getResource(rc, req.id.get());
                resources = Collections.singletonList(resource);
            } else {
                final QueryFilter queryFilter = Strings.isNullOrEmpty(req.filter) ? null :
                                                SimpleQueryFilter.fromJson(rc, req.filter.get(), manager.getFields());
                if (queryFilter == null) {
                    res.addFailureResponseCode(rc, req, "Invalid / unsupported filter");
                    return;
                }
                resources = manager.getAll(rc, queryFilter);
            }
            res.body.set(resources);
            res.addSuccessResponseCode(rc, req);
        }
    }
}