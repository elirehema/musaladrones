package musala.project.drone.data;

import lombok.*;
import musala.project.drone.model.Drone;

/**
 * This file was created by eli on  30/11/2022 for musala.project.drone.data
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
@AllArgsConstructor
@ToString
@Getter
@Setter
public class BatteryLevel {
    public Long droneId;
    public String message;

    public static BatteryLevel instance(Drone drone){
        return new BatteryLevel(drone.getId(), "Battery Level: "+ drone.getBatteryPercentage());
    }
}
