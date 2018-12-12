package com.ravindra.practice.restapi.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class UserCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("COMMAND LINE RUNNER");
        userRepository.save(new User("Ranga","ADMIN"));
        userRepository.save(new User("Ravi","USER"));
        userRepository.save(new User("Satish","ADMIN"));
        userRepository.save(new User("Ramu","USER"));

        for(User user:userRepository.findAll()){
            log.info(user.toString());
        }
        log.info("Adim Users are ...");
        for (User user:userRepository.findByRole("ADMIN")){
            log.info(user.toString());
        }
    }
}
