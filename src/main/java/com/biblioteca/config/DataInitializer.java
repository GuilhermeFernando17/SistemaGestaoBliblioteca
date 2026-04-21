package com.biblioteca.config;

import com.biblioteca.model.*;
import com.biblioteca.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired private AdminRepository    adminRepo;
    @Autowired private UsuarioRepository  usuarioRepo;
    @Autowired private MaterialRepository materialRepo;
    @Autowired private EmprestimoRepository emprestimoRepo;
    @Autowired private ReservaRepository  reservaRepo;
    @Autowired private BCryptPasswordEncoder encoder;

    @Override
    public void run(String... args) {
        // Admin
        if (adminRepo.findByEmail("admin@gmail.com").isEmpty()) {
            Admin admin = new Admin();
            admin.setNome("Administrador");
            admin.setEmail("admin@gmail.com");
            admin.setSenha(encoder.encode("admin123"));
            adminRepo.save(admin);
        }

        // Usuários seed
        Usuario u1 = usuarioRepo.findByEmail("gui@gmail.com").orElseGet(() -> {
            Usuario u = new Usuario();
            u.setNome("Guilherme F");
            u.setEmail("gui@gmail.com");
            u.setTelefone("+551199989887");
            u.setStatus("Ativo");
            return usuarioRepo.save(u);
        });

        Usuario u2 = usuarioRepo.findByEmail("maik@gmail.com").orElseGet(() -> {
            Usuario u = new Usuario();
            u.setNome("Maikon H");
            u.setEmail("maik@gmail.com");
            u.setTelefone("+551199989887");
            u.setStatus("Inativo");
            return usuarioRepo.save(u);
        });

        // Materiais seed
        Material m1 = materialRepo.findByIsbn("463546121").orElseGet(() -> {
            Material m = new Material();
            m.setTitulo("O Senhor dos Aneis");
            m.setAutor("Tolkien");
            m.setTipo("Livro");
            m.setIsbn("463546121");
            return materialRepo.save(m);
        });

        Material m2 = materialRepo.findByIsbn("778555321").orElseGet(() -> {
            Material m = new Material();
            m.setTitulo("Turma da Monica");
            m.setAutor("M. Souza");
            m.setTipo("Revista");
            m.setIsbn("778555321");
            return materialRepo.save(m);
        });

        // Empréstimos seed (apenas se não existir nenhum entre esses usuários/materiais)
        boolean emp1Existe = emprestimoRepo.findByUsuario_Id(u2.getId()).stream()
                .anyMatch(e -> e.getMaterial().getId().equals(m1.getId()));
        if (!emp1Existe) {
            Emprestimo e1 = new Emprestimo();
            e1.setUsuario(u2);
            e1.setMaterial(m1);
            e1.setDataEmprestimo(LocalDate.now().minusDays(1));
            e1.setDataDevolucao(LocalDate.now().plusDays(6));
            e1.setStatus("Ativo");
            emprestimoRepo.save(e1);
        }

        boolean emp2Existe = emprestimoRepo.findByUsuario_Id(u1.getId()).stream()
                .anyMatch(e -> e.getMaterial().getId().equals(m2.getId()));
        if (!emp2Existe) {
            Emprestimo e2 = new Emprestimo();
            e2.setUsuario(u1);
            e2.setMaterial(m2);
            e2.setDataEmprestimo(LocalDate.now().minusDays(2));
            e2.setDataDevolucao(LocalDate.now().plusDays(5));
            e2.setStatus("Ativo");
            emprestimoRepo.save(e2);
        }

        // Reserva seed
        boolean reservaExiste = reservaRepo.findByUsuario_Id(u1.getId()).stream()
                .anyMatch(r -> r.getMaterial().getId().equals(m1.getId()));
        if (!reservaExiste) {
            Reserva r = new Reserva();
            r.setUsuario(u1);
            r.setMaterial(m1);
            r.setDataReserva(LocalDate.now());
            r.setDataEmprestimoPrevisto(LocalDate.now().plusDays(7));
            r.setStatus("Pendente");
            reservaRepo.save(r);
        }
    }
}
