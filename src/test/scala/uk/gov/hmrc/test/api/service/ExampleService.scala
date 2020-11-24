/*
 * Copyright 2020 HM Revenue & Customs
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

package uk.gov.hmrc.test.api.service

import uk.gov.hmrc.test.api.client.{HttpClient, ServiceResponse}
import uk.gov.hmrc.test.api.conf.TestConfiguration

import scala.concurrent.Await
import scala.concurrent.duration._

class ExampleService(client: HttpClient) {
  val host: String = TestConfiguration.url("des")

  def getInformation(regime: String, idType: String, id: String): ServiceResponse = {
    val url                                      = s"$host/cross-regime/customer/$regime/$idType/$id/information"
    def envHeader(env: String): (String, String) = ("Environment", env)
    Await.result(
      client.GET(url, ("Authorization", "Bearer EvYRlYH8AS_hZGw32ffqJ25Mz04a"), envHeader("ist0")),
      10.seconds
    )
  }
}
