package musala.project.drone.utils;

import musala.project.drone.data.DroneModel;
import musala.project.drone.data.DroneState;
import musala.project.drone.model.Drone;
import musala.project.drone.repository.DroneJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This file was created by eli on  30/11/2022 for musala.project.drone.utils
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
public class DroneDataLoader implements ApplicationRunner {
    private final DroneJpaRepository droneJpaRepository;

    @Autowired
    public DroneDataLoader(DroneJpaRepository droneJpaRepository) {
        this.droneJpaRepository = droneJpaRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Drone> drones = new ArrayList<>();
        for(int i =0; i<10;i++){
            drones.add(new Drone(null,"2138129321EIUYEUIWI"+i, DroneModel.Lightweight, i*5,i*4, DroneState.IDLE, null));

        }
        this.droneJpaRepository.saveAll(drones);
    }
}
