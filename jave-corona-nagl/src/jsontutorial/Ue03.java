package jsontutorial;

import com.google.gson.Gson;
import java.util.Arrays;

/**
 *
 * @author user
 */
public class Ue03 {
    
    public static void main(String[] args) {
        Employee employee = new Employee();

        employee.setId(1);
        employee.setFirstName("Lokesh");
        employee.setLastName("Gupta");
        employee.setRoles(Arrays.asList("ADMIN", "MANAGER"));

        Gson gson2 = new Gson();

        System.out.println(gson2.toJson(employee));
    }
}
