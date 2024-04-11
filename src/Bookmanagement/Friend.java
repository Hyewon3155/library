package Bookmanagement;

public class Friend {
	private int id;
    private String name;
    private String phone;
    private String school;
    private String type; // 대출 여부

    public Friend(int id, String name, String phone, String school, String type) {
        this.id = id;
    	this.name = name;
        this.phone = phone;
        this.school = school;
        this.type = type;
    }

    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
   
}
