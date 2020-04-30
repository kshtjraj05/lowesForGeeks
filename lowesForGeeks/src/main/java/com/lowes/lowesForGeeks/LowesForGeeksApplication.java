package com.lowes.lowesForGeeks;

import com.lowes.lowesForGeeks.model.Member;
import com.lowes.lowesForGeeks.model.Organization;
import com.lowes.lowesForGeeks.repository.EventRepository;
import com.lowes.lowesForGeeks.repository.MemberRepository;
import com.lowes.lowesForGeeks.repository.OrganizationRepository;
import com.lowes.lowesForGeeks.service.MemberService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LowesForGeeksApplication {


	public static void main(String[] args) {
		SpringApplication.run(LowesForGeeksApplication.class, args);
	}

}
