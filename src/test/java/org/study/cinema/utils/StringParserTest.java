package org.study.cinema.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringParserTest {

    @Test
    public void shouldCheckNameSurname() {
        boolean test1 = StringParser.checkNameSurname("Anna");
        boolean test2 = StringParser.checkNameSurname("123");
        boolean test3 = StringParser.checkNameSurname("ggg");
        boolean test4 = StringParser.checkNameSurname("Annsss11a");
        boolean test5 = StringParser.checkNameSurname("Анна");


        assertTrue("name 'Anna' isn't correct", test1);
        assertFalse("name '123' is correct", test2);
        assertFalse("name 'ggg' is correct", test3);
        assertFalse("name 'Annsss11a' is correct", test4);
        assertTrue("name 'Анна' isn't correct", test5);
    }

    @Test
    public void checkLoginPassword() {
        boolean test1 = StringParser.checkLoginPassword("anna");
        boolean test2 = StringParser.checkLoginPassword("123");
        boolean test3 = StringParser.checkLoginPassword("Annss__s11a");
        boolean test4 = StringParser.checkLoginPassword("__aa 1A");
        boolean test5 = StringParser.checkLoginPassword("Аннfgtа");

        assertTrue("login and password 'anna' isn't correct", test1);
        assertTrue("login and password '123' isn't correct", test2);
        assertTrue("login and password 'Annss__s11a' isn't correct", test3);
        assertFalse("login and password '__aa 1A' is correct", test4);
        assertTrue("login and password 'Аннfgtа' isn't correct", test5);
    }

    @Test
    public void checkEMail() {
        boolean test1 = StringParser.checkEMail("anna123@gmail.com");
        boolean test2 = StringParser.checkEMail("__anna123@gmail.com");
        boolean test3 = StringParser.checkEMail("12an/_na123@gmail.com");
        boolean test4 = StringParser.checkEMail("Anna123@gmail.com");
        boolean test5 = StringParser.checkEMail("anna123@gmail.hgh");
        boolean test6 = StringParser.checkEMail("anna123@gmailcom");
        boolean test7 = StringParser.checkEMail("anna123@gmail._com");

        assertTrue("email 'anna123@gmail.com' isn't correct", test1);
        assertTrue("email '__anna123@gmail.com' isn't correct", test2);
        assertFalse("email '12an/_na123@gmail.com' is correct", test3);
        assertTrue("email 'Anna123@gmail.com' isn't correct", test4);
        assertFalse("email 'anna123@gmail.hgh' is correct", test5);
        assertFalse("email 'anna123@gmailcom' is correct", test6);
        assertFalse("email 'anna123@gmail._com' isn correct", test7);
    }

    @Test
    public void checkMovieNameDescription() {
        boolean test1 = StringParser.checkMovieNameDescription("Avengers");
        boolean test2 = StringParser.checkMovieNameDescription("100 dalm");
        boolean test3 = StringParser.checkMovieNameDescription("_Once s");
        boolean test4 = StringParser.checkMovieNameDescription("waw_");
        boolean test5 = StringParser.checkMovieNameDescription("U: aw");
        boolean test6 = StringParser.checkMovieNameDescription("aelita");
        boolean test7 = StringParser.checkMovieNameDescription("Космок12ssАФ");

        assertTrue("name 'Avengers' isn't correct", test1);
        assertTrue("name '100 dalm' isn't correct", test2);
        assertFalse("name '_Once s' is correct", test3);
        assertFalse("name 'waw_' is correct", test4);
        assertTrue("name 'U: aw' isn't correct", test5);
        assertFalse("name 'aelita' is correct", test6);
        assertTrue("name 'Космок12ssАФ' isn't correct", test7);
    }

    @Test
    public void checkMovieDuration() {
        boolean test1 = StringParser.checkMovieDuration("123");
        boolean test2 = StringParser.checkMovieDuration("5556");
        boolean test3 = StringParser.checkMovieDuration("89");
        boolean test4 = StringParser.checkMovieDuration("980");
        boolean test5 = StringParser.checkMovieDuration("05");

        assertTrue("duration '123' isn't correct", test1);
        assertFalse("duration '5556' is correct", test2);
        assertTrue("duration '89' isn't correct", test3);
        assertFalse("duration '980' is correct", test4);
        assertFalse("duration '05' is correct", test5);
    }

    @Test
    public void checkMovieAge() {
        boolean test1 = StringParser.checkMovieAge("123");
        boolean test2 = StringParser.checkMovieAge("0");
        boolean test3 = StringParser.checkMovieAge("16");
        boolean test4 = StringParser.checkMovieAge("25");

        assertFalse("age '123' is correct", test1);
        assertTrue("age '0' isn't correct", test2);
        assertTrue("age '16' isn't correct", test3);
        assertFalse("age '25' is correct", test4);
    }
}
