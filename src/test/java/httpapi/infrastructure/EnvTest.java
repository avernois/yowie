package httpapi.infrastructure;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class EnvTest {

    @Test
    public void should_return_default_port_when_env_is_not_defined() {
        final Integer DEFAULT_PORT = 1234;

        assertThat(Env.getPort(DEFAULT_PORT), is(DEFAULT_PORT));
    }

    @Test
    public void should_return_port_when_PORT_is_a_defined_env_variable() {
        final Integer DEFAULT_PORT = 1234;
        setEnv("PORT", 2345);

        assertThat(Env.getPort(DEFAULT_PORT), is(2345));
    }
    
    
    @Test public void 
    should_return_default_port_when_PORT_is_defined_but_not_an_integer() {
        final Integer DEFAULT_PORT = 1234;
        setEnv("PORT", "definitely_not_an_integer");
        
        assertThat(Env.getPort(DEFAULT_PORT), is(DEFAULT_PORT));
    }

    private void setEnv(String variable, Integer value) {
        setEnv(variable, value.toString());
    }
    
    private void setEnv(String variable, String value) {
        Map<String, String> env = new HashMap<String, String>();
        env.put(variable, value);
        setEnv(env);       
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void setEnv(Map<String, String> newenv) {
        try {
            Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
            Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
            theEnvironmentField.setAccessible(true);
            Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
            env.putAll(newenv);
            Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
            theCaseInsensitiveEnvironmentField.setAccessible(true);
            Map<String, String> cienv = (Map<String, String>) theCaseInsensitiveEnvironmentField.get(null);
            cienv.putAll(newenv);
        } catch (NoSuchFieldException e) {
            try {
                Class[] classes = Collections.class.getDeclaredClasses();
                Map<String, String> env = System.getenv();
                for (Class cl : classes) {
                    if ("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
                        Field field = cl.getDeclaredField("m");
                        field.setAccessible(true);
                        Object obj = field.get(env);
                        Map<String, String> map = (Map<String, String>) obj;
                        map.clear();
                        map.putAll(newenv);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
