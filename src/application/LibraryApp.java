package application;

import dao.LibraryDAO;
import entity.*;
import java.time.LocalDate;
import java.util.List;

public class LibraryApp {

    public static void main(String[] args) {
        LibraryDAO libraryDAO = new LibraryDAO();

        try {
            System.out.println("Creating authors...");
            Author author1 = new Author("George Orwell");
            Author author2 = new Author("Jane Austen");
            Author author3 = new Author("J.K. Rowling");

            libraryDAO.saveAuthor(author1);
            libraryDAO.saveAuthor(author2);
            libraryDAO.saveAuthor(author3);
            System.out.println("Authors created and saved successfully!");
            
            Biography bio1 = new Biography(
                "Eric Arthur Blair, known by his pen name George Orwell, was an English novelist and essayist.",
                author1
            );
            Biography bio2 = new Biography(
                "Jane Austen was an English novelist known primarily for her six major novels.",
                author2
            );
            Biography bio3 = new Biography(
                "Joanne Rowling, better known by her pen name J. K. Rowling, is a British author and philanthropist.",
                author3
            );

            libraryDAO.saveBiography(bio1);
            libraryDAO.saveBiography(bio2);
            libraryDAO.saveBiography(bio3);
            System.out.println("Biographies created and saved successfully!");
            
            PrintedBook printedBook1 = new PrintedBook("1984", author1, 328);
            PrintedBook printedBook2 = new PrintedBook("Pride and Prejudice", author2, 432);

            libraryDAO.saveBook(printedBook1);
            libraryDAO.saveBook(printedBook2);
            System.out.println("Printed books created and saved successfully!");
            
            EBook ebook1 = new EBook("Animal Farm", author1, "http://library.com/downloads/animal-farm.pdf");
            EBook ebook2 = new EBook("Harry Potter and the Philosopher's Stone", author3, "http://library.com/downloads/hp1.epub");

            libraryDAO.saveBook(ebook1);
            libraryDAO.saveBook(ebook2);
            System.out.println("E-books created and saved successfully!");
            
            Student student1 = new Student("Alice Johnson", "alice.johnson@university.edu", "S001");
            Student student2 = new Student("Bob Smith", "bob.smith@university.edu", "S002");
            Student student3 = new Student("Carol Davis", "carol.davis@university.edu", "S003");

            libraryDAO.saveStudent(student1);
            libraryDAO.saveStudent(student2);
            libraryDAO.saveStudent(student3);
            System.out.println("Students created and saved successfully!");
            
            BorrowedBook borrowedBook1 = new BorrowedBook(student1, printedBook1, LocalDate.now().minusDays(10));
            BorrowedBook borrowedBook2 = new BorrowedBook(student2, ebook1, LocalDate.now().minusDays(5));
            BorrowedBook borrowedBook3 = new BorrowedBook(student1, printedBook2, LocalDate.now().minusDays(3));
            BorrowedBook borrowedBook4 = new BorrowedBook(student3, ebook2, LocalDate.now().minusDays(1));

            libraryDAO.saveBorrowedBook(borrowedBook1);
            libraryDAO.saveBorrowedBook(borrowedBook2);
            libraryDAO.saveBorrowedBook(borrowedBook3);
            libraryDAO.saveBorrowedBook(borrowedBook4);
            System.out.println("Borrowed book records created and saved successfully!");


            System.out.println("All students:");
            List<Student> allStudents = libraryDAO.getAllStudents();
            for (Student s : allStudents) {
                System.out.println("- " + s.getName() + " (" + s.getStudentNumber() + ")");
            }

            System.out.println("All books:");
            List<Book> allBooks = libraryDAO.getAllBooks();
            for (Book b : allBooks) {
                System.out.println("- " + b.getTitle() + " by " + b.getAuthor().getName() +
                                 " [" + b.getClass().getSimpleName() + "]");
            }

            System.out.println("Books by George Orwell:");
            List<Book> orwellBooks = libraryDAO.findBooksByAuthor("George Orwell");
            for (Book b : orwellBooks) {
                System.out.println("- " + b.getTitle());
            }

            System.out.println("Borrowed books by Alice Johnson:");
            List<BorrowedBook> aliceBorrowedBooks = libraryDAO.findBorrowedBooksByStudent(student1.getId());
            for (BorrowedBook bb : aliceBorrowedBooks) {
                System.out.println("- " + bb.getBook().getTitle() + " (borrowed on " + bb.getBorrowDate() + ")");
            }

            System.out.println("Students with email 'alice.johnson@university.edu':");
            List<Student> aliceStudents = libraryDAO.findStudentsByCriteria("alice.johnson@university.edu");
            for (Student s : aliceStudents) {
                System.out.println("- " + s.getName());
            }


        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            LibraryDAO.close();
        }
    }
}
