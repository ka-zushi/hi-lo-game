package jp.co.sfrontier.ss3.highandlow.card;

/**
 * トランプの1枚のカードを表すクラス
 */
public class Card implements Comparable<Card> {
	
	/** カードのスート */
	private final Suit suit;
	
	/** カードのランク */
	private final Rank rank;
	
	/**
	 * Cardクラスを生成するコンストラクタ
	 * @param suit 
	 * @param rank 
	 */
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	/**
	 * カードの情報を返す
	 * @return suit + "の" + rank
	 */
	@Override
	public String toString() {
	    return suit + "の" + rank;
	}
	
    /**
     * カードのランクを比較する。
     *
     * @param other 比較対象のカード
     * @return ディーラーと比べて小さい場合は負の値、等しい場合は0、大きい場合は正の値
     */
	@Override
	public int compareTo(Card other) {
	    return this.rank.compareTo(other.rank);
	}
	
}
