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

public class QuerySummaryResponseType {

    private ConnectionStatesType connectionStates;
    private long sourceVlan;
    private long destVlan;

    public void setConnectionStates(ConnectionStatesType connectionStates) {
        this.connectionStates = connectionStates;
    }

    public ConnectionStatesType getConnectionStates() {
        return connectionStates;
    }

    public void setSourceVlan(long sourceVlan) {
        this.sourceVlan = sourceVlan;
    }

    public long getSourceVlan() {
        return sourceVlan;
    }

    public void setDestVlan(long destVlan) {
        this.destVlan = destVlan;
    }

    public long getDestVlan() {
        return destVlan;
    }
}
