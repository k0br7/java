package hibernate_test;

import Entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test4 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            //изменение объекта таблицы при помощи геттера и сеттера
            Employee emp = session.get(Employee.class, 1);
            emp.setSalary(1500);

            //с условиями
            session.createQuery("update Employee set salary=1000 " + "where firstName = 'Danil'")
                    .executeUpdate();

            session.getTransaction().commit();

            System.out.println("Done!");

        }
        finally {
            factory.close();
        }
    }
}
