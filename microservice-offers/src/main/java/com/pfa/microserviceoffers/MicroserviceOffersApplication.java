package com.pfa.microserviceoffers;

import com.pfa.microserviceoffers.models.Offre;
import com.pfa.microserviceoffers.models.Organisme;
import com.pfa.microserviceoffers.models.enumuration.Niveau;
import com.pfa.microserviceoffers.models.enumuration.TypeOffre;
import com.pfa.microserviceoffers.repositories.OffreRepository;
import com.pfa.microserviceoffers.repositories.OrganismeRepository;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.TimeZone;

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
	@Bean
	public ConfigurableServletWebServerFactory webServerFactory() {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
			@Override
			public void customize(Connector connector) {
				connector.setProperty("relaxedQueryChars", "é");
				connector.setAttribute("relaxedPathChars", "é");
			}
		});
		return factory;
	}
	@Override
	public void run(String... args) throws Exception {

		Organisme o1=organismeRepository.save(new Organisme("Poulina","Agro-alimentaire"));
		Organisme o2=organismeRepository.save(new Organisme("Mind Engineering","Software engineering"));
		Organisme o3=organismeRepository.save(new Organisme("Sofrecom","Devops"));

		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
		LocalDate d=LocalDate.parse("2015-10-16");
		Offre of1=new Offre("Développeur JAVA H/F",
				LocalDate.parse("2020-03-31"),
				LocalDate.parse("2020-07-04")
				,"Développeur JAVA","Tunis", TypeOffre.CDD,
				"Cette offre nécessite l'expérience en JAVA",2, Niveau.Licence,
				true, (long) 2,o1);

		Offre of2=new Offre("Développeur Angular 5 H/F",
				LocalDate.parse("2020-03-30"),
				LocalDate.parse("2020-05-10")
				,"Développeur Angular","Ariana", TypeOffre.TempsPartiel,
				"Cette offre nécessite l'expérience en Angular",1, Niveau.Mastere,
				true, (long) 2,o2);

		Offre of3=new Offre("Développeur Fullstack H/F",
				LocalDate.parse("2020-04-10"),
				LocalDate.parse("2020-05-21")
				,"Développeur Fullstack Spring boot et Angular 5+","Mannouba", TypeOffre.TempsPlein,
				"Cette offre nécessite l'expérience en JAVA Spring boot et Angular",4, Niveau.Ingenieur,
				true, (long) 2,o3);

		Offre of4=new Offre("Designer H/F",
				LocalDate.parse("2020-04-15"),
				LocalDate.parse("2020-04-29")
				,"Web and mobile Designer","Ben Arous", TypeOffre.TravailTemporaire,
				"Cette offre nécessite l'expérience en Photoshop et Adobe ...",3, Niveau.Autre,
				true, (long) 2,o1);
		this.offreRepository.save(of1);
		this.offreRepository.save(of2);
		this.offreRepository.save(of3);
		this.offreRepository.save(of4);
	}
}
