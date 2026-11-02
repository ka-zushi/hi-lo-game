package jp.co.sfrontier.ss3.highandlow.card;

/**
 * トランプで登場するランクをもつ列挙子
 */
public enum Rank {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");

	/** トランプの値 */
    private final String displayName;

    Rank(String displayName) {
        this.displayName = displayName;
    }

    /**
     * 表示名に対応するランクを取得する。
     *
     * @param displayName ランクの表示名
     * @return 表示名に対応するランク
     * @throws IllegalArgumentException 対応するランクが存在しない場合
     */
    public static Rank fromDisplayName(String displayName) {

        for (Rank rank : values()) {
            if (rank.displayName.equals(displayName)) {
                return rank;
            }
        }

        throw new IllegalArgumentException(
                "存在しないランクです: " + displayName);
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
