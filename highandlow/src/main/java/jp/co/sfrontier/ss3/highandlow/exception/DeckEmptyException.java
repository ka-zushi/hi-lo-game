package jp.co.sfrontier.ss3.highandlow.exception;

/**
 * 山札が空の状態でカードを引こうとした場合に発生するチェック例外
 */
public class DeckEmptyException extends Exception {
	
	public DeckEmptyException(String message) {
		super(message);
	}
}