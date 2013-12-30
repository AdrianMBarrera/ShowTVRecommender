package AI;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.omertron.tvrageapi.TVRageApi;
import com.omertron.tvrageapi.TVRageApiTest;
import com.omertron.tvrageapi.model.ShowInfo;

public class ApiTVRage {

    private static Logger LOG = LoggerFactory.getLogger(TVRageApiTest.class);
    private static String APIKEY = "1tyJ0xqGoNMyZTaD1AY7";
    private static TVRageApi tvr;
    
	public ApiTVRage() {
		tvr = new TVRageApi(APIKEY);
	}
	
    public static int searchShow(String nameShow) {
        LOG.info("test searchShow");
        boolean found = false;
        int id = 0;

        tvr = new TVRageApi(APIKEY);
        
    	nameShow = nameShow.replace(" ", "%20");
        List<ShowInfo> showList = tvr.searchShow(nameShow);
    	nameShow = nameShow.replace("%20", " ");
        
        for (ShowInfo showInfo : showList) {
            if (showInfo.getShowName().equalsIgnoreCase(nameShow)) {
            	System.out.println("ID: " + showInfo.getShowID());
            	id = showInfo.getShowID();
            	found = true;
                break;
            }
        }
        
        if (!found)
        	id = 0;
        
        return id;
    }

    
    public static ShowInfo getShowInfoInt(int itemID) {
        LOG.info("test getShowInfoInt");
        tvr = new TVRageApi(APIKEY);
        ShowInfo showInfo = tvr.getShowInfo(itemID);
        
        //if (showInfo.getShowName().equals(TVRageApi.UNKNOWN))
        //	System.out.println("serie no encontrada");
        //else
        //	System.out.println("Nombre: " + showInfo.getShowName());
        
        return showInfo;
    }

    
    public static String getShowInfoString(String itemID) {
        LOG.info("test getShowInfoString");
        ShowInfo showInfo = tvr.getShowInfo(itemID);

        return showInfo.getShowName();
    }

}
