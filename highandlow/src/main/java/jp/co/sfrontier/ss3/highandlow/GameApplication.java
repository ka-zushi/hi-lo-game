package jp.co.sfrontier.ss3.highandlow;

import java.io.IOException;
import java.util.Scanner;

import jp.co.sfrontier.ss3.highandlow.card.Deck;
import jp.co.sfrontier.ss3.highandlow.exception.DeckEmptyException;
import jp.co.sfrontier.ss3.highandlow.game.Game;
import jp.co.sfrontier.ss3.highandlow.game.Judge;
import jp.co.sfrontier.ss3.highandlow.record.GameResultRecorder;
import jp.co.sfrontier.ss3.highandlow.record.GameResultRecorderFactory;
import jp.co.sfrontier.ss3.highandlow.ui.GameConsole;

/**
 * アプリケーション全体の進行を管理するクラス
 */
public class GameApplication {

	/**
	 * アプリケーション全体の進行を管理する
	 */
	public void run() {

		Judge judge = new Judge();

		try (Scanner scanner = new Scanner(System.in)) {

			GameConsole gameConsole = new GameConsole(scanner);
			GameResultRecorderFactory recorderFactory = new GameResultRecorderFactory();

			while (true) {
				//再戦するたびに実施する処理

				//山札作成
				Deck deck = new Deck();

				//新しい日時付きresultファイル
				GameResultRecorder recorder = recorderFactory.create();

				Game game = new Game(deck, judge, gameConsole, recorder);

				game.start();

				if (!gameConsole.askContinue()) {
					gameConsole.showGameEndMessage();
					break;
				}

				gameConsole.showNewGameMessage();
			}

		} catch (DeckEmptyException e) {
			System.out.println("山札からカードを引けませんでした。");
			e.printStackTrace();

		} catch (IOException e) {
			System.out.println("対戦結果の記録に失敗しました。");
			e.printStackTrace();
		}
	}
}
