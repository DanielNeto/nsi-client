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

package br.rnp.sdnoverlay;

import br.rnp.sdnoverlay.types.PointToPointType;
import br.rnp.sdnoverlay.types.QuerySummaryResponseType;
import br.rnp.sdnoverlay.types.ReserveCriteriaType;
import br.rnp.sdnoverlay.types.ScheduleType;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        try {

            NSIClient client = new NSIClient();
            //client.setProviderNSA("");

            ScheduleType schedule = new ScheduleType();
            PointToPointType p2p = new PointToPointType();
            ReserveCriteriaType criteria = new ReserveCriteriaType();

            p2p.setCapacity(100);
            //p2p.setSourceSTP("urn:ogf:network:czechlight.cesnet.cz:2013:topology:");
            p2p.setSourceSTP("urn:ogf:network:cipo.rnp.br:2013::MXSP2:xe-3_0_0:+?vlan=202");
            //p2p.setDestSTP("urn:ogf:network:czechlight.cesnet.cz:2013:topology:");
            p2p.setDestSTP("urn:ogf:network:cipo.rnp.br:2013::MXPA:ge-2_3_4:+?vlan=202");

            criteria.setVersion(1);
            criteria.setSchedule(schedule);
            criteria.setPointToPoint(p2p);

            System.out.println("Reserving...");

            String connId = client.reserve("Teste App 2.2", criteria);

            System.out.println(connId);

            Thread.sleep(5000);

            //client.querySummarySync(connId);

            System.out.println("Commiting...");

            client.reserveCommit(connId);

            Thread.sleep(5000);

            QuerySummaryResponseType q = client.querySummarySync(connId);
            System.out.println(q.getSourceVlan());
            System.out.println(q.getDestVlan());
            System.out.println(q.getConnectionStates().getReservationState().value());
            System.out.println(q.getConnectionStates().getProvisionState().value());
            System.out.println(q.getConnectionStates().getLifeCycleState().value());
            System.out.println(q.getConnectionStates().getDataPlaneStatus().getVersion());

            System.out.println("Modifying...");

            criteria.getPointToPoint().setCapacity(200);
            criteria.setVersion(2);

            client.reserveModify(connId, criteria);

            Thread.sleep(5000);

            System.out.println("Commiting...");

            client.reserveCommit(connId);

            Thread.sleep(5000);

            System.out.println("Providing...");

            client.provision(connId);

            Thread.sleep(5000);

            client.querySummarySync(connId);

            System.out.println("Releasing...");

            client.release(connId);

            Thread.sleep(5000);

            client.querySummarySync(connId);

            System.out.println("Terminating...");

            client.terminate(connId);

            client.querySummarySync(connId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
