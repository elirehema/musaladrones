package musala.project.drone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
@Entity(name = "m_medications")
@Table(name = "m_medications")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "med_name")
    @Pattern(regexp = "^[a-zA-Z0-9.\\-\\/-_ ]*$")
    @NotEmpty
    private String  name;


    @Column
    @NotNull
    private int weight;

    @Column
    @NotNull
    @Pattern(regexp = "^[A-Z0-9.\\-\\/-_ ]*$")
    private String code;

    @Column
    @NotNull
    private String type;

    @Column(name = "picture", unique = false, nullable = false, length = 100000)
    private byte[] picture;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Drone drone;


}
