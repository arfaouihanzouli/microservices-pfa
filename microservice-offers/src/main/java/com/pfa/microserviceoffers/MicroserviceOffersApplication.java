package com.pfa.microserviceoffers;

import com.pfa.microserviceoffers.models.Offre;
import com.pfa.microserviceoffers.models.Organisme;
import com.pfa.microserviceoffers.models.enumuration.Niveau;
import com.pfa.microserviceoffers.models.enumuration.TypeOffre;
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

import java.text.SimpleDateFormat;

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

		Organisme o1=organismeRepository.save(new Organisme("Poulina","Agro-alimentaire"));
		Organisme o2=organismeRepository.save(new Organisme("Mind Engineering","Software engineering"));
		Organisme o3=organismeRepository.save(new Organisme("Sofrecom","Devops"));

		Offre of1=new Offre("Développeur JAVA H/F",
				new SimpleDateFormat("yyyy-MM-dd").parse("31-03-2020"),
				new SimpleDateFormat("yyyy-MM-dd").parse("07-04-2020")
				,"Développeur JAVA","Tunis", TypeOffre.CDD,
				"Cette offre nécessite l'expérience en JAVA",2, Niveau.Licence,
				true, (long) 2,o1);

		Offre of2=new Offre("Développeur Angular 5 H/F",
				new SimpleDateFormat("yyyy-MM-dd").parse("30-03-2020"),
				new SimpleDateFormat("yyyy-MM-dd").parse("10-05-2020")
				,"Développeur Angular","Ariana", TypeOffre.TempsPartiel,
				"Cette offre nécessite l'expérience en Angular",1, Niveau.Mastere,
				true, (long) 2,o2);

		Offre of3=new Offre("Développeur Fullstack H/F",
				new SimpleDateFormat("yyyy-MM-dd").parse("10-04-2020"),
				new SimpleDateFormat("yyyy-MM-dd").parse("21-05-2020")
				,"Développeur Fullstack Spring boot et Angular 5+","Mannouba", TypeOffre.TempsPlein,
				"Cette offre nécessite l'expérience en JAVA Spring boot et Angular",4, Niveau.Ingenieur,
				true, (long) 2,o3);

		Offre of4=new Offre("Designer H/F",
				new SimpleDateFormat("yyyy-MM-dd").parse("15-04-2020"),
				new SimpleDateFormat("yyyy-MM-dd").parse("29-04-2020")
				,"Web and mobile Designer","Ben Arous", TypeOffre.TravailTemporaire,
				"Cette offre nécessite l'expérience en Photoshop et Adobe ...",3, Niveau.Autre,
				true, (long) 2,o1);
	}
}
