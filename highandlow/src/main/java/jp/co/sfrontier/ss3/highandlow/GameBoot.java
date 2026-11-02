package jp.co.sfrontier.ss3.highandlow;

import java.io.IOException;

/**
 * アプリを起動するクラス
 */
public class GameBoot {

	/**
	 * エントリーポイント
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		new GameApplication().run();

	}

}