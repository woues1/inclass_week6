package dao;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import entity.*;
import java.time.LocalDate;
import java.util.List;

public class LibraryDAO {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DevPU");

    public void saveStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
    }

    public Student findStudentById(int id) {
        EntityManager em = emf.createEntityManager();
        Student student = em.find(Student.class, id);
        em.close();
        return student;
    }

    public List<Student> getAllStudents() {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT s FROM Student s";
        TypedQuery<Student> query = em.createQuery(jpql, Student.class);
        List<Student> students = query.getResultList();
        em.close();
        return students;
    }

    public void updateStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(student);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteStudent(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student student = em.find(Student.class, id);
        if (student != null) {
            em.remove(student);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void saveBook(Book book) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
        em.close();
    }

    public Book findBookById(int id) {
        EntityManager em = emf.createEntityManager();
        Book book = em.find(Book.class, id);
        em.close();
        return book;
    }

    public List<Book> getAllBooks() {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT b FROM Book b";
        TypedQuery<Book> query = em.createQuery(jpql, Book.class);
        List<Book> books = query.getResultList();
        em.close();
        return books;
    }

    public void updateBook(Book book) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(book);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteBook(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Book book = em.find(Book.class, id);
        if (book != null) {
            em.remove(book);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void saveAuthor(Author author) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();
        em.close();
    }

    public Author findAuthorById(int id) {
        EntityManager em = emf.createEntityManager();
        Author author = em.find(Author.class, id);
        em.close();
        return author;
    }

    public void saveBiography(Biography biography) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(biography);
        em.getTransaction().commit();
        em.close();
    }

    public void saveBorrowedBook(BorrowedBook borrowedBook) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(borrowedBook);
        em.getTransaction().commit();
        em.close();
    }

    public BorrowedBook findBorrowedBookById(int id) {
        EntityManager em = emf.createEntityManager();
        BorrowedBook borrowedBook = em.find(BorrowedBook.class, id);
        em.close();
        return borrowedBook;
    }

    public List<BorrowedBook> getAllBorrowedBooks() {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT bb FROM BorrowedBook bb";
        TypedQuery<BorrowedBook> query = em.createQuery(jpql, BorrowedBook.class);
        List<BorrowedBook> borrowedBooks = query.getResultList();
        em.close();
        return borrowedBooks;
    }

    public void updateBorrowedBook(BorrowedBook borrowedBook) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(borrowedBook);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteBorrowedBook(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        BorrowedBook borrowedBook = em.find(BorrowedBook.class, id);
        if (borrowedBook != null) {
            em.remove(borrowedBook);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Student> findStudentsByName(String name) {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> student = cq.from(Student.class);
        cq.select(student).where(cb.like(student.get("name"), "%" + name + "%"));
        TypedQuery<Student> query = em.createQuery(cq);
        List<Student> students = query.getResultList();
        em.close();
        return students;
    }

    public List<Book> findBooksByAuthor(String authorName) {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);
        cq.select(book).where(cb.equal(book.get("author").get("name"), authorName));
        TypedQuery<Book> query = em.createQuery(cq);
        List<Book> books = query.getResultList();
        em.close();
        return books;
    }

    public List<BorrowedBook> findBorrowedBooksByStudent(int studentId) {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BorrowedBook> cq = cb.createQuery(BorrowedBook.class);
        Root<BorrowedBook> borrowedBook = cq.from(BorrowedBook.class);
        cq.select(borrowedBook).where(cb.equal(borrowedBook.get("student").get("id"), studentId));
        TypedQuery<BorrowedBook> query = em.createQuery(cq);
        List<BorrowedBook> borrowedBooks = query.getResultList();
        em.close();
        return borrowedBooks;
    }

    public List<Book> findBooksByCriteria(String title) {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);

        cq.select(book).where(cb.like(book.get("title"), "%" + title + "%"));

        TypedQuery<Book> query = em.createQuery(cq);
        List<Book> books = query.getResultList();
        em.close();
        return books;
    }

    public List<Student> findStudentsByCriteria(String email) {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> student = cq.from(Student.class);

        cq.select(student).where(cb.equal(student.get("email"), email));

        TypedQuery<Student> query = em.createQuery(cq);
        List<Student> students = query.getResultList();
        em.close();
        return students;
    }

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
