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
    
    /**
     * 表示名に対応するスートを取得する。
     *
     * @param displayName スートの表示名
     * @return 表示名に対応するスート
     * @throws IllegalArgumentException 対応するスートが存在しない場合
     */
    public static Suit fromDisplayName(String displayName) {

        for (Suit suit : values()) {
            if (suit.displayName.equals(displayName)) {
                return suit;
            }
        }

        throw new IllegalArgumentException(
                "存在しないスートです: " + displayName);
    }

    @Override
    public String toString() {
        return displayName;
    }
}
