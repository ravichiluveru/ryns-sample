#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import io.ryns.platform.core.buildable.AbstractBuildable;
import io.ryns.platform.core.ioh.HandlerBinding;
import io.ryns.platform.core.ioh.Input;
import io.ryns.platform.core.ioh.InputType;
import io.ryns.platform.core.ioh.Output;
import io.ryns.platform.core.ioh.SimpleHandler;
import io.ryns.platform.core.request.RequestContext;

#parse("File Header.java")

public interface ${NAME}Create {
    class In extends AbstractBuildable implements Input<${NAME}>{
        public static final InputType<In> TYPE = InputType.of(In.class);

        @Override
        public InputType<In> getInputType() {
            return TYPE;
        }
    }    
    
    @HandlerBinding(value = In.class)
    class Handler implements SimpleHandler<In, ${NAME}> {
        @Override
        public void doValidate(RequestContext rc, In In, Output.Buildable<${NAME}> res) {
            //if (Strings.isAnyNullOrEmpty()) {
            //    res.addValidationResponseCode(rc, in, ValidationTypes.Enum.MISSING_REQUIRED_FIELD, "....");
            //} 
        }

        @Override
        public void doProcess(RequestContext rc, In in, Output.Buildable<${NAME}> res) {
            ${NAME}Persistable persisted = PersistenceService.saveIt(rc, new ${NAME}Persistable(in));
            res.body.set(persisted);
            res.addSuccessResponseCode(rc, in);
        }
    
    }
}