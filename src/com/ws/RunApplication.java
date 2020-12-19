package com.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
    @PropertySource(value = {"classpath:sql-negozio.properties"}, ignoreResourceNotFound = false),
    @PropertySource(value = {"classpath:sql-prodotto.properties"}, ignoreResourceNotFound = false),
    @PropertySource(value = {"classpath:sql-recapito.properties"}, ignoreResourceNotFound = false),
    @PropertySource(value = {"classpath:sql-sotto-tipo.properties"}, ignoreResourceNotFound = false),
    @PropertySource(value = {"classpath:sql-tipo.properties"}, ignoreResourceNotFound = false),
    @PropertySource(value = {"classpath:sql-magazino.properties"}, ignoreResourceNotFound = false),
    @PropertySource(value = {"classpath:sql-utente.properties"}, ignoreResourceNotFound = false),
    @PropertySource(value = {"classpath:sql-acquisto.properties"}, ignoreResourceNotFound = false),
    @PropertySource(value = {"classpath:sql-giorni-lavorativi.properties"}, ignoreResourceNotFound = false),
    @PropertySource(value = {"classpath:sql-mod-pagamento.properties"}, ignoreResourceNotFound = false)
})
public class RunApplication {
	public static void main(String[] args) {
        SpringApplication.run(RunApplication.class, args);
    }

}
