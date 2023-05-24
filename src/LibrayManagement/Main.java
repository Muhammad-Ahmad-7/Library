package LibrayManagement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.loadData();
        int choice = 1;
        do{
            System.out.println("Select Option From Menu: ");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Borrow Book");
            System.out.println("4. Retrun Book");
            System.out.println("5. Display All Books");
            System.out.println("6. Display All Members");
            System.out.println("0. Exit");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice){
                case 1: {
                    //Adding Book
                    String name;
                    int id;
                    int numberOfBooks;
                    String author;
                    System.out.println("Enter Book Id: ");
                    id = sc.nextInt();
                    sc.nextLine(); // consume remaining newline character
                    System.out.println("Enter Book Title");
                    name = sc.nextLine();
                    System.out.println("Enter Book Author");
                    author = sc.nextLine();
                    System.out.println("Enter Number Of Books");
                    numberOfBooks = sc.nextInt();
                    Book book = new Book(id, name, author, numberOfBooks);
                    library.addBook(book);
                    library.saveData();
                    break;
                }
                case 2: {
                    //Add Member
                    String memberName;
                    int memberId;
                    System.out.println("Enter Id: ");
                    memberId = sc.nextInt();
                    sc.nextLine(); // consume remaining newline character
                    System.out.println("Enter Name");
                    memberName = sc.nextLine();
                    Member member = new Member(memberId, memberName);
                    library.addMember(member);
                    library.saveData();
                    break;
                }
                case 3: {
                    //Borrow Book
                    int bookId;
                    int memberId;
                    System.out.println("Enter Book Id: ");
                    bookId = sc.nextInt();
                    System.out.println("Enter Member Id: ");
                    memberId = sc.nextInt();
                    library.borrowBook(bookId, memberId);
                    library.saveData();
                    break;
                }
                case 4: {
                    //Return Book
                    int memberId;
                    System.out.println("Enter Member Id: ");
                    memberId = sc.nextInt();
                    library.returnBook(memberId);
                    library.saveData();
                    break;
                }
                case 5: {
                    //Display Books
                    library.displayBooks();
                    break;
                }
                case 6: {
                    //Display Members
                    library.displayMembers();
                    break;
                }
                case 0:
                    break;
                default:
                    System.out.println("Choose Valid Option");
            }
        }while(choice != 0);
        System.out.println("Thanks For Using");
    }
}
