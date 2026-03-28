package vijaystack.ai.config;

import java.util.List;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerEnterpriseConfig {

    private static final String SECURITY_SCHEME = "bearerAuth";

    // 🔥 MAIN OPENAPI CONFIG
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()

                // 🌍 Server (Your Port 9191)
                .servers(List.of(
                        new Server().url("http://localhost:9191").description("Local Server")
                ))

//                // 🔐 JWT Security (Optional but Ready)
//                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME))
//                .components(new Components()
//                        .addSecuritySchemes(SECURITY_SCHEME,
//                                new SecurityScheme()
//                                        .name("Authorization")
//                                        .type(SecurityScheme.Type.HTTP)
//                                        .scheme("bearer")
//                                        .bearerFormat("JWT")
//                        )
//                )

                // 📌 API INFO
                .info(new Info()
                        .title("VijayStack AI - Enterprise API")
                        .version("1.0")
                        .description("Enterprise-grade Swagger OpenAPI configuration for VijayStack AI platform")

                        .contact(new Contact()
                                .name("K. Vijay")
                                .email("vijaystack.ai@gmail.com")
                                .url("https://linkedin.com://vijaystack.ai"))

                        .license(new License()
                                .name("Enterprise License")
                                .url("https://vijaystack.ai"))
                )

                // 📚 External Docs
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation")
                        .url("https://docs.vijaystack.ai"));
    }

    // 📦 GROUP ALL APIs (COVERS YOUR CONTROLLER)
    @Bean
    public GroupedOpenApi allApis() {
        return GroupedOpenApi.builder()
                .group("vijaystack-ai-apis")
                .packagesToScan("vijaystack.ai.controller")
                .pathsToMatch("/api/v1/**")
                .build();
    }

    // ⚙️ GLOBAL HEADERS (ENTERPRISE TOUCH)
    @Bean
    public OperationCustomizer globalHeaders() {
        return (Operation operation, org.springframework.web.method.HandlerMethod handlerMethod) -> {

            operation.addParametersItem(new Parameter()
                    .in("header")
                    .name("X-Correlation-ID")
                    .required(false)
                    .description("Request tracking ID"));

            operation.addParametersItem(new Parameter()
                    .in("header")
                    .name("X-Source-System")
                    .required(false)
                    .description("Client system name"));

            return operation;
        };
    }
}