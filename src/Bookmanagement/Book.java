package Bookmanagement;

public class Book {
	private int id;
    private String title;
    private String author;
    private String publisher;
    private String status; // 대출 여부
    private String majorStatus; // 전공, 비전공 여부

    public Book(int id, String title, String author, String publisher, String status, String majorStatus) {
        this.id = id;
    	this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.status = status;
        this.majorStatus = majorStatus;
    }

    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getMajorStatus() {
        return majorStatus;
    }
    
    public void setMajorStatus() {
    	this.majorStatus = majorStatus;
    }
}
