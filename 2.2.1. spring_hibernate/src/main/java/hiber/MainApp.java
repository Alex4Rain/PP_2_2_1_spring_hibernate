package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Volvo XC90", 120)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Toyota Hilux", 416)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Mazda CX7", 250)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Audi A4", 370)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar().getModel() + " " + user.getCar().getSeries());
         System.out.println();
      }


      User use15 = userService.getUserByCar("Mazda CX7", 250);
      System.out.println("Id = "+use15.getId());
      System.out.println("First Name = "+use15.getFirstName());
      System.out.println("Last Name = "+use15.getLastName());
      System.out.println("Email = "+use15.getEmail());
      System.out.println("Car = " + use15.getCar().getModel() + " " + use15.getCar().getSeries());
      System.out.println();


      context.close();
   }
}
