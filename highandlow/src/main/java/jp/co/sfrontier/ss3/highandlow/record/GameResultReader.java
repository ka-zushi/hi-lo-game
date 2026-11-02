package jp.co.sfrontier.ss3.highandlow.record;

import java.io.IOException;
import java.util.List;

/**
 * 対戦結果のファイルを読み込むためのインターフェース<br>
 * 読み込む内容を実装側で決める。
 */
public interface GameResultReader {


	    /**
	     * 対戦結果の読み込みを行う
	     *
	     * @return 読み込んだ対戦結果
	     * @throws IOException ファイルの読み込みに失敗した場合
	     */
	    List<GameResult> read() throws IOException;
	}
