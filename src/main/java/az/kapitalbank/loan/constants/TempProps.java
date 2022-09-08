package az.kapitalbank.loan.constants;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("test")
@RefreshScope
@Data
public class TempProps {
    private String key;
}
