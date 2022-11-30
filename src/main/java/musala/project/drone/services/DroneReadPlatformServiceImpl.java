package musala.project.drone.services;

import musala.project.drone.data.BatteryLevel;
import musala.project.drone.data.DroneState;
import musala.project.drone.exceptions.DroneNotFoundException;
import musala.project.drone.repository.DroneJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

/**
 * This file was created by eli on  29/11/2022 for musala.project.drone.services
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
@Repository
public class DroneReadPlatformServiceImpl implements DroneReadPlatformService{
    private final DroneJpaRepository droneJpaRepository;

    @Autowired
    public DroneReadPlatformServiceImpl(DroneJpaRepository droneJpaRepository) {
        this.droneJpaRepository = droneJpaRepository;
    }

    @Override
    public ResponseEntity<?> retrieveAllDrones() {
        return ResponseEntity.ok().body(this.droneJpaRepository.findAll());
    }

    @Override
    public ResponseEntity<?> retrieveDroneById(Long id) {
        return ResponseEntity.ok().body(this.droneJpaRepository.findById(id));
    }

    @Override
    public ResponseEntity<?> checkDroneBatteryLevelDroneById(Long id) {
        return this.droneJpaRepository.findById(id).map(drone -> {
            BatteryLevel level = BatteryLevel.instance(drone);
            return ResponseEntity.ok().body(level);
        }).orElseThrow(DroneNotFoundException::new);
    }

    @Override
    public ResponseEntity<?> retrieveAvailableDroneForLoading() {
        return ResponseEntity.ok().body(this.droneJpaRepository.findByState(DroneState.IDLE));
    }
}

