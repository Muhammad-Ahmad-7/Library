package LibrayManagement;

public class Book {
    private int bookId;
    private String title;
    private String author;

    private int memberIdBorrowedBook;
    private int numberOfBooks;
    private boolean isAvailable = true;

    public Book(int bookId, String title, String author, int numberOfBooks) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.numberOfBooks = numberOfBooks;
    }

    public Book(int bookId, String title, int memberIdBorrowedBook, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.memberIdBorrowedBook =  memberIdBorrowedBook;
    }

    public int getMemberIdBorrowedBook() {
        return memberIdBorrowedBook;
    }

    public void setMemberIdBorrowedBook(int memberIdBorrowedBook) {
        this.memberIdBorrowedBook = memberIdBorrowedBook;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }



    @Override
    public String toString() {
        if (!isAvailable){
            return "Not Available";
        }
        else {
            return "Book{" +
                    "bookId=" + bookId +
                    ", title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", isAvailable=" + isAvailable +
                    '}';
        }
    }
}
