package br.ufs.dcomp.eduard6.disciplinas.ia.atividades.topicrandomizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TopicRandomizer {
	/**
	 * @author Eduardo Fillipe da Silva Reis
	 * Classe que divide uma dada atividade entre os integrantes
	 * do grupo.
	 */

	enum Alunos{
        EDUARDO(), FELIPE(), MARIANA();
        public String topico;

        private Alunos(String topico) {
            this.topico = topico;
        }
        
        private Alunos() {}
        
        @Override
        public String toString() {
            return this.name() + ": " + this.topico;
        }
    }
    
    public static void main(String[] args) {
        Random rGenerator = new Random();
        ArrayList<String> topicos = new ArrayList<>(Arrays.asList("5.5","5.6","5.7 e 5.8"));
        for (Alunos aluno : Alunos.values()) {
            int t = rGenerator.nextInt(topicos.size());
            aluno.topico = topicos.get(t);
            topicos.remove(t);
        }
        Arrays.asList(Alunos.values()).forEach(s -> {
        	System.out.println(s);
        });
    }

}
