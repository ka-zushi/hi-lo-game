package jp.co.sfrontier.ss3.highandlow.card;

/**
 * トランプで登場するスートをもつ列挙子
 */
public enum Suit {
	SPADE("スペード"), 
	DIA("ダイア"), 
	CLUB("クラブ"), 
	HEART("ハート");
	
	private final String displayName;

    Suit(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
