package may.com.mm.component;

import may.com.mm.component.spring.SpringContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@ComponentScan("may.com.mm.component")
public class ComponentConfiguration {

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        binder.registerCustomEditor(String.class, new java.beans.PropertyEditorSupport() {

            @Override
            public void setAsText(String text) {

                setValue(text == null ? null : text.trim());
            }
        });
    }

    @Bean
    public SpringContext springContext() {

        return new SpringContext();
    }

}
