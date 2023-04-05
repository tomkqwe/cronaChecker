package ru.lebedev.cronachecker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.lebedev.cronachecker.dao.DaoImpl;
import ru.lebedev.cronachecker.util.Parser;

@SpringBootApplication
public class CronaCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CronaCheckerApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(DaoImpl dao){
        return runner -> {
            insertExcangeMarket(dao);
            System.out.println("asasasa");
        };
    }

    private void insertExcangeMarket(DaoImpl dao) {
        var exchangeMarketEntities = Parser.runParse();
        exchangeMarketEntities.forEach(dao::save);
    }

}
