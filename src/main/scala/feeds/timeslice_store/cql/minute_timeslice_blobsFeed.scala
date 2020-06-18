package feeds.timeslice_store.cql

import com.datastax.gatling.stress.core.BaseFeed
import com.typesafe.scalalogging.LazyLogging

class minuteByTimeSliceBlobsFeed extends BaseFeed with LazyLogging {

  def getMinuteByTimeSliceBlobs = {

    def rowData = this.getMinuteByTimeSliceBlobsRow
    Iterator.continually(rowData)
  }

  private def getMinuteByTimeSliceBlobsRow = {
    val txt = faker.lorem.characters(75, 100)
    val fakeblob = txt.getBytes("UTF-8")

    Map(
      "account_id" -> faker.random().nextInt(99999990),
      "agent_id" -> faker.random().nextInt(9555),
      "metric_id" -> faker.random().nextInt(99999),
      "date" -> getRandomEpoch,
      "begin_time" -> getRandomEpoch,
      "identifier" -> faker.random().nextInt(1000),
      "data" -> fakeblob
    )
  }
}
