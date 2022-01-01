package org.sj.verbConjugation.trilateral.unaugmented.passive;

import org.sj.verbConjugation.util.*;
import org.sj.verbConjugation.trilateral.unaugmented.*;


/**
 * يمثل هذا الصف الفعل في صيغة المضارع المبني للمجهول متضمناً الأحرف الثلاثة
 * وحركاتها مع ضمير الرفع المنفصل ومع الأحرف الأخيرة المضافة للفعل حسب الضمير
 * <p>Title: Sarf</p>
 * <p>Description: برنامج التصريف</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
public class PassivePresentVerb {
    private UnaugmentedTrilateralRoot root;

    //حرف المضارع
    private String cp;

    //حركة حرف المضارع وهي دائماًً ضمة
    private static final String vcp = ArabCharUtil.DAMMA;

    //حركة فاء الفعل وهي دائماً ثابتة سكون
    private static final String dpr1 = ArabCharUtil.SKOON;

    //حركة فاء الفعل وهي دائماً ثابتة فتحة
    private static final String dpr2 = ArabCharUtil.FATHA;

    //حركة لام الفعل حسب الضمير
    private String lastDpr;

    //الأحرف المضافة لنهاية الفعل حسب الضمير
    private String connectedPronoun;

    public PassivePresentVerb(UnaugmentedTrilateralRoot root, String cp, String lastDpr, String connectedPronoun) {
        this.root = root;
        this.cp = cp;
        this.lastDpr = lastDpr;
        this.connectedPronoun = connectedPronoun;
    }

    public String getConnectedPronoun() {
        return connectedPronoun;
    }

    public String getdpr2() {
        return dpr2;
    }

    public UnaugmentedTrilateralRoot getRoot() {
        return root;
    }

    public String getlastDpr() {
        return lastDpr;
    }

    public String getCp() {
        return cp;
    }

    public String toString() {
        return cp+vcp+root.getC1()+dpr1+root.getC2()+dpr2+root.getC3()+lastDpr+connectedPronoun;
    }

}
