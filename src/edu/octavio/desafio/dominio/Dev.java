package edu.octavio.desafio.dominio;

import java.util.*;

public class Dev implements Comparable<Dev> {
    private String nome;
    private final Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private final Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();
    private static int SEQUENCIAL = 0;
    private final int id;

    public Dev() {
        id = SEQUENCIAL++;
    }

    public void inscreverBootcamp(Bootcamp bootcamp) {
        conteudosInscritos.addAll(bootcamp.getConteudos());
        bootcamp.getDevsInscritos().add(this);
    }

    public void progredir() {
        Optional<Conteudo> conteudo = conteudosInscritos.stream().findFirst();
        if (conteudo.isPresent()) {
            conteudosConcluidos.add(conteudo.get());
            conteudosInscritos.remove(conteudo.get());
        } else {
            System.err.println("Você não está matriculado em nenhum conteúdo!");
        }
    }

    public double calcularTotalXp() {
        return conteudosConcluidos.stream().mapToDouble(Conteudo::calcularXp).sum();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        return Objects.equals(id, dev.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public int compareTo(Dev dev) {
        return nome.compareTo(dev.getNome());
    }

    public double porcentagemConcluidaBootcamp(Bootcamp bootcamp) {
        if (!bootcamp.getDevsInscritos().contains(this))
            throw new RuntimeException("O Dev " + nome + " não está inscrito no bootcamp " + bootcamp.getNome());
        return (double) bootcamp.getConteudos().stream().filter(c -> conteudosConcluidos.contains(c)).toList().size() / bootcamp.getConteudos().size() * 100;
    }

    @Override
    public String toString() {
        return "Dev{" +
                "nome='" + nome + '\'' +
                ", conteudosInscritos=" + conteudosInscritos +
                ", conteudosConcluidos=" + conteudosConcluidos +
                '}';
    }

    public int getId() {
        return id;
    }
}

class CompararPorXp implements Comparator<Dev> {
    @Override
    public int compare(Dev d1, Dev d2) {
        return Double.compare(d1.calcularTotalXp(), d2.calcularTotalXp());
    }
}
