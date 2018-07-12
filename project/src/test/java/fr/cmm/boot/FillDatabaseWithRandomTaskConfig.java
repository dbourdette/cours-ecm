package fr.cmm.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import fr.cmm.SpringConfig;

@Configuration
@Import(SpringConfig.class)
public class FillDatabaseWithRandomTaskConfig {
}
