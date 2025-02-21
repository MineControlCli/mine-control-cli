package net.andrecarbajal.mine_control_cli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static final String APP_FOLDER_NAME = "mine-control-cli";
    public static final String VERSION = "0.0.1";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
