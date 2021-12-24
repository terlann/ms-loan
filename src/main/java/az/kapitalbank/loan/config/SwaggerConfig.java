package az.kapitalbank.loan.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MS-LOAN")
                        .description("This rest api use for hot lead receiving")
                        .contact(new Contact().email("Irshad.Eyvazov@kapitalbank.az"))
                        .version("v1"));
    }
}
