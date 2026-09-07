package jp.co.sfrontier.ss3.highandlow.record;

import java.io.IOException;

/**
 * 対戦結果の記録を行うためのインターフェース<br>
 * 記録方法や内容を実装側で決める。
 */
public interface GameResultRecorder {
	
	/**
	 * 対戦結果の記録を行う
	 * @param result 
	 * @throws IOException 
	 */
	void record(GameResult result) throws IOException;
}