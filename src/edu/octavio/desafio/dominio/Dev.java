package edu.octavio.desafio.dominio;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Dev {
    private String nome;
    private Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();

    public void InscreverBootcamp(Bootcamp bootcamp) {}

    public void progredir() {}

    public void calcularTotalXp() {}

    public String getNome() {
        return nome;
    }

    public Set<Conteudo> getConteudosInscritos() {
        return conteudosInscritos;
    }

    public Set<Conteudo> getConteudosConcluidos() {
        return conteudosConcluidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dev dev = (Dev) o;
        return Objects.equals(nome, dev.getNome()) && Objects.equals(conteudosInscritos, dev.getConteudosInscritos()) && Objects.equals(conteudosConcluidos, dev.getConteudosConcluidos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, conteudosInscritos, conteudosConcluidos);
    }
}
