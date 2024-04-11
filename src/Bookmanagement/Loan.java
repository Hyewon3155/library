package Bookmanagement;

public class Loan {
	private int book_id;
	private int friend_id;
    private String loanDate;
    private String friendName;
    private String bookTitle;
    private String returnDate;
    private String dueDate;    
    
    public Loan(int book_id, int friend_id, String loanDate, String friendName, String bookTitle, String returnDate, String dueDate) {
    	this.book_id = book_id;
    	this.friend_id = friend_id;
    	this.loanDate = loanDate;
    	this.friendName = friendName;
    	this.bookTitle = bookTitle;
    	this.returnDate = returnDate;
    	this.dueDate = dueDate;
    }

    public int getBook_id() {
    	return book_id;
    }
    
    public void setBook_id(int book_id) {
    	this.book_id = book_id;
    }

    public int getFriend_id() {
    	return friend_id;
    }
    
    public void setFriend_id(int friend_id) {
    	this.friend_id = friend_id;
    }
    
    public String getLoanDate() {
    	return loanDate;
    }
    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getReturnDate() {
    	return returnDate;
    }
    
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
    
    public String getFriendName() {
    	return friendName;
    }
    
    public void setFriendName(String friendName) {
    	this.friendName = friendName;
    }
    

    public String getBookTitle() {
    	return bookTitle;
    }
    
    public void setBookTitle(String bookTitle) {
    	this.bookTitle = bookTitle;
    }
    
    public String getDueDate() {
    	return dueDate;
    }
    
    public void setDueDate(String dueDate) {
    	this.dueDate = dueDate;
    }
   
}
