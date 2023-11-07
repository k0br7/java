package spring_introduction;

import org.springframework.context.annotation.*;

import javax.sound.midi.Soundbank;

@Configuration
@PropertySource("classpath:myApp.properties")
public class MyConfig {

    @Bean
    public Pet catBean() {
        return new Cat();
    }

    @Bean
    public Person personBean(){
        return new Person(catBean());
    }
}
