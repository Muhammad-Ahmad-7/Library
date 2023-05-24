package LibrayManagement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Library {
    ArrayList<Book> books;
    ArrayList<Book> borrowedBooks;
    ArrayList<Book> booksCopy;
    ArrayList<Member> members;
    List<String> booksList, membersList, borrowedBooksRead;
    Path path;
    void addBook(Book book){
        books.add(book);
    }
    void addMember(Member member){
        members.add(member);
    }
    void borrowBook(int bookId, int memberId){
        int bookIdAvailable = 0;
        path = Paths.get("BookBorrowed.txt");
        try{
            if (!Files.exists(path)){
                Path path1 = Files.createFile(path);
            }
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        for (Book tempBook: books){
            if (tempBook.getBookId()==bookId){
                bookIdAvailable++;
                if (tempBook.getNumberOfBooks() > 0 ){
                    for (Member member : members) {
                        if (member.getMemberId() == memberId) {
                            String writeFile = tempBook.getBookId()+","+tempBook.getTitle()+","+tempBook.getAuthor()+","+member.getMemberId();
                            try {
                                if (Files.size(path) > 0){
                                    Files.write(path, (writeFile + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
                                }
                                else
                                    Files.write(path, ("\n"+writeFile + System.lineSeparator()).getBytes());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            member.setBookBorrowed(tempBook);
                            System.out.println("Member Matched: "+ member.toString());
                            break;
                        }
                    }
                    tempBook.setNumberOfBooks(tempBook.getNumberOfBooks() - 1);
                    tempBook.setAvailable(false);
                } else{
                    System.out.println("Book Not Available");
                }
                break;
            }
        }
        if (bookIdAvailable == 0){
            System.out.println("No Book Found With This Id");
        }
    }

    void returnBook(int memberId) {
        Path path = Paths.get("BookBorrowed.txt");
        List<Book> borrowedBooks = new ArrayList<>();
        List<String> updatedBooks = new ArrayList<>();

        if (Files.exists(path)) {
            try {
                List<String> borrowedBooksRead = Files.readAllLines(path);

                for (String book : borrowedBooksRead) {
                    String[] parts = book.split(",");
                    if (parts.length >= 4) {
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        String author = parts[2];
                        int memberIdBorrowedBook = Integer.parseInt(parts[3]);
                        Book borrowedBook = new Book(id, name, memberIdBorrowedBook, author);
                        borrowedBooks.add(borrowedBook);
                    }
                }
                boolean found = false;

                for (Book book : borrowedBooks) {
                    if (book.getMemberIdBorrowedBook() == memberId) {
                        for (Book book2: books){
                            if (book2.getBookId() == book.getBookId()){
                                System.out.println(book2.getNumberOfBooks());
                                book2.setNumberOfBooks(book2.getNumberOfBooks()+1);
                            }
                        }
                        System.out.println("Book Returned Successfully");
                        System.out.println(book);
                        found = true;
                    } else {
                        updatedBooks.add(book.getBookId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getMemberIdBorrowedBook());
                    }
                }

                if (found) {
                    // Rewrite the file with updated borrowedBooks
                    try {
                        Files.write(path, updatedBooks);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("No matching borrowed book found.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("No Borrowed Book Found");
        }
    }

    void displayBooks(){
        for (String line : booksList) {
            System.out.println(line);
        }
    }
    void displayMembers(){
        for (String line : membersList) {
            System.out.println(line);
        }
    }
    void loadData(){
        boolean loadData = true;
        int id;
        String name;
        int numberOfBooksRemain;
        if(loadData){
            Path path = Paths.get("library.txt");
            Path path2 = Paths.get("member.txt");
            try {
                booksList = Files.readAllLines(path);
                membersList = Files.readAllLines(path2);
                books = new ArrayList<>();
                for (String book : booksList) {
                    String[] parts = book.split(",");
                    if (parts.length >= 3) {
                        id = Integer.parseInt(parts[0]);
                        name = parts[1];
                        String author = parts[2];
                        numberOfBooksRemain = Integer.parseInt(parts[3]);
                        Book book1 = new Book(id, name, author,numberOfBooksRemain);
                        books.add(book1);
                    }
                }
                members = new ArrayList<>();
                for (String line : membersList) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        id = Integer.parseInt(parts[0]);
                        name = parts[1];
                        Member member = new Member(id, name);
                        members.add(member);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    void saveData(){
        Path path = Paths.get("library.txt");
        Path path1 = Paths.get("member.txt");
        List<String> booksData = new ArrayList<>();
        List<String> membersData = new ArrayList<>();
        for (Book book: books){
            if (book!= null){
                String lines = book.getBookId()+","+book.getTitle()+","+book.getAuthor()+","+book.getNumberOfBooks();
                booksData.add(lines);
            }
        }
        for (Member member: members){
            if (member!= null){
                String lines = member.getMemberId()+","+member.getName();
                membersData.add(lines);
            }
        }
        try {
            Files.write(path,booksData, StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(path1,membersData,StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
