package uz.pdp.cinema.projection;

import java.sql.Date;

public interface CustomMovie {
    Integer getId();

    String getTitle();

    Date getReleaseDate();

    Integer getCoverImgId();

}
