package jsontutorial;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */

public class Employee {
    
   private Integer id;
   private String firstName;
   private String lastName;
   private List<String> roles;
    
    //construktor
    public Employee() {      
    }
    
    public Employee(Integer id, String firstName, String lastName, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //getter und setter

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    
    
    
    
    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", " + "lastName=" + lastName + ", roles=" + roles + "]";
    }
}
