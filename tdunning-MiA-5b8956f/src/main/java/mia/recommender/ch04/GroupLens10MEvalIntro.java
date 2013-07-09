package mia.recommender.ch04;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.example.grouplens.GroupLensDataModel;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.recommender.knn.KnnItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.knn.NonNegativeQuadraticOptimizer;
import org.apache.mahout.cf.taste.impl.recommender.knn.Optimizer;
import org.apache.mahout.cf.taste.impl.recommender.slopeone.SlopeOneRecommender;
import org.apache.mahout.cf.taste.impl.recommender.svd.ALSWRFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;

final class GroupLens10MEvalIntro {

  private GroupLens10MEvalIntro() {
  }

  public static void main(String[] args) throws Exception {
    DataModel model = new FileDataModel(new File("src/main/java/mia/recommender/ch04/ua.base"));

    RecommenderEvaluator evaluator =
      new AverageAbsoluteDifferenceRecommenderEvaluator();
    RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
      @Override
      public Recommender buildRecommender(DataModel model) throws TasteException {
        UserSimilarity similarity = new LogLikelihoodSimilarity(model);
        ClusterSimilarity clusterSimilarity = new FarthestNeighborClusterSimilarity(similarity);
        return new TreeClusteringRecommender(model, clusterSimilarity, 10);
      }
    };
    double score = evaluator.evaluate(recommenderBuilder, null, model, 0.95, 0.05);
    System.out.println(score);
  }

}
