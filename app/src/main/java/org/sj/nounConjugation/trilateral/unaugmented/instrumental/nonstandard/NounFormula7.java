package org.sj.nounConjugation.trilateral.unaugmented.instrumental.nonstandard;

import org.sj.verbConjugation.trilateral.unaugmented.*;
import org.sj.verbConjugation.util.*;
import org.sj.nounConjugation.trilateral.unaugmented.instrumental.*;

/**
 * <p>Title: Sarf Program</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ALEXO</p>
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
public class NounFormula7 extends NonStandardInstrumentalNounFormula{

    public NounFormula7(UnaugmentedTrilateralRoot root, String suffixNo) {
        super(root, suffixNo);
    }

    //to be used in refection getting the formula name
    public NounFormula7() {
    }

    public String form() {
        switch (suffixNo) {
        case 2:
        case 4:
        case 8:
        case 10:
        case 14:
        case 16:
            return root.getC1()+ArabCharUtil.DAMMA+root.getC2()+ArabCharUtil.SKOON+root.getC3()+suffix;
        }
        return "";
    }

    public String getFormulaName() {
        return "فُعْلَة";
    }

    public String getSymbol() {
        return "K";
    }


}
