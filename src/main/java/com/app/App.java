package com.app;

import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.config.ConfigLoader;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

/*
    <dependency>
        <groupId>org.simplejavamail</groupId>
        <artifactId>simple-java-mail</artifactId>
        <version>7.9.1</version>
    </dependency>
*/

public class App {
    public static void main(String[] args) {
        ConfigLoader
                .loadProperties(
                        "email-config.properties",
                        true);

        var email = EmailBuilder
                .startingBlank()
                .withSubject("Test")
                .withHTMLText("<h1>Hello world</h1>")
                .to("programowanie.krzysiek@gmail.com")
                .buildEmail();

        var mailer = MailerBuilder
                .withTransportStrategy(
                        TransportStrategy.SMTPS)
                .async()
                .buildMailer();

        mailer
                .sendMail(email)
                .thenAccept(res ->
                        mailer.shutdownConnectionPool());

    }
}
