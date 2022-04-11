package uz.pdp.cinema.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.cinema.model.*;
import uz.pdp.cinema.model.enums.Gender;
import uz.pdp.cinema.model.enums.RoleEnum;
import uz.pdp.cinema.repository.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Value("${spring.sql.init.mode}")
    String initMode;


    final RowRepository rowRepository;
    final HallRepository hallRepository;
    final AttachmentRepository attachmentRepository;
    final MovieRepository movieRepository;
    final SessionDateRepository sessionDateRepository;
    final SessionTimeRepository sessionTimeRepository;
    final MovieAnnouncementRepository movieAnnouncementRepository;
    final MovieSessionRepository movieSessionRepository;
    //final PasswordEncoder passwordEncoder;
    final RoleRepository roleRepository;
    final PermissionRepository permissionRepository;

    public DataLoader(
            RowRepository rowRepository,
            HallRepository hallRepository,
            AttachmentRepository attachmentRepository,
            MovieRepository movieRepository,
            SessionDateRepository sessionDateRepository,
            SessionTimeRepository sessionTimeRepository,
            MovieAnnouncementRepository movieAnnouncementRepository,
            MovieSessionRepository movieSessionRepository,
           /* PasswordEncoder passwordEncoder,*/ RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.rowRepository = rowRepository;
        this.hallRepository = hallRepository;
        this.attachmentRepository = attachmentRepository;
        this.movieRepository = movieRepository;
        this.sessionDateRepository = sessionDateRepository;
        this.sessionTimeRepository = sessionTimeRepository;
        this.movieAnnouncementRepository = movieAnnouncementRepository;
        this.movieSessionRepository = movieSessionRepository;
       // this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {

            // HALL OBJ CREATE QILINADI
            Hall zal1 = new Hall("Zal 1");
            Hall zal2 = new Hall("Zal 2");
            Hall zal3Vip = new Hall("Zal 3 VIP", 20.0);

            //ROW LIST CREATE QILINADI
            List<Row> rowList1 = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                rowList1.add(new Row(i + 1, zal1));
            }
            List<Row> rowList2 = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                rowList2.add(new Row(i + 1, zal2));
            }
            List<Row> rowList3 = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                rowList3.add(new Row(i + 1, zal3Vip));
            }

            zal1.setRows(rowList1);
            zal2.setRows(rowList2);
            zal3Vip.setRows(rowList3);

            // ZAL1, ZAL2, ZAL3VIP DB GA SAQLANADI
            hallRepository.save(zal1);
            hallRepository.save(zal2);
            hallRepository.save(zal3Vip);


            //attachment img
            Attachment movieImg = attachmentRepository.save(new Attachment("movieImg", "image/jpg", 100000L));
            // MOVIES

            Movie movie1 = new Movie("Batman", "dsgagadsgasgasdg", 120, 50000, movieImg, movieImg, LocalDate.now(), 10000000.0, null, 50.0
            );
            Movie movie2 = new Movie("Spiderman", "zxcvzxcv cbvxvxcbxxcv dgfshdfghdfghfg", 110, 40000, movieImg, movieImg, LocalDate.now(), 9000000.0, null, 40.0);
            Movie movie3 = new Movie("Superman", "xzcvzcx teyrtyuru bxcxbvcx", 90, 45000, movieImg, movieImg, LocalDate.now(), 12000000.0, null, 60.0);
            movieRepository.save(movie1);
            Movie spiderman = movieRepository.save(movie2);
            Movie superman = movieRepository.save(movie3);

            // SESSION DATES
            SessionDate march17 = new SessionDate(LocalDate.of(2022, 3, 17));

            SessionDate march18 = new SessionDate(LocalDate.of(2022, 3, 18));
            SessionDate march19 = new SessionDate(LocalDate.of(2022, 3, 19));
            sessionDateRepository.save(march17);
            sessionDateRepository.save(march18);
            sessionDateRepository.save(march19);

            //SESSION TIMES
            SessionTime hour11 = new SessionTime(LocalTime.of(11, 0));
            SessionTime hour13 = new SessionTime(LocalTime.of(13, 0));
            SessionTime hour15 = new SessionTime(LocalTime.of(15, 0));
            SessionTime hour18 = new SessionTime(LocalTime.of(18, 0));
            sessionTimeRepository.save(hour11);
            sessionTimeRepository.save(hour13);
            sessionTimeRepository.save(hour15);
            sessionTimeRepository.save(hour18);

            //MOVIE ANNOUNCEMENTS
            MovieAnnouncement batmanAfisha = movieAnnouncementRepository.save(
                    new MovieAnnouncement(movie1, true));
            MovieAnnouncement spidermanAfisha = movieAnnouncementRepository.save(
                    new MovieAnnouncement(spiderman, true));
            MovieAnnouncement supermanAfisha = movieAnnouncementRepository.save(
                    new MovieAnnouncement(superman, true));

            // MOVIE SESSIONS

            movieSessionRepository.save(
                    new MovieSession(
                            batmanAfisha,
                            zal1,
                            march18,
                            hour11,
                            hour13
                    )
            );
            movieSessionRepository.save(
                    new MovieSession(
                            batmanAfisha,
                            zal1,
                            march18,
                            hour15,
                            hour18
                    )
            );
            movieSessionRepository.save(
                    new MovieSession(
                            spidermanAfisha,
                            zal3Vip,
                            march18,
                            hour15,
                            hour18
                    )
            );

            movieSessionRepository.save(
                    new MovieSession(
                            spidermanAfisha,
                            zal2,
                            march19,
                            hour11,
                            hour13
                    )
            );
            movieSessionRepository.save(
                    new MovieSession(
                            spidermanAfisha,
                            zal2,
                            march19,
                            hour15,
                            hour18
                    )
            );

            movieSessionRepository.save(
                    new MovieSession(
                            supermanAfisha,
                            zal3Vip,
                            march19,
                            hour11,
                            hour13
                    )
            );


            Role role_admin = roleRepository.save(new Role("ROLE_ADMIN", null));
            Role role_user = roleRepository.save(new Role("ROLE_USER", null));


            Set<Role> roles = new HashSet<>();

            roles.add(role_admin);
            roles.add(role_user);

//            userRepository.save(new User(
//                    "Asil",
//                    "asil",
//                    passwordEncoder.encode("7"),
//                    LocalDate.of(2001, 8, 8),
//                    Gender.MALE,
//                    roles));
    //            userRepository.save(new User("Asil","asil", passwordEncoder.encode("7"),
    //                    LocalDate.of(2022,4,1),Gender.MALE,roles));
        }
    }
}
