
package studentsmanagementsystem;

/**
 *
 * @author thlavlu
 */
public class Students {
   
    
    private Integer id;
    private String name;
    private String dept;
    private  Integer age;
    private  String email;

    public Students(Integer id, String name, String dept, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.age = age;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
    
    
}
