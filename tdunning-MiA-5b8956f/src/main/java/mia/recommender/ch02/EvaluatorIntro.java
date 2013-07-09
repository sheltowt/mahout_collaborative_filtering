package mia.recommender.ch02;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.HadoopUtil;
import org.apache.mahout.common.RandomUtils;
import org.apache.hadoop.hdfs.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class EvaluatorIntro {

  private EvaluatorIntro() {
  }

  public static void main(String[] args) throws Exception {
    RandomUtils.useTestSeed();

//      Configuration conf = new Configuration();
//      conf.addResource(new Path("/home/williamshelton/Desktop/hadoop-1.1.2/conf/core-site.xml"));
//      conf.addResource(new Path("/home/williamshelton/Desktop/hadoop-1.1.2/conf/hdfs-site.xml"));

//      FileSystem fs = FileSystem.get(conf);
//      Path inFile = new Path("usr/dating_data");
//      FSDataInputStream file = fs.open(inFile);

      //new File("/home/williamshelton/Desktop/ml-100k/ua.base"));


      org.apache.hadoop.fs.Path pt = new org.apache.hadoop.fs.Path("hdfs://ubuntu:54310/usr/dating_data");
    FileSystem fs = FileSystem.get(new Configuration());
    BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(pt)));
    String line;
    line = br.readLine();
    while (line != null){
        System.out.println(line);
        line=br.readLine();
    }

//    DataModel model = new FileDataModel(new File(file));
//
//    RecommenderEvaluator evaluator =
//      new AverageAbsoluteDifferenceRecommenderEvaluator();
//    // Build the same recommender for testing that we did last time:
//    RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
//      @Override
//      public Recommender buildRecommender(DataModel model) throws TasteException {
//        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
//        UserNeighborhood neighborhood =
//          new NearestNUserNeighborhood(2, similarity, model);
//        return new GenericUserBasedRecommender(model, neighborhood, similarity);
//      }
//    };
//    // Use 70% of the data to train; test using the other 30%.
//    double score = evaluator.evaluate(recommenderBuilder, null, model, 0.7, 1.0);
//    System.out.println(score);
  }
}
