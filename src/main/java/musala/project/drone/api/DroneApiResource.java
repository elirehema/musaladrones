package musala.project.drone.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import musala.project.drone.data.BatteryLevel;
import musala.project.drone.model.Drone;
import musala.project.drone.model.Medication;
import musala.project.drone.services.DroneReadPlatformService;
import musala.project.drone.services.DroneWritePlatformService;
import musala.project.drone.services.MedicationReadPlatformService;
import musala.project.drone.services.MedicationWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * This file was created by eli on  29/11/2022 for musala.project.drone.api
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController()
@RequestMapping("/drones")
@Api(value = "/drones", tags = "Drone", description = "Manage drone operations")
public class DroneApiResource {
    private final DroneWritePlatformService droneWritePlatformService;
    private final DroneReadPlatformService droneReadPlatformService;
    private final MedicationWritePlatformService medicationWritePlatformService;
    private final MedicationReadPlatformService medicationReadPlatformService;

    @Autowired
    public DroneApiResource(DroneWritePlatformService droneWritePlatformService, DroneReadPlatformService droneReadPlatformService, MedicationWritePlatformService medicationWritePlatformService, MedicationReadPlatformService medicationReadPlatformService) {
        this.droneWritePlatformService = droneWritePlatformService;
        this.droneReadPlatformService = droneReadPlatformService;
        this.medicationWritePlatformService = medicationWritePlatformService;
        this.medicationReadPlatformService = medicationReadPlatformService;
    }

    @ApiOperation(value = "GET all Drones", notes = "Retrieve all drones", response = Drone.class,responseContainer = "List")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> retrieveDrones() {
        return droneReadPlatformService.retrieveAllDrones();
    }

    @ApiOperation(value = "UPDATE Drone", notes = "Update drone with ID", response = Drone.class)
    @RequestMapping(value = "/{droneId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> updateDroneWithId(@PathVariable(name = "droneId") Long droneId, @RequestBody @Valid Drone body) {
        return this.droneWritePlatformService.updateDrone(droneId, body);
    }
    @ApiOperation(value = "Drone with medications", notes = "Retrieve drone with medical items", response = Drone.class)
    @RequestMapping(value = "/{droneId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> retrieveDroneWithMedication(@PathVariable(name = "droneId") Long droneId) {
        return droneReadPlatformService.retrieveDroneById(droneId);
    }
    @ApiOperation(value = "Check drone battery level", notes = "Check drone battery level", response = BatteryLevel.class)
    @RequestMapping(value = "/{droneId}/battery", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> retrieveDroneBatteryLevel(@PathVariable(name = "droneId") Long droneId) {
        return droneReadPlatformService.checkDroneBatteryLevelDroneById(droneId);
    }

    @ApiOperation(value = "Retrieve all available drones", notes = "Retrieve available drones for loading", response = Drone.class, responseContainer = "List")
    @RequestMapping(value = "/available", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> retrieveAvailableDrones() {
        return droneReadPlatformService.retrieveAvailableDroneForLoading();
    }
    @ApiOperation(value = "CREATE new drone", notes = "Register new drone", response = Drone.class)
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> registerNewDrone(@RequestBody @Valid Drone drone) {
        return droneWritePlatformService.registerDrone(drone);
    }

    @ApiOperation(value = "LOAD drone with new medication", notes = "Load drone with new medication Item", response = Drone.class)
    @RequestMapping(value = "/{droneId}/medications", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> loadDroneWithMedicationItem(@PathVariable(name = "droneId", required = true) Long droneId, @RequestParam String name,@RequestParam String code,@RequestParam Integer weight, @RequestPart MultipartFile file) {
        Medication m = new Medication();
        m.setName(name);
        m.setWeight(weight);
        m.setCode(code);
        return medicationWritePlatformService.loadDroneWithMedication(droneId, m,file);
    }

    @ApiOperation(value = "LOADED medications in drone", notes = "Load medication items in given drone", response = Drone.class, responseContainer = "List")
    @RequestMapping(value = "/medications/{droneId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> loadedMedicationItemsInDrone(@PathVariable(name = "droneId", required = true) Long droneId) {
        return medicationReadPlatformService.medicationLoadedInDrone(droneId);
    }

    @ApiOperation(value = "LOAD medication image", notes = "Load medication item image")
    @RequestMapping(value = "/medications/{medicationId}/image", method = RequestMethod.GET)
    ResponseEntity<?> getMedicationImageResource(@PathVariable(name = "medicationId", required = true) Long medicationId) {
        return medicationReadPlatformService.retrieveMedicationImage(medicationId);
    }
}
