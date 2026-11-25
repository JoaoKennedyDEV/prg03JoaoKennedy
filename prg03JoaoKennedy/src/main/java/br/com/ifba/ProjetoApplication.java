package br.com.ifba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.ifba.login.view.Login;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProjetoApplication {
    
    public static void main(String[] args) {
        ativarNimbus();

        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(ProjetoApplication.class)
                        .headless(false)
                        .run(args);

        Login tela = context.getBean(Login.class);
        java.awt.EventQueue.invokeLater(() -> tela.setVisible(true));
    }

    private static void ativarNimbus() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}

