package de.jro.demo.web.defaults;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ProductSwaggerConfig.class, ErrorHandlingConfig.class})
public class WebDefaultConfig {}
