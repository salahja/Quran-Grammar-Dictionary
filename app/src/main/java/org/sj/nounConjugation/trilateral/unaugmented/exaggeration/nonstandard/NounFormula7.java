package org.sj.nounConjugation.trilateral.unaugmented.exaggeration.nonstandard;

import org.sj.verbConjugation.trilateral.unaugmented.*;
import org.sj.verbConjugation.util.*;
import org.sj.nounConjugation.trilateral.unaugmented.exaggeration.NonStandardExaggerationNounFormula;

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
public class NounFormula7 extends NonStandardExaggerationNounFormula {

    public NounFormula7(UnaugmentedTrilateralRoot root, String suffixNo) {
        super(root, suffixNo);
    }

    //to be used in refection getting the formula name
    public NounFormula7() {
    }

    public String form() {
        switch (suffixNo) {
        case 1:
        case 3:
        case 7:
        case 9:
        case 13:
        case 15:
            return root.getC1() + ArabCharUtil.FATHA + ArabCharUtil.Aleph + root.getC2() + ArabCharUtil.DAMMA + ArabCharUtil.WAW + root.getC3() + suffix;
        }

        return "";

    }

    public String getFormulaName() {
        return "فَاعُول";
    }

    public String getSymbol() {
        return "H";
    }


}
