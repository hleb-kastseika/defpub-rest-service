package gk.defpub.restservice.swagger;

import org.junit.Test;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * SwaggerConfigTest class.
 * <p>
 * Date: Feb 18, 2019
 * <p>
 *
 * @author Gleb Kosteiko
 */
public class SwaggerConfigTest {

    private SwaggerConfig swaggerConfig = new SwaggerConfig();

    @Test
    public void testGetApi() {
        Docket docket = swaggerConfig.getApi();
        assertThat(docket.getDocumentationType()).isEqualTo(DocumentationType.SWAGGER_2);
    }
}
