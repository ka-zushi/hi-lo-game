package jp.co.sfrontier.ss3.highandlow.record;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import jp.co.sfrontier.ss3.highandlow.card.Card;
import jp.co.sfrontier.ss3.highandlow.card.Rank;
import jp.co.sfrontier.ss3.highandlow.card.Suit;
import jp.co.sfrontier.ss3.highandlow.game.Comparison;

/**
 * ファイルに保存された対戦結果を読み込むクラス。
 */
public class FileGameResultReader implements GameResultReader {

	/** 対戦結果が保存されているディレクトリ */
	private final Path resultDirectory;

	/** 対戦結果に記録されている日時のフォーマット。String ⇒ LocalDateTimeにする際、parseの引数に使用する */
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	public FileGameResultReader(Path resultDirectory) {
		this.resultDirectory = resultDirectory;
	}

	/**
	 * 対戦結果ファイルの1行分の文字列をGameResultに変換する。
	 *
	 * @param line 1対戦分の結果を表す文字列
	 * @return 変換したGameResult
	 */
	private GameResult parse(String line) {

		String[] parts = line.split(", ");

		//各種対戦結果だけを取得する
	    String playedAtText = parts[0].trim().replace("日時=", "");
	    String parentCardText = parts[1].trim().replace("親=", "");
	    String childCardText = parts[2].trim().replace("子=", "");
	    String predictionText = parts[3].trim().replace("予想=", "");
	    String actualResultText = parts[4].trim().replace("結果=", "");
	    String winText = parts[5].trim().replace("勝敗=", "");

	    //親のカード取得
		String[] parentCardParts = parentCardText.split("の");

		Suit parentSuit = Suit.fromDisplayName(parentCardParts[0]);

		Rank parentRank = Rank.fromDisplayName(parentCardParts[1]);

		Card parentCard = new Card(parentSuit, parentRank);

		//子のカード取得
		String[] childCardParts = childCardText.split("の");

		Suit childSuit = Suit.fromDisplayName(childCardParts[0]);

		Rank childRank = Rank.fromDisplayName(childCardParts[1]);

		Card childCard = new Card(childSuit, childRank);

		//String ⇒ LocalDateTime
		LocalDateTime playedAt = LocalDateTime.parse(playedAtText, FORMATTER);

		//プレイヤー予想
		Comparison prediction = Comparison.valueOf(predictionText);

		//実際に出たカード比較の結果
		Comparison actualResult = Comparison.valueOf(actualResultText);

		//勝敗
		boolean win = winText.equals("勝ち");

		return new GameResult(
				parentCard,
				childCard,
				prediction,
				actualResult,
				win,
				playedAt);
	}

	/**
	 * 指定されたディレクトリ内の対戦結果ファイルを読み込む。
	 * @return 読み込んだ対戦結果のリスト
	 * @throws IOException ファイルの読み込みに失敗した場合
	 */
	@Override
	public List<GameResult> read() throws IOException {

		List<GameResult> results = new ArrayList<>();

		try (Stream<Path> paths = Files.list(resultDirectory)) {

			List<Path> files = paths
					.filter(path -> path.toString().endsWith(".txt"))
					.toList();

			for (Path file : files) {

				List<String> lines = Files.readAllLines(file);

				for (String line : lines) {
					results.add(parse(line));
				}
			}
		}

		return results;
	}
}
