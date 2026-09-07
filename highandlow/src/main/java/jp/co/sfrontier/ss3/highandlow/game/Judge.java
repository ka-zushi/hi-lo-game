package jp.co.sfrontier.ss3.highandlow.game;

import jp.co.sfrontier.ss3.highandlow.card.Card;

/**
 * 
 */
public class Judge {
	
    /**
     * 親のカードと子のカードを比較する。
     *
     * @param parentCard 親のカード
     * @param childCard 子のカード
     * @return Hi-Lo上の比較結果
     */
    public Comparison compare(Card parentCard, Card childCard) {

        int result = childCard.compareTo(parentCard);

        if (result > 0) {
            return Comparison.HIGH;
        }

        if (result < 0) {
            return Comparison.LOW;
        }

        return Comparison.EQUAL;
    }

    /**
     * ユーザーの予想が当たっていたかどうかの判定
     * 
     * @param prediction
     * @param actualResult
     * @return
     */
    public boolean judge(Comparison prediction, Comparison actualResult) {
        return prediction == actualResult;
    }
    
}
