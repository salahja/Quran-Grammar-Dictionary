package org.sj.nounConjugation.trilateral.unaugmented.exaggeration.nonstandard;

import org.sj.verbConjugation.trilateral.unaugmented.*;
import org.sj.verbConjugation.util.*;
import org.sj.nounConjugation.trilateral.unaugmented.exaggeration.NonStandardExaggerationNounFormula;
import org.sj.nounConjugation.*;

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
public class NounFormula8 extends NonStandardExaggerationNounFormula {

    public NounFormula8(UnaugmentedTrilateralRoot root, String suffixNo) {
        super(root, suffixNo);
        if (this.suffixNo == 7 && GenericNounSuffixContainer.getInstance().isInDefiniteMode()) {
            suffix = "ً";
        }
    }

    //to be used in refection getting the formula name
    public NounFormula8() {
    }

    public String form() {
        switch (suffixNo) {
        case 1:
        case 7:
        case 13:
            return root.getC1() + ArabCharUtil.FATHA + ArabCharUtil.Aleph + root.getC2() + ArabCharUtil.KASRA + root.getC3() + ArabCharUtil.FATHA + "ة" + suffix;
        }

        return "";
    }

    public String getFormulaName() {
        return "فَاعِلَة";
    }

    public String getSymbol() {
        return "J";
    }
}
