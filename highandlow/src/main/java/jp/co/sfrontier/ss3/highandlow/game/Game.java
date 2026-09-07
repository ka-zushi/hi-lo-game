package jp.co.sfrontier.ss3.highandlow.game;

import java.io.IOException;

import jp.co.sfrontier.ss3.highandlow.card.Card;
import jp.co.sfrontier.ss3.highandlow.card.Deck;
import jp.co.sfrontier.ss3.highandlow.exception.DeckEmptyException;
import jp.co.sfrontier.ss3.highandlow.record.GameResult;
import jp.co.sfrontier.ss3.highandlow.record.GameResultRecorder;
import jp.co.sfrontier.ss3.highandlow.ui.GameConsole;

/**
 * 1ゲームの進行を管理する
 */
public class Game {

	/** 山札に対する処理 */
	private final Deck deck;

	/** 各種判定用 */
	private final Judge judge;

	/** コンソールへの入出力用 */
	private final GameConsole gameConsole;

	/** リザルト結果の記録用 */
	private final GameResultRecorder recorder;

	/**
	 * Gameクラスを生成するコンストラクター
	 * @param dealer Dealer
	 * @param judge Judge
	 * @param gameConsole GameConsole
	 * @param recorder GameResultRecorder
	 */
	public Game(Deck deck, Judge judge, GameConsole gameConsole, GameResultRecorder recorder) {
		this.deck = deck;
		this.judge = judge;
		this.gameConsole = gameConsole;
		this.recorder = recorder;
	}

	/**
	 * 1ゲームを進行する
	 * @throws DeckEmptyException 空の山札に対してカードを引こうとした場合
	 * @throws IOException ファイルへの入出力で失敗した場合
	 */
	public void start() throws DeckEmptyException, IOException {

		gameConsole.showRule();

		deck.shuffleCard();

		// 親のカードを引く
		Card parentCard = deck.drawCard();
		gameConsole.showCard(parentCard);

		while (true) {
			// ユーザーの予想を受け取る
			gameConsole.showPredictionPrompt();
			Comparison prediction = gameConsole.inputPrediction();

			// 子カードを引けるか確認
			if (deck.isDeckEmpty()) {
				gameConsole.showDeckEmptyMessage();
				return;
			}

			// 子のカードを引く
			Card childCard = deck.drawCard();
			gameConsole.showCard(childCard);

			// Hi-Lo結果を判定
			Comparison actualResult = judge.compare(parentCard, childCard);

			// ユーザーの予想が当たったか判定
			boolean isWin = judge.judge(prediction, actualResult);

			// 勝敗表示
			gameConsole.showResult(isWin);

			//対戦結果記録
			GameResult result = new GameResult(
					parentCard,
					childCard,
					prediction,
					actualResult,
					isWin);

			recorder.record(result);

			if (isWin) {
				// 子が勝った
				// 今回の子カードを、次回の親カードにする
				parentCard = childCard;

				gameConsole.showNextRoundMessage();
				gameConsole.showCurrentParentCard(parentCard);

			} else {
				return;
			}
		}
	}
}
