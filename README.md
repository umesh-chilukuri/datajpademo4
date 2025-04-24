
# Spring Boot Data JPA Application - Pagination and Query by Example (QBE)

## 📌 Overview

This project demonstrates how to use Spring Data JPA with features like **CRUD**, **Pagination**, **Sorting**, and **Query By Example (QBE)**. It's a great reference for beginners who are learning how to implement dynamic queries, filter conditions, and efficient data retrieval.

---

## 📂 Project Structure

```
src/
├── main/
│   ├── java/in/ashokit/
│   │   ├── entities/ContactsEntity.java
│   │   ├── repo/ContactsRepo.java
│   │   ├── services/EmployeeService.java
│   │   └── Springbootdatajpa4Application.java
```

---

## 🧠 Concepts Explained

### JpaRepository

`JpaRepository` is a pre-defined interface in Spring Data JPA that supports:

- CRUD operations
- Pagination
- Sorting
- Query By Example (QBE)

**Hierarchy:**
```
JpaRepository
   ↳ PagingAndSortingRepository
       ↳ CrudRepository
```

### Why Wrapper Classes?

Spring Data JPA works with `null` values to determine whether a field should be included in the query filter. Primitive types (like `int`, `long`) can’t hold `null`, so **always use wrapper classes (`Integer`, `Long`)** in entity classes.

### What If You Don’t Pass Any Data in Object for QBE?

If no data is passed to the object and it is passed to `Example.of(obj)`, **all records** are fetched from the database.

---

## 🛠 Entity Class

```java
@Entity
@Table(name = "CONTCTS_MASTER")
public class ContactsEntity {
    @Id
    @Column(name = "Contact_Id")
    private Integer contactId;

    @Column(name = "Contact_Name")
    private String contactName;

    @Column(name = "Contact_Number")
    private Long contactNumber;

    // Getters, Setters, toString()
}
```

---

## 📊 Pagination

Pagination is the process of dividing records into manageable pages.

Example:
```java
public void getEmps(int pageNum) {
    Pageable page = PageRequest.of(pageNum - 1, pageSize);
    Page<ContactsEntity> emps = repo.findAll(page);
    emps.get().forEach(System.out::println);
}
```

`PageRequest.of(pageNum - 1, size)` is used since pages are 0-indexed.

---

## 🔍 Query By Example (QBE)

Used to construct dynamic queries based on entity data.

```java
public void getEmpsWithQBE(ContactsEntity emp) {
    Example<ContactsEntity> empEx = Example.of(emp);
    List<ContactsEntity> emps = repo.findAll(empEx);
    emps.forEach(System.out::println);
}
```

**Examples:**
- If `emp.setContactName("kiran")` is given, the SQL would be like:
  ```sql
  SELECT * FROM CONTCTS_MASTER WHERE contact_name = 'kiran';
  ```

- If no value is set, all rows are returned.

---

## 💡 Real World Use Case

Just like Flipkart filters products with **price ranges**, you can use **custom queries** in the repository to achieve range-based filtering, since QBE does not support greater than/less than out of the box.

```java
@Query("FROM ContactsEntity WHERE contactNumber BETWEEN :min AND :max")
List<ContactsEntity> findByContactNumberRange(Long min, Long max);
```

---

## 🧬 Main Class

```java
@SpringBootApplication
public class Springbootdatajpa4Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Springbootdatajpa4Application.class, args);
        EmployeeService e = run.getBean(EmployeeService.class);

        // Example: QBE with Name Filter
        ContactsEntity c = new ContactsEntity();
        c.setContactName("kiran");
        e.getEmpsWithQBE(c);
    }
}
```

---

## 🧾 Summary

| Feature         | Support via           |
|----------------|-----------------------|
| CRUD            | CrudRepository        |
| Sorting         | Sort.by()             |
| Pagination      | PageRequest.of()      |
| QBE (Dynamic)   | Example.of()          |
| Custom Queries  | @Query annotation     |

---

## 📌 Best Practices

- Always use **wrapper classes** in Entity fields.
- Use **QBE for equality checks**; use **@Query for range or complex conditions**.
- Avoid fetching all records at once — **always prefer pagination**.

---

## ✨ Next Steps

- Add filtering using HQL and native SQL with `@Query`.
- Implement price range filters like e-commerce platforms.
- Practice complex QBE conditions using `ExampleMatcher`.

