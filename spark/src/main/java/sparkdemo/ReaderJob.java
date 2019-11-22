package sparkdemo;

import java.io.IOException;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * Example using the older RDD; newer code uses DataSet instead.
 */
public class ReaderJob {
	public final static String SAMPLE_FILE = "/Users/ian/twitter-history/tweets.csv";
	private JavaSparkContext ctx;

	public static void main(String[] args) throws Exception {
		JavaSparkContext sc = 
			new JavaSparkContext(new SparkConf().
					setAppName("ReaderJob").
					setMaster("local"));
        ReaderJob job = new ReaderJob(sc);
		job.process(SAMPLE_FILE);
	}
	
	ReaderJob(JavaSparkContext ctx) {
		this.ctx = ctx;
	}

	void process(String fileName) throws IOException {
		
		System.out.println("ReaderJob.process(): " + ctx);

		JavaRDD<String> sampleInputFile = ctx.textFile(SAMPLE_FILE);

		for (String s : sampleInputFile.collect()) {
			System.out.println(s);
		}
		System.out.println(sampleInputFile.count());
	}
}
