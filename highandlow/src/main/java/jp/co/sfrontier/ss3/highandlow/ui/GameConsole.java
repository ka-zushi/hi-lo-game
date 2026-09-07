package jp.co.sfrontier.ss3.highandlow.ui;

import java.util.Scanner;

import jp.co.sfrontier.ss3.highandlow.card.Card;
import jp.co.sfrontier.ss3.highandlow.game.Comparison;

/**
 * ゲーム全体の入出力操作をもつクラス
 */
public class GameConsole {
	
	private final Scanner scanner;

	/**
	 * GameConsoleを生成するコンストラクタ。<br>
	 * scannerは呼び出し側でcloseまですること
	 * @param scanner 
	 */
	public GameConsole(Scanner scanner) {
		this.scanner = scanner;
	}

	public void showRule() {
		System.out.println("========================================");
		System.out.println("          HIGH & LOW GAME");
		System.out.println("========================================");
		System.out.println();
		System.out.println("【ルール】");
		System.out.println();
		System.out.println("1. 最初のカードがランダムに表示されます。");
		System.out.println();
		System.out.println("2. 次に出るカードが、最初のカードと比べて");
		System.out.println("   「大きい」「小さい」「同じ」");
		System.out.println("   のどれになるか予想してください。");
		System.out.println();
		System.out.println("3. 次のカードがランダムに表示されます。");
		System.out.println();
		System.out.println("4. あなたの予想と実際の結果が一致すれば勝ちです！");
		System.out.println();
		System.out.println("5. 勝った場合、今回出たカードが");
		System.out.println("   次の勝負の親カードになります。");
		System.out.println();
		System.out.println("6. ゲームは負けるまで続きます。");
		System.out.println();
		System.out.println("7. 一度引いたカードは、そのゲーム中は山札に戻りません。");
		System.out.println("   再挑戦すると山札はリセットされます！");
		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println("        それではゲームスタート！");
		System.out.println("========================================");
	}
	
	/**
	 * プレイヤーから予想の入力を1～3で受け取る
	 * @return　
	 * <pre>
	 * 1 ⇒ Comparison.HIGH
	 * 2 ⇒ Comparison.LOW
	 * 3 ⇒ Comparison.EQUAL
	 * </pre>
	 */
	public Comparison inputPrediction() {

		while (true) {

			String input = scanner.nextLine();

			switch (input) {
			case "1":
				return Comparison.HIGH;
			case "2":
				return Comparison.LOW;
			case "3":
				return Comparison.EQUAL;
			default:
				System.out.println("1～3を入力してください。");
			}
		}

	}

	/**
	 * 新たにゲームを始めるかをユーザーに聞き、入力させ、入力の判定まで行う。
	 * @return boolean 判定結果 y: true / n: false
	 */
	public boolean askContinue() {
		while (true) {
			System.out.println();
			System.out.print("もう一度ゲームに挑戦しますか？ (y/n) > ");

			String input = scanner.nextLine();

			switch (input.toLowerCase()) {
			case "y":
				return true;
			case "n":
				return false;
			default:
				System.out.println("y または n を入力してください。");
			}
		}
	}
	
	/**
	 * 「card + " が出ました！"」と出力する
	 * @param card Card
	 */
	public void showCard(Card card) {
		System.out.println(card + " が出ました！");
	}

	/**
	 * 次の親カードを出力する
	 * @param card Card
	 */
	public void showCurrentParentCard(Card card) {
		System.out.println("今回出た " + card + " が、次の親カードになります！");
	}

	/**
	 * プレイヤーにカードの予想入力を促す
	 */
	public void showPredictionPrompt() {
		System.out.println();
		System.out.println("次のカードを予想してください！");
		System.out.println("1：大きい");
		System.out.println("2：小さい");
		System.out.println("3：等しい");
		System.out.print("あなたの予想は？ > ");
	}

	/**
	 * 勝敗を出力する
	 * @param isWin 対戦結果
	 */
	public void showResult(boolean isWin) {

		if (isWin) {
			System.out.println();
			System.out.println("★ あなたの勝ちです！ ★");
		} else {
			System.out.println();
			System.out.println("残念！ あなたの負けです。");
			System.out.println("========================================");
			System.out.println("          GAME OVER");
			System.out.println("========================================");
		}
	}

	/**
	 * 山札がなくなったことを出力する
	 */
	public void showDeckEmptyMessage() {
		System.out.println();
		System.out.println("山札がなくなりました！");
		System.out.println("これ以上カードを引けないため、ゲーム終了です。");
	}

	/**
	 * 次の勝負に進むことを出力する
	 */
	public void showNextRoundMessage() {
		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println("次の勝負に進みます！");
		System.out.println("----------------------------------------");
	}

	/**
	 * ゲーム終了を出力する
	 */
	public void showGameEndMessage() {
		System.out.println();
		System.out.println("ゲームを終了します。");
	}

	/**
	 * 新しいゲームを開始することを出力する
	 */
	public void showNewGameMessage() {
		System.out.println();
		System.out.println("========================================");
		System.out.println("        NEW GAME START！");
		System.out.println("========================================");
	}
}
