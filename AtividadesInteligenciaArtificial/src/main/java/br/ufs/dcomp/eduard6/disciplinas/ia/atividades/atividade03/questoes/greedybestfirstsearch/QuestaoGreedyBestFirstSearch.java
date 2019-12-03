package br.ufs.dcomp.eduard6.disciplinas.ia.atividades.atividade03.questoes.greedybestfirstsearch;

import java.util.function.ToDoubleFunction;

import aima.core.agent.Action;
import aima.core.agent.Agent;
import aima.core.agent.impl.DynamicPercept;
import aima.core.environment.eightpuzzle.BidirectionalEightPuzzleProblem;
import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.eightpuzzle.EightPuzzleFunctions;
import aima.core.environment.map.ExtendableMap;
import aima.core.environment.map.MapEnvironment;
import aima.core.environment.map.MapFunctions;
import aima.core.environment.map.MoveToAction;
import aima.core.environment.map.SimpleMapAgent;
import aima.core.environment.map.SimplifiedRoadMapOfRomania;
import aima.core.search.agent.SearchAgent;
import aima.core.search.framework.Metrics;
import aima.core.search.framework.Node;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.framework.qsearch.QueueSearch;
import aima.core.search.informed.EvaluationFunction;
import aima.core.search.informed.GreedyBestFirstSearch;
import br.ufs.dcomp.eduard6.disciplinas.ia.atividades.atividade03.QuestaoaAtividade03Base;

public class QuestaoGreedyBestFirstSearch extends QuestaoaAtividade03Base{

	@Override
	public Metrics problemaDoCaixeiroViajante() {
		// TODO Auto-generated method stub
		ExtendableMap map = new ExtendableMap();
		SimplifiedRoadMapOfRomania.initMap(map);
		MapEnvironment env = new MapEnvironment(map);

		String agentLoc = SimplifiedRoadMapOfRomania.ARAD;
		String destination = SimplifiedRoadMapOfRomania.BUCHAREST;
		
		GreedyBestFirstSearch<String, MoveToAction> search;
		
		EvaluationFunction<String, MoveToAction> fn = new EvaluationFunction<String, MoveToAction>() {

			@Override
			public double applyAsDouble(Node<String, MoveToAction> value) {
				// TODO Auto-generated method stub
				return MapFunctions. getSLD(value.getState(), destination, map);
			}
		};
		
		QueueSearch<String, MoveToAction> fila = (new GraphSearch<>());
		
		search = new GreedyBestFirstSearch<String, MoveToAction>(fila, fn);
		
		Agent<DynamicPercept, MoveToAction> agent;
		agent = new SimpleMapAgent(map, search, destination);

		env.addAgent(agent, agentLoc);
		env.stepUntilDone();
		return search.getMetrics();
		
	}

	@Override
	public Metrics problemaDas8Rainhas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Metrics problemaDoQuebraCabeca() {
		// TODO Auto-generated method stub
		Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(QuestaoaAtividade03Base.QUEBRA_CABECA_TABULEIRO);
		GreedyBestFirstSearch<EightPuzzleBoard, Action> search;
		
		EvaluationFunction <EightPuzzleBoard, Action> fn = new EvaluationFunction<EightPuzzleBoard, Action>() {
			@Override
			public double applyAsDouble(Node<EightPuzzleBoard, Action> value) {
				// TODO Auto-generated method stub
				return EightPuzzleFunctions.getManhattanDistance(value);
			}
		};
		
		QueueSearch<EightPuzzleBoard, Action> fila = (new GraphSearch<>());
		search = new GreedyBestFirstSearch<EightPuzzleBoard, Action>(fila, fn);
		
		search.findActions(problem);
		
		return search.getMetrics();
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return "3.5.1 Greedy best-first search";
	}

}
