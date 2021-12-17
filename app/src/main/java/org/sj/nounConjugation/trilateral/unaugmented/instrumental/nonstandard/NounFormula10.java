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
public class NounFormula10 extends NonStandardInstrumentalNounFormula{

    public NounFormula10(UnaugmentedTrilateralRoot root, String suffixNo) {
        super(root, suffixNo);
    }

    //to be used in refection getting the formula name
    public NounFormula10() {
    }

    public String form() {
        switch (suffixNo) {
        case 2:
        case 4:
        case 8:
        case 10:
        case 14:
        case 16:
            return root.getC1()+ArabCharUtil.FATHA+ArabCharUtil.Aleph+root.getC2()+ArabCharUtil.DAMMA+ArabCharUtil.WAW+root.getC3()+suffix;
        }

        return "";
    }

    public String getFormulaName() {
        return "فَاعُولَة";
    }

    public String getSymbol() {
        return "N";
    }


}
