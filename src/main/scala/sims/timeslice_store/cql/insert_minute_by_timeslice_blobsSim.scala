package sims.timeslice_store.cql

import actions.timeslice_store.cql.minuteByTimesliceBlobsActions
import com.datastax.gatling.plugin.CqlPredef._
import com.datastax.gatling.stress.core.BaseSimulation
import com.datastax.gatling.stress.libs.SimConfig
import feeds.timeslice_store.cql.minuteByTimeSliceBlobsFeed
import io.gatling.core.Predef._

class InsertMinuteByTimesliceBlobsSimulation extends BaseSimulation  {
  val simName = "insertMinuteByTimesliceSim"
  val scenarioName = "InsertMinuteByTimeSliceBlobsScenario"

  val simConf = new SimConfig(conf, simName, scenarioName)

  val minuteByTimesliceActions = new minuteByTimesliceBlobsActions(cass, simConf)

  val minuteByTimesliceFeed = new minuteByTimeSliceBlobsFeed

  val writeScenario = scenario("writeMinuteByTimesliceBlobsScenario")
    .feed(minuteByTimesliceFeed.getMinuteByTimeSliceBlobs)
    .exec(minuteByTimesliceActions.writeMinuteTimesliceBlobs)

  setUp(

    //loadGenerator.constantToTotal(writeScenario, simConf)
    loadGenerator.rampUpToConstant(writeScenario, simConf)
    //loadGenerator.runOnlyOnce(writeScenario)

  ).protocols(cqlProtocol)
}
