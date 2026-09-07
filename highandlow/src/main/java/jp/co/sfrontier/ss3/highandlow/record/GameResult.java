package jp.co.sfrontier.ss3.highandlow.record;

import jp.co.sfrontier.ss3.highandlow.card.Card;
import jp.co.sfrontier.ss3.highandlow.game.Comparison;

/**
 * 1回の対戦結果をまとめるためのDTO
 */
public class GameResult {

	private final Card parentCard;
	private final Card childCard;
	private final Comparison prediction;
	private final Comparison actualResult;
	private final boolean win;

	public GameResult(
			Card parentCard,
			Card childCard,
			Comparison prediction,
			Comparison actualResult,
			boolean win) {

		this.parentCard = parentCard;
		this.childCard = childCard;
		this.prediction = prediction;
		this.actualResult = actualResult;
		this.win = win;
	}

	public Card getParentCard() {
		return parentCard;
	}

	public Card getChildCard() {
		return childCard;
	}

	public Comparison getPrediction() {
		return prediction;
	}

	public Comparison getActualResult() {
		return actualResult;
	}

	public boolean isWin() {
		return win;
	}
	
}
