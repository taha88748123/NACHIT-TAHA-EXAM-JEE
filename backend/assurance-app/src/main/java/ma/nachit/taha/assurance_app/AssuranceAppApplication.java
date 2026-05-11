package ma.nachit.taha.assurance_app;

import ma.nachit.taha.assurance_app.entities.*;
import ma.nachit.taha.assurance_app.enums.*;
import ma.nachit.taha.assurance_app.repositories.ClientRepository;
import ma.nachit.taha.assurance_app.repositories.ContratRepository;
import ma.nachit.taha.assurance_app.repositories.PaiementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class AssuranceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssuranceAppApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ClientRepository clientRepository,
                            ContratRepository contratRepository,
                            PaiementRepository paiementRepository) {
        return args -> {

            List<Client> clients = List.of(
                    new Client(null, "Hassan El Amrani", "hassan@mail.com", null),
                    new Client(null, "Sara Bennani", "sara@mail.com", null),
                    new Client(null, "Karim Tazi", "karim@mail.com", null),
                    new Client(null, "Fatima Idrissi", "fatima@mail.com", null),
                    new Client(null, "Omar Chraibi", "omar@mail.com", null)
            );
            clientRepository.saveAll(clients);

            ContratAutomobile c1 = new ContratAutomobile();
            c1.setDateSouscription(LocalDate.of(2024, 1, 15));
            c1.setStatut(StatutContrat.VALIDE);
            c1.setDateValidation(LocalDate.of(2024, 1, 20));
            c1.setMontantCotisation(450.0);
            c1.setDureeContrat(12);
            c1.setTauxCouverture(80.0);
            c1.setNumImmatriculation("12345-A-6");
            c1.setMarque("Renault");
            c1.setModele("Clio");
            c1.setClient(clients.get(0));

            ContratAutomobile c2 = new ContratAutomobile();
            c2.setDateSouscription(LocalDate.of(2024, 3, 5));
            c2.setStatut(StatutContrat.EN_COURS);
            c2.setMontantCotisation(620.0);
            c2.setDureeContrat(24);
            c2.setTauxCouverture(90.0);
            c2.setNumImmatriculation("78901-B-12");
            c2.setMarque("Peugeot");
            c2.setModele("208");
            c2.setClient(clients.get(1));

            ContratHabitation c3 = new ContratHabitation();
            c3.setDateSouscription(LocalDate.of(2023, 11, 10));
            c3.setStatut(StatutContrat.VALIDE);
            c3.setDateValidation(LocalDate.of(2023, 11, 12));
            c3.setMontantCotisation(300.0);
            c3.setDureeContrat(12);
            c3.setTauxCouverture(70.0);
            c3.setTypeLogement(TypeLogement.APPARTEMENT);
            c3.setAdresse("Rue Atlas, Casablanca");
            c3.setSuperficie(85.0);
            c3.setClient(clients.get(0));

            ContratHabitation c4 = new ContratHabitation();
            c4.setDateSouscription(LocalDate.of(2024, 5, 1));
            c4.setStatut(StatutContrat.VALIDE);
            c4.setDateValidation(LocalDate.of(2024, 5, 3));
            c4.setMontantCotisation(550.0);
            c4.setDureeContrat(36);
            c4.setTauxCouverture(85.0);
            c4.setTypeLogement(TypeLogement.MAISON);
            c4.setAdresse("Avenue Hassan II, Rabat");
            c4.setSuperficie(180.0);
            c4.setClient(clients.get(2));

            ContratSante c5 = new ContratSante();
            c5.setDateSouscription(LocalDate.of(2024, 2, 1));
            c5.setStatut(StatutContrat.VALIDE);
            c5.setDateValidation(LocalDate.of(2024, 2, 5));
            c5.setMontantCotisation(800.0);
            c5.setDureeContrat(12);
            c5.setTauxCouverture(95.0);
            c5.setNiveauCouverture(NiveauCouverture.PREMIUM);
            c5.setNombrePersonnesCouvertes(4);
            c5.setClient(clients.get(3));

            ContratSante c6 = new ContratSante();
            c6.setDateSouscription(LocalDate.of(2024, 4, 12));
            c6.setStatut(StatutContrat.EN_COURS);
            c6.setMontantCotisation(350.0);
            c6.setDureeContrat(12);
            c6.setTauxCouverture(60.0);
            c6.setNiveauCouverture(NiveauCouverture.BASIQUE);
            c6.setNombrePersonnesCouvertes(1);
            c6.setClient(clients.get(4));

            ContratAutomobile c7 = new ContratAutomobile();
            c7.setDateSouscription(LocalDate.of(2023, 7, 20));
            c7.setStatut(StatutContrat.RESILIE);
            c7.setDateValidation(LocalDate.of(2023, 7, 22));
            c7.setMontantCotisation(480.0);
            c7.setDureeContrat(12);
            c7.setTauxCouverture(75.0);
            c7.setNumImmatriculation("55555-C-7");
            c7.setMarque("Dacia");
            c7.setModele("Logan");
            c7.setClient(clients.get(2));

            ContratHabitation c8 = new ContratHabitation();
            c8.setDateSouscription(LocalDate.of(2024, 6, 15));
            c8.setStatut(StatutContrat.EN_COURS);
            c8.setMontantCotisation(700.0);
            c8.setDureeContrat(24);
            c8.setTauxCouverture(80.0);
            c8.setTypeLogement(TypeLogement.LOCAL_COMMERCIAL);
            c8.setAdresse("Boulevard Mohammed V, Casablanca");
            c8.setSuperficie(120.0);
            c8.setClient(clients.get(1));

            ContratSante c9 = new ContratSante();
            c9.setDateSouscription(LocalDate.of(2024, 1, 5));
            c9.setStatut(StatutContrat.VALIDE);
            c9.setDateValidation(LocalDate.of(2024, 1, 8));
            c9.setMontantCotisation(500.0);
            c9.setDureeContrat(12);
            c9.setTauxCouverture(75.0);
            c9.setNiveauCouverture(NiveauCouverture.INTERMEDIAIRE);
            c9.setNombrePersonnesCouvertes(2);
            c9.setClient(clients.get(0));

            ContratAutomobile c10 = new ContratAutomobile();
            c10.setDateSouscription(LocalDate.of(2024, 8, 1));
            c10.setStatut(StatutContrat.VALIDE);
            c10.setDateValidation(LocalDate.of(2024, 8, 4));
            c10.setMontantCotisation(900.0);
            c10.setDureeContrat(36);
            c10.setTauxCouverture(95.0);
            c10.setNumImmatriculation("99999-D-10");
            c10.setMarque("Hyundai");
            c10.setModele("Tucson");
            c10.setClient(clients.get(3));

            List<Contrat> contrats = List.of(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10);
            contratRepository.saveAll(contrats);

            for (Contrat contrat : contrats) {
                Paiement p1 = new Paiement();
                p1.setDate(contrat.getDateSouscription().plusMonths(1));
                p1.setMontant(contrat.getMontantCotisation() / 12);
                p1.setType(TypePaiement.MENSUALITE);
                p1.setContrat(contrat);

                Paiement p2 = new Paiement();
                p2.setDate(contrat.getDateSouscription().plusMonths(2));
                p2.setMontant(contrat.getMontantCotisation() / 12);
                p2.setType(TypePaiement.MENSUALITE);
                p2.setContrat(contrat);

                paiementRepository.save(p1);
                paiementRepository.save(p2);
            }

            System.out.println("===========================================");
            System.out.println("Donnees inserees :");
            System.out.println("  Clients   : " + clientRepository.count());
            System.out.println("  Contrats  : " + contratRepository.count());
            System.out.println("  Paiements : " + paiementRepository.count());
            System.out.println("===========================================");
        };
    }
}
