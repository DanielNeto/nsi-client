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

public enum ReservationStateEnumType {

    RESERVE_START("ReserveStart"),
    RESERVE_CHECKING("ReserveChecking"),
    RESERVE_FAILED("ReserveFailed"),
    RESERVE_ABORTING("ReserveAborting"),
    RESERVE_HELD("ReserveHeld"),
    RESERVE_COMMITTING("ReserveCommitting"),
    RESERVE_TIMEOUT("ReserveTimeout");

    private final String value;

    ReservationStateEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ReservationStateEnumType fromValue(String v) {
        for (ReservationStateEnumType c: ReservationStateEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}