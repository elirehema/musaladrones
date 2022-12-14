package musala.project.drone.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * This file was created by eli on  30/11/2022 for musala.project.drone.model
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
@Entity(name = "m_battery_hist")
@Table(name = "m_battery_hist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DroneBatteryHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "drone_id")
    private Long droneId;

    @Column(name = "battery_level")
    private Integer batteryLevel;

    @Column(name = "recorded_time")
    @Basic
    private Timestamp createdOn;

    public static DroneBatteryHistory instance(Drone d){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return new DroneBatteryHistory(null,d.getId(), d.getBatteryPercentage(),timestamp);
    }
}
