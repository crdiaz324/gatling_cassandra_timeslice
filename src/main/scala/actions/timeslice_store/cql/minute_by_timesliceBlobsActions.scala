package actions.timeslice_store.cql

import com.datastax.driver.core.ConsistencyLevel
import com.datastax.driver.core.querybuilder.QueryBuilder._
import com.datastax.driver.core.querybuilder.{Insert, QueryBuilder}
import com.datastax.gatling.plugin.CqlPredef._
import com.datastax.gatling.stress.core.BaseAction
import com.datastax.gatling.stress.libs.{Cassandra, SimConfig}
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder

import scala.annotation.meta

class minuteByTimesliceBlobsActions(cassandra: Cassandra, simConf: SimConfig) extends BaseAction(cassandra, simConf) {
  private val minuteTimesliceBlobsTable = "minute_timeslice_blobs"

  createKeyspace
  createTables()

  private val writeMinuteTimesliceBlobsQuery: Insert = QueryBuilder.insertInto(keyspace, minuteTimesliceBlobsTable)
    .value("account_id", raw(":account_id "))
    .value("agent_id", raw(":agent_id"))
    .value("metric_id", raw(":metric_id"))
    .value("date", raw(":date"))
    .value("begin_time", raw(":begin_time"))
    .value("identifier", raw(":identifier"))
    .value("data", raw(":data"))


  def writeMinuteTimesliceBlobs: ChainBuilder = {

    val preparedStatement = session.prepare(writeMinuteTimesliceBlobsQuery)
    exec(cql("writeMinuteTimesliceBlobs")
      .executeNamed(preparedStatement).withConsistencyLevel(ConsistencyLevel.LOCAL_ONE)
    )
  }


  def createTables(): Unit = {
    runQueries(
      Array(
        s"""CREATE TABLE IF NOT EXISTS $keyspace.$minuteTimesliceBlobsTable (
            account_id int,
            agent_id int,
            metric_id bigint,
            date timestamp,
            begin_time timestamp,
            identifier int,
            data blob,
            PRIMARY KEY ((account_id, agent_id, metric_id, date), begin_time, identifier)
         );"""
      )
    )
  }
}


