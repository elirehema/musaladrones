package musala.project.drone.services;

import musala.project.drone.exceptions.DroneNotFoundException;
import musala.project.drone.repository.MedicationRepository;
import musala.project.drone.utils.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
public class MedicationReadPlatformServiceImpl implements MedicationReadPlatformService{
    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationReadPlatformServiceImpl(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Override
    public ResponseEntity<?> medicationLoadedInDrone(Long droneId) {
        return ResponseEntity.ok().body(this.medicationRepository.findByDroneId(droneId));
    }

    @Override
    public ResponseEntity<?> retrieveMedicationImage(long medicationId) {
        return this.medicationRepository.findById(medicationId).map(medication -> {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.valueOf(medication.getType()))
                    .body(ImageUtility.decompressImage(medication.getPicture()));
        }).orElseThrow(DroneNotFoundException::new);
    }
}
