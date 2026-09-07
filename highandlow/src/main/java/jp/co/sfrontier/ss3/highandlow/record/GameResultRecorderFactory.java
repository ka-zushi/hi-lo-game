package jp.co.sfrontier.ss3.highandlow.record;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * resultディレクトリや日時付きファイル名を準備して、1対戦ごとの GameResultRecorder を生成する
 */
public class GameResultRecorderFactory {

	/** 出力先フォルダー名 */
	private static final String RESULT_DIRECTORY = "result";

	/** 対戦日時。ファイル名に仕様する */
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

	/**
	 * 対戦結果の出力先を準備し、GameResultRecorderを生成する
	 *
	 * @return 対戦結果を記録するGameResultRecorder
	 * @throws IOException 出力先の作成に失敗した場合
	 */
	public GameResultRecorder create() throws IOException {

		Path resultDirectory = Path.of(RESULT_DIRECTORY);

		//フォルダがなければ作成してくれる。存在していても例外は出ない。
		Files.createDirectories(resultDirectory);

		String dateTime = LocalDateTime.now().format(FORMATTER);

		//パスの作成
		Path filePath = resultDirectory.resolve(
				"game_result_" + dateTime + ".txt");

		return new FileGameResultRecorder(
				filePath.toString());
	}
}