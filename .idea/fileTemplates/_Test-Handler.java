#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import io.ryns.platform.test.BaseRequestHandlerTest;
import org.junit.jupiter.api.Test;
import static io.ryns.platform.core.impl.service.ValidationTypes.Enum.MISSING_REQUIRED_FIELD;

#parse("File Header.java")

public class ${NAME}HandlerTest extends BaseRequestHandlerTest {
    @Test
    public void testValidations() {
        final TestCase<${NAME}.Req, ${NAME}.Res> t =
            new TestCase<>(new ${NAME}.Req());

        t.verifyResponseCodes(t.validationResponseCode(MISSING_REQUIRED_FIELD));

        //Set params....
        t.verifyResponseCodes(t.validationResponseCode(MISSING_REQUIRED_FIELD));
    }
    @Test
    public void testForSuccess() {
        final TestCase<${NAME}.Req, ${NAME}.Res> t = new TestCase<>(new ${NAME}.Req());
    }
}