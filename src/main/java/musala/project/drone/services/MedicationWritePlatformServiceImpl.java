package musala.project.drone.services;

import com.sun.imageio.plugins.common.ImageUtil;
import musala.project.drone.data.DroneState;
import musala.project.drone.data.WarningMessage;
import musala.project.drone.exceptions.DroneNotFoundException;
import musala.project.drone.model.Drone;
import musala.project.drone.model.Medication;
import musala.project.drone.repository.DroneJpaRepository;
import musala.project.drone.repository.MedicationRepository;
import musala.project.drone.utils.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
public class MedicationWritePlatformServiceImpl implements MedicationWritePlatformService {
    private final DroneJpaRepository droneJpaRepository;

    @Autowired
    public MedicationWritePlatformServiceImpl(DroneJpaRepository droneJpaRepository) {
        this.droneJpaRepository = droneJpaRepository;
    }

    @Override
    public ResponseEntity<?> loadDroneWithMedication(Long droneId, Medication medication, MultipartFile file) {
        return  this.droneJpaRepository.findById(droneId).map(drone -> {
            if(drone.getBatteryPercentage() < 25){
                return ResponseEntity.badRequest().body(new WarningMessage(HttpStatus.BAD_REQUEST.toString(),"WARNING: The provided drone battery is below 25% of allowed battery percentage "));
            }
            if(medication.getWeight() > drone.getWeight()){
                return ResponseEntity.badRequest().body(new WarningMessage(HttpStatus.BAD_REQUEST.toString(), "Medication Item weight is heavier then allowed drone weight of: "+drone.getWeight()));
            }
            try {
                Medication md = Medication.builder()
                        .code(medication.getCode())
                        .name(medication.getName())
                        .weight(medication.getWeight())
                        .type(file.getContentType())
                        .picture(ImageUtility.compressImage(file.getBytes())).build();

            drone.addMedication(md);
            drone.setState(DroneState.LOADING);
            Drone d = droneJpaRepository.save(drone);
            return ResponseEntity.ok().body(d);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).orElseThrow(DroneNotFoundException::new);
    }

}
