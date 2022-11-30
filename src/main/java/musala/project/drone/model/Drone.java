package musala.project.drone.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import musala.project.drone.data.DroneModel;
import musala.project.drone.data.DroneState;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * This file was created by eli on  29/11/2022 for musala.project.drone.model
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
@Entity(name = "m_drones")
@Table(name = "m_drones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "serial_no", unique = true)
    @NotBlank(message = "Serial Number is mandatory ")
    @Size(max = 100)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private DroneModel model;

    @Column
    @Max( 500)
    private Integer weight;

    @Column(name = "battery_percentage")
    //@NotBlank(message = "Battery percentage is mandatory ")
    private Integer batteryPercentage;

    @Enumerated(EnumType.STRING)
    private DroneState state;
    @OneToMany(
            mappedBy = "drone",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Medication> medications = new ArrayList<>();

    public void addMedication(Medication md){
        this.medications.add(md);
        md.setDrone(this);
    }

    public void removeMedication(Medication md){
        this.medications.remove(md);
        md.setDrone(null);
    }
}

