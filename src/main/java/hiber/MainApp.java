package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Ivan", "Vorobyov", "vorobyov@mail.ru");
        User user2 = new User("Petr", "Andreev", "andreev@mail.ru");
        User user3 = new User("Anton", "Alekseev", "alekseev@mail.ru");

        Car car1 = new Car("Toyota", 4347);
        Car car2 = new Car("Lada", 2107);
        Car car3 = new Car("Kia", 2100);

        car1.setUser(user1);
        car2.setUser(user2);
        car3.setUser(user3);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);

        List<User> users = userService.listUsers();
        for (User user : users) {
             System.out.println("Id = " + user.getId());
             System.out.println("First Name = " + user.getFirstName());
             System.out.println("Last Name = " + user.getLastName());
             System.out.println("Email = " + user.getEmail());
             System.out.println("Car model: " + user.getCar().getModel());
             System.out.println("Car series: " + user.getCar().getSeries());
             System.out.println();
        }

        System.out.println();
        System.out.println(userService.getUserByCar("Toyota", 4347));
        System.out.println(userService.getUserByCar("Lada", 2107));
        System.out.println(userService.getUserByCar("Kia", 2100));

        context.close();
    }
}
