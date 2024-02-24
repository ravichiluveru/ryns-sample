#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import io.ryns.platform.core.buildable.AbstractBuildable;
import io.ryns.platform.core.impl.ioh.ValidationTypes;
import io.ryns.platform.core.ioh.HandlerBinding;
import io.ryns.platform.core.ioh.Input;
import io.ryns.platform.core.ioh.InputType;
import io.ryns.platform.core.ioh.Output;
import io.ryns.platform.core.ioh.SimpleHandler;
import io.ryns.platform.core.persist.PersistenceService;
import io.ryns.platform.core.request.RequestContext;
import io.ryns.platform.core.resource.ResourceParam;
import io.ryns.platform.core.util.Optional2;

#parse("File Header.java")

public interface ${Resource}Update {
    class In extends AbstractBuildable implements Input<${Resource}>{
        public static final InputType<In> TYPE = InputType.of(In.class);

        public final ResourceParam<${Resource}> ${Resource} = new ResourceParam<>(this, ${Resource}.PC_${Resource}_BY_ID);

        @Override
        public InputType<In> getInputType() {
            return TYPE;
        }
    }

    @HandlerBinding(value = In.class)
    class Handler implements SimpleHandler<In, ${Resource}> {
        @Override
        public void doValidate(RequestContext rc, In in, Output.Buildable<${Resource}> res) {
            //if (Strings.isAnyNullOrEmpty()) {
            //    res.addValidationResponseCode(rc, in, ValidationTypes.Enum.MISSING_REQUIRED_FIELD, "....");
            //}
            if (in.${Resource}.get() == null) {
                res.addValidationResponseCode(rc, in, ValidationTypes.Enum.MISSING_REQUIRED_FIELD, "${Resource}");
            }
        }

        @Override
        public void doProcess(RequestContext rc, In in, Output.Buildable<${Resource}> res) {
            ${Resource}Persistable persisted = Optional2.ofNullSafeCast(in.${Resource}.get(), ${Resource}Persistable.class)
                    .accept(p -> p.update(in))
                    .map(p -> PersistenceService.saveIt(rc, p))
                    .get();
            if (persisted != null) {
                res.body.set(persisted);
                res.addSuccessResponseCode(rc, in);
            } else {
                res.addFailureResponseCode(rc, in, "Unable to update ${Resource}");
            }
        }
    }
}