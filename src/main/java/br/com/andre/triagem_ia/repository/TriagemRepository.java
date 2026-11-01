package br.com.andre.triagem_ia.repository;

import br.com.andre.triagem_ia.dominio.CategoriaTriagem;
import br.com.andre.triagem_ia.dominio.PrioridadeTriagem;
import br.com.andre.triagem_ia.dominio.Triagem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Optional;

@Repository
public class TriagemRepository {

    private final JdbcTemplate jdbcTemplate;

    public TriagemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Triagem> buscarPorId(Long id) {
        String sql = "SELECT id, texto, categoria, prioridade, criado_em FROM triagem WHERE id = ?";
        return jdbcTemplate.query(sql, this::mapear, id).stream().findFirst();
    }

    public Triagem salvar(String texto, CategoriaTriagem categoria, PrioridadeTriagem prioridade) {
        Long id = inserir(texto, categoria, prioridade);
        return buscarPorId(id).orElseThrow();
    }

    private Long inserir(String texto, CategoriaTriagem categoria, PrioridadeTriagem prioridade) {
        String sql = "INSERT INTO triagem (texto, categoria, prioridade) VALUES (?, ?, ?)";
        KeyHolder chaves = new GeneratedKeyHolder();

        jdbcTemplate.update(conexao -> {
            PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, texto);
            ps.setString(2, categoria.name());
            ps.setString(3, prioridade.name());
            return ps;
        }, chaves);

        return Objects.requireNonNull(chaves.getKey(), "banco nao devolveu o id gerado").longValue();
    }

    private Triagem mapear(ResultSet rs, int numeroLinha) throws SQLException {
        return new Triagem(
                rs.getLong("id"),
                rs.getString("texto"),
                CategoriaTriagem.valueOf(rs.getString("categoria")),
                PrioridadeTriagem.valueOf(rs.getString("prioridade")),
                rs.getTimestamp("criado_em").toLocalDateTime()
        );
    }
}