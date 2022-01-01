package org.sj.verbConjugation.trilateral.unaugmented.passive;

import org.sj.verbConjugation.util.*;
import org.sj.verbConjugation.trilateral.unaugmented.*;

/**
 * يمثل هذا الصف الفعل في صيغة الماضي المبني للمجهول متضمناً الأحرف الثلاثة
 * وحركاتها مع الأحرف الأخيرة المضافة للفعل حسب الضمير
 * <p>Title: Sarf</p>
 * <p>Description: برنامج التصريف</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
public class PassivePastVerb {
    private UnaugmentedTrilateralRoot root;

    //حركة فاء الفعل وهي دائماً  ضمة
    private static final String dpa1 = ArabCharUtil.DAMMA;

    //حركة عين الفعل وهي دائماً كسرة
    private static final String dpa2 = ArabCharUtil.KASRA;

    //حركة لام الفعل حسب الضمير
    private String lastDpa;

    //الأحرف المضافة لنهاية الفعل حسب الضمير
    private String connectedPronoun;

    public PassivePastVerb(UnaugmentedTrilateralRoot root, String lastDpa, String connectedPronoun) {
        this.root = root;
        this.lastDpa = lastDpa;
        this.connectedPronoun = connectedPronoun;
    }

    public String getConnectedPronoun() {
        return connectedPronoun;
    }

    public UnaugmentedTrilateralRoot getRoot() {
        return root;
    }

    public String getLastDpa() {
        return lastDpa;
    }

    public String toString() {
        return root.getC1()+dpa1+root.getC2()+dpa2+root.getC3()+lastDpa+connectedPronoun;
    }

}
