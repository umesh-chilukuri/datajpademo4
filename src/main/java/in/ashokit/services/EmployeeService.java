package in.ashokit.services;

import java.util.List;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import in.ashokit.entities.ContactsEntity;
import in.ashokit.repo.ContactsRepo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;


@Service
public class EmployeeService {
	
	private static final int pageSize=2;
	private ContactsRepo repo;
	
	public EmployeeService(ContactsRepo repo) {
		this.repo=repo;
		System.out.println("service class constructer is called");
	}
	
	public void getEmpsWithQBE(ContactsEntity emp) {
		Example<ContactsEntity> empEx = Example.of(emp);
		List<ContactsEntity> emps = repo.findAll(empEx);
		emps.forEach(System.out::println);
	}
	
	
	public void getEmps(int pageNum) {
		Pageable page = PageRequest.of(pageNum-1, pageSize);
		Page<ContactsEntity> emps= repo.findAll(page);
		emps.get().forEach(System.out::println);
	}
	
	
	

}
