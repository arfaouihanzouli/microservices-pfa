package com.pfa.microserviceoffers;

import com.pfa.microserviceoffers.models.Offre;
import com.pfa.microserviceoffers.models.Organisme;
import com.pfa.microserviceoffers.repositories.OffreRepository;
import com.pfa.microserviceoffers.repositories.OrganismeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing
@EnableFeignClients("com.pfa.microserviceoffers")
public class MicroserviceOffersApplication implements CommandLineRunner {

	@Autowired
	private OffreRepository offreRepository;

	@Autowired
	private OrganismeRepository organismeRepository;

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceOffersApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Organisme o1=new Organisme("Poulina","Agro-alimentaire");
		Organisme o2=new Organisme("Mind Engineering","Software engineering");
		organismeRepository.save(o1);
		organismeRepository.save(o2);
	}
}
