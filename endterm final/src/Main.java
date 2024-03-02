import java.util.ArrayList;
import java.util.List;

// 1st principle: interface. it is representing items that can be borrowed
interface Borrowable {
    void borrow(LibraryMember member);
}

// Interface of a Library
interface Library {
    void addItem(LibraryItem item);

    void removeItem(LibraryItem item);

    void listItems();

    void addMember(LibraryMember member);

    void removeMember(LibraryMember member);

    void listMembers();

    void borrowItem(LibraryItem item, LibraryMember member);
}

// 2nd principle: abstract class. shows items in the library, implements borrowable interface.
abstract class LibraryItem implements Borrowable {
    private int itemId;
    private String title;

    public LibraryItem(int itemId, String title) {
        this.itemId = itemId;
        this.title = title;
    }

    // 3rd principle: abstract method. will implement in subclass
    abstract void displayInfo();

    public int getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    // Implementation of the borrow method from the Borrowable interface
    @Override
    public void borrow(LibraryMember member) {
        System.out.println(member.getName() + " has borrowed '" + getTitle() + "'.");
    }
}

// 4th principle. Book class extending the abstract class and inherits its behaviour
class Book extends LibraryItem {
    private String author;

    public Book(int itemId, String title, String author) {
        super(itemId, title);
        this.author = author;
    }

    // Implementation of the abstract method
    @Override
    void displayInfo() {
        System.out.println("Book ID: " + getItemId());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + author);
        System.out.println();
    }
}


class LibraryMember {
    private int memberId;
    private String name;

    public LibraryMember(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Library class implements the Library interface
class MyLibrary implements Library {
    private List<LibraryItem> items = new ArrayList<>();
    private List<LibraryMember> members = new ArrayList<>();

    @Override
    public void addItem(LibraryItem item) {
        items.add(item);
    }

    @Override
    public void removeItem(LibraryItem item) {
        items.remove(item);
    }

    @Override
    public void listItems() {
        for (LibraryItem item : items) {
            item.displayInfo();
        }
    }

    @Override
    public void addMember(LibraryMember member) {
        members.add(member);
    }

    @Override
    public void removeMember(LibraryMember member) {
        members.remove(member);
    }

    @Override
    public void listMembers() {
        for (LibraryMember member : members) {
            System.out.println("Member ID: " + member.getName());
        }
    }

    @Override
    public void borrowItem(LibraryItem item, LibraryMember member) {
        item.borrow(member);
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        Book book1 = new Book(101, "Introduction to OOP", "John Doe");
        Book book2 = new Book(102, "Design Patterns", "Jane Smith");

        LibraryMember member = new LibraryMember(1001, "Alice Johnson");

        Library myLibrary = new MyLibrary();
        myLibrary.addItem(book1);
        myLibrary.addItem(book2);
        myLibrary.addMember(member);

        myLibrary.listItems();
        myLibrary.listMembers();

        myLibrary.borrowItem(book2, member);
    }
}
