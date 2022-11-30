package musala.project.drone;

import musala.project.drone.data.DroneModel;
import musala.project.drone.data.DroneState;
import musala.project.drone.model.Drone;
import musala.project.drone.repository.DroneJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class DroneApplication {
	public static void main(String[] args) {
		SpringApplication.run(DroneApplication.class, args);
	}


}
