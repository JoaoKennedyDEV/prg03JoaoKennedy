/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.util;

/**
 *
 * @author kennedy
 */

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class JPAUtil {

     private static final String UNIDADE = "com.mycompany_prg03joaokennedysmarcelino_jar_1.0-SNAPSHOTPU";
    
    private static final  EntityManagerFactory FACTORY = criarFactory();

    private JPAUtil() {
    }

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
    
     private static EntityManagerFactory criarFactory() {
        Properties arquivo = lerApplicationProperties();

        Map<String, String> config = new HashMap<>();
        config.put("jakarta.persistence.jdbc.url", arquivo.getProperty("spring.datasource.url"));
        config.put("jakarta.persistence.jdbc.user", arquivo.getProperty("spring.datasource.username"));
        config.put("jakarta.persistence.jdbc.password", arquivo.getProperty("spring.datasource.password"));
        config.put("hibernate.hbm2ddl.auto", arquivo.getProperty("spring.jpa.hibernate.ddl-auto"));

        return Persistence.createEntityManagerFactory(UNIDADE, config);
    }

    private static Properties lerApplicationProperties() {
        Properties propriedades = new Properties();
        try (InputStream entrada = JPAUtil.class.getResourceAsStream("/application.properties")) {
            if (entrada == null) {
                throw new IllegalStateException("application.properties não encontrado");
            }
            propriedades.load(entrada);
        } catch (IOException e) {
            throw new IllegalStateException("Erro ao ler application.properties", e);
        }
        return propriedades;
    }
}
