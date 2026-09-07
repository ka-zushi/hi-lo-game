package jp.co.sfrontier.ss3.highandlow.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.co.sfrontier.ss3.highandlow.exception.DeckEmptyException;

/**
 * カード52枚をCardクラスとして合計52オブジェクト作成し、List<Card> deck で山札として管理するクラス
 */
public class Deck {
	
	/** 52枚のカードをもつリスト。要素はコンストラクタ内で作成する。 */
	private final List<Card> deck = new ArrayList<>();
	
	/**
	 * Deckクラスを作成するコンストラクタ。<br>
	 * カード1枚(計52枚を List<Card> deck に詰める)
	 */
	public Deck() {
		
		for(Suit suit : Suit.values()) {
			for(Rank rank : Rank.values()) {
				//カード1枚を作成
				Card card = new Card(suit,rank);
				//山札に加える
				deck.add(card);
			}
		}
	}
	
	/**
	 * List<Card> deck の要素をランダムに置き換える<br>
	 * 山札をシャッフルする際に使用する。
	 */
	public void shuffleCard() {
		Collections.shuffle(deck);
	}
	
	/**
	 * 山札が空かどうかの判定
	 * @return boolean　判定結果
	 */
	public boolean isDeckEmpty() {
	    return deck.isEmpty();
	}
	
	/**
	 * 山札から先頭のカードを取得する<br>
	 * 山札がないのに呼び出した場合は、例外を発生させる
	 * @return Card 山札の先頭のカード1枚
	 * @throws DeckEmptyException List<Card> deckが空の場合に発生する例外。山札が空なのにカードを引こうとした場合に発生する。
	 */
	public Card drawCard() throws DeckEmptyException {
		
		if(isDeckEmpty()) {
			throw new DeckEmptyException("山札にカードがありません。");
		}
		
	    return deck.remove(0);
	    
	}
}