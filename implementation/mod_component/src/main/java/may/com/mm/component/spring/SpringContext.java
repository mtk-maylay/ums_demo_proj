package may.com.mm.component.spring;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

public class SpringContext implements ApplicationContextAware {

    private static final Logger LOG = LoggerFactory.getLogger(SpringContext.class);

    private static ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> clazz) {

        return SpringContext.applicationContext.getBean(clazz);

    }

    public static <T> T getBean(Class<T> clazz, String qualifier) {

//        return BeanFactoryAnnotationUtils.qualifiedBeanOfType(applicationContext.getAutowireCapableBeanFactory(), clazz,
//                                                              qualifier);

        ApplicationContext context = SpringContext.applicationContext;

        Map<String, T> beansOfType = context.getBeansOfType(clazz);

        for (Map.Entry<String, T> entry : beansOfType.entrySet()) {

            Qualifier qualifierAnnotation = context.findAnnotationOnBean(entry.getKey(), Qualifier.class);

            if (qualifierAnnotation != null) {

                if (qualifier.equals(qualifierAnnotation.value())) {
                    return entry.getValue();
                }
            }
        }

        throw new IllegalArgumentException(
            "No bean of type [" + clazz.getName() + "] with qualifier [" + qualifier + "] found");

    }

    public static Object getBean(String beanName) {

        return SpringContext.applicationContext.getBean(beanName);

    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {

        LOG.info("applicationContext is set : {}", applicationContext);

        SpringContext.applicationContext = applicationContext;

    }

}