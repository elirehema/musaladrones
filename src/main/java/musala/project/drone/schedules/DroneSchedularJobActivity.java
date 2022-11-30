package musala.project.drone.schedules;

import musala.project.drone.model.Drone;
import musala.project.drone.model.DroneBatteryHistory;
import musala.project.drone.repository.DroneBatteryJpaRepository;
import musala.project.drone.repository.DroneJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This file was created by eli on  30/11/2022 for musala.project.drone.schedules
 * --
 * --
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
@Component
@Configuration
public class DroneSchedularJobActivity {
    @Autowired
    private DroneJpaRepository droneJpaRepository;
    @Autowired
    private DroneBatteryJpaRepository droneBatteryJpaRepository;
    @Scheduled(cron = "0 * * * * *")
    @Async
    public void scheduleFixedDelayTask() {
        System.out.println( "Battery level checked - " + System.currentTimeMillis() / 1000);
        Iterable<Drone> drones = this.droneJpaRepository.findAll();
        for(Drone drone: drones){
            DroneBatteryHistory history = DroneBatteryHistory.instance(drone);
            this.droneBatteryJpaRepository.save(history);
        }


    }
}
