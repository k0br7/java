package hibernate_test;

import Entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test2 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            //добавляем работника в таблицу
            Session session = factory.getCurrentSession();
            Employee emp = new Employee("Nikita", "BlackHole", "Application", 5000);
            session.beginTransaction();
            session.save(emp);

            int myId = emp.getId();

            Employee employee = session.get(Employee.class, myId);
            session.getTransaction().commit();

            System.out.println("Done!");

        }
        finally {
            factory.close();
        }
    }
}
