import java.util.*;
import static UserSimilarity;
import static PearsonCorrelationSimilarity;
import static UserNeighborhood;
import static NearestNUserNeighborhood;
import static Recommender;
import static GenericUserBasedRecommender;

class RecommenderIntro {
	public static void main(String[] args) throws Exception {
		DataModel model =
			new FileDataModel (new File("intro.csv"));

		UserSimilarity similarity =
			new PearsonCorrelationSimilarity (model);
		UserNeighborhood neighborhood =
			new NearestNUserNeighborhood (2, similarity, model);

		Recommender recommender = new GenericUserBasedRecommender (
			model, neighborhood, similarity);

		List<RecommendedItem> recommendations =
			recommender.recommend(1, 1);

		for (RecommendedItem recommendation : recommendations) {
			System.out.println(recommendation);
		}
	}
}