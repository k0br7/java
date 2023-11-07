package spring_introduction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLOutput;

public class ConfigWithAnnotations1 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext3");

            Person person = context.getBean("personBean", Person.class);
            person.callYourPet();

        System.out.println(person.getAge());
        System.out.println(person.getSurname());

            context.close();
    }
}
