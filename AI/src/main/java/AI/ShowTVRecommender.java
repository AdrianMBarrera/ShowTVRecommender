package AI;

import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;

import java.util.*;

public class ShowTVRecommender {	
    
	private ShowTVRecommender() {

	}    
    
    public static int recommenderFunction(DataModel model, long userID) throws Exception {
    	
		/*Here we are using Pearson's Correlation
	     * it has limitations such as that it does not account
	     * for the NUMBER of items in which 2 users' preferences overlap.
	     * There are other options such as Euclidean Distance similarity.
	     */

		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
	    
	    //First parameter - neighborhood size; capped at the number of users in the data model
	    UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

	    Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

	    //First parameter is UserID, the second is the number of items to be recommended
	    List<RecommendedItem> recommendations = recommender.recommend(userID,1);

	    int itemID = 0;
	    for (RecommendedItem recommendation : recommendations) {
	    	itemID = (int)recommendation.getItemID();
	      System.out.println("Serie Recomendada:" + 
	                            "\n\tID: " + itemID + 
	                            "\n\tNombre: " + ApiTVRage.getShowInfoInt(itemID) +
	                            "\n\tValor: " + recommendation.getValue());
	    }
	    
	    return itemID;
    	
    }
    

}
