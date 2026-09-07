package jp.co.sfrontier.ss3.highandlow.record;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 対戦結果のファイルへの記録を管理するクラス
 */
public class FileGameResultRecorder implements GameResultRecorder {

	/** 出力先のファイルパス */ 
	private final String filePath;

	/**
	 * FileGameResultRecorderを生成するコンストラクタ
	 * @param filePath 出力先ファイルパス
	 */
	public FileGameResultRecorder(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 対戦結果をファイルへ記録する
	 * @param result GameResult
	 */
	@Override
	public void record(GameResult result) throws IOException {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {

			writer.write(
					"親=" + result.getParentCard()
							+ ", 子=" + result.getChildCard()
							+ ", 予想=" + result.getPrediction()
							+ ", 結果=" + result.getActualResult()
							+ ", 勝敗=" + (result.isWin() ? "勝ち" : "負け"));

			writer.newLine();
		}
	}

}
