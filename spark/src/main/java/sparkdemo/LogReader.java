package sparkdemo;

import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;
import org.apache.spark.api.java.function.FilterFunction;

// tag::main[]
/**
 * Read an Apache Logfile and summarize it.
 */
public class LogReader {

  public static void main(String[] args) {

    final String logFile = "/var/wildfly/standalone/log/access_log.log"; // <1>
    SparkSession spark = 
      SparkSession
              .builder()
              .appName("Log Analyzer")
              .config("spark.master", "local")
              .getOrCreate();      // <2>
    Dataset<String> logData = spark.read().textFile(logFile).cache();    // <3>

    long good = logData.filter(                                          // <4>
    new FilterFunction<>() {public boolean call(String s) {
          return s.contains("200");
        }
      }).count();

    long bad = logData.filter(new FilterFunction<>() {
        public boolean call(String s) {
          return s.contains("404");
        }
      }).count();

    long ugly = logData.filter(new FilterFunction<>() {
        public boolean call(String s) {
          return s.contains("500");
        }
      }).count();

    System.out.printf(                                                   // <5>
      "Successful transfers %d, 404 tries %d, 500 errors %d\n",
      good, bad, ugly);

    spark.stop();
  }
}
// end::main[]
