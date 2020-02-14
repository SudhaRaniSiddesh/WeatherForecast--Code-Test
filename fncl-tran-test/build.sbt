import au.com.cba.omnia.tempo.DSL._

import concurrent.duration._

etl.project("etl-spark-enr-fncl-tran-test", "au.com.cba.omnia.etl.spark.enr.fncl.tran.test")

List(
  ("SAP_FNCL_TRAN_TEST_TRANSFORM_ENR", Set("HLS_FNCL_TRAN_CUST_STAT_TRAN_SZBICDCS_TRANSFORM_DIL"), 12 hours)
) map schedule

def schedule(dependencies: (String, Set[String], FiniteDuration)): Setting[_] = {
  etl.addSchedule(
    s"${dependencies._1}" runs daily,
    dependencies = dependencies._2,
    sla = FixedSLA(dependencies._3),
    dataLatency = 1 days
  )
}