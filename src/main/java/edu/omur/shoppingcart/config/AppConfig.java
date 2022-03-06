package edu.omur.shoppingcart.config;

import edu.omur.shoppingcart.model.Discount;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
public class AppConfig {
    private List<Discount> discountList = new ArrayList<Discount>();
}