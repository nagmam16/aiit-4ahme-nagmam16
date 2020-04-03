package jsontutorial;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author user
 */
public class Ue02_object {
    //Default constructor mit gson1  (gson objekt)
    Gson gson1 = new Gson();

    //GsonBuilder (objekt von gson)
    Gson gson = new GsonBuilder()
                 .disableHtmlEscaping()
                 .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                 .setPrettyPrinting()
                 .serializeNulls()
                 .create();

    
    
}
