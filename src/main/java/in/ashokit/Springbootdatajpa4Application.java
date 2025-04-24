package in.ashokit;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import in.ashokit.entities.*;
import in.ashokit.services.EmployeeService;

@SpringBootApplication
public class Springbootdatajpa4Application {

	public static void main(String[] args) {
		 ConfigurableApplicationContext run = SpringApplication.run(Springbootdatajpa4Application.class, args);
         EmployeeService e=run.getBean(EmployeeService.class);
        // e.getEmps(3);
         
         /*
         ContactsEntity c=new ContactsEntity();
         e.getEmpsWithQBE(c);
         by default ,if you just create the object and dont pass the any variable data all records get fetched
		 */
         
         ContactsEntity c=new ContactsEntity();
         c.setContactName("kiran");
         e.getEmpsWithQBE(c);
	}

}
