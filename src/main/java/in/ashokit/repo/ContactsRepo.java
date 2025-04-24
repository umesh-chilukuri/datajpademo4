package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entities.ContactsEntity;

public interface ContactsRepo extends JpaRepository<ContactsEntity, Integer> {

}
