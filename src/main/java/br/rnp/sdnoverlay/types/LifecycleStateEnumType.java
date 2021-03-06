/*
 * Copyright 2017 Brazilian National Research and Educational Network - RNP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.rnp.sdnoverlay.types;

public enum LifecycleStateEnumType {

    CREATED("Created"),
    FAILED("Failed"),
    PASSED_END_TIME("PassedEndTime"),
    TERMINATING("Terminating"),
    TERMINATED("Terminated");

    private final String value;

    LifecycleStateEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LifecycleStateEnumType fromValue(String v) {
        for (LifecycleStateEnumType c: LifecycleStateEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}