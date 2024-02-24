#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import io.ryns.platform.core.buildable.AbstractBuildable;
import io.ryns.platform.core.ioh.HandlerBinding;
import io.ryns.platform.core.ioh.Input;
import io.ryns.platform.core.ioh.InputType;
import io.ryns.platform.core.ioh.Output;
import io.ryns.platform.core.ioh.SimpleHandler;
import io.ryns.platform.core.request.RequestContext;

#parse("File Header.java")
public interface ${NAME} {
class Req extends AbstractBuildable implements Input<Res> {
    public static final InputType<Req> TYPE = InputType.of(Req.class);

    @Override
    public InputType<Req> getInputType() {
        return TYPE;
    }

}

interface Res {

}

@HandlerBinding(value = Req.class)
class Handler implements SimpleHandler<Req, Res> {
    @Override
    public void doValidate(RequestContext rc, Req req, Output.Buildable<Res> res) {
        //if (Strings.isAnyNullOrEmpty()) {
        //    res.addValidationResponseCode(rc, req, ValidationTypes.Enum.MISSING_REQUIRED_FIELD, "....");
        //}
    }

    @Override
    public void doProcess(RequestContext rc, Req req, Output.Buildable<Res> res) {
        res.addSuccessResponseCode(rc, req);
    }
}
}