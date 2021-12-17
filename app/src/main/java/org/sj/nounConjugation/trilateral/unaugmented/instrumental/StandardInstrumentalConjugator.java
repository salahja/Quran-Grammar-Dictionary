package org.sj.nounConjugation.trilateral.unaugmented.instrumental;

import org.sj.nounConjugation.*;
import org.sj.verbConjugation.util.*;
import org.sj.verbConjugation.trilateral.unaugmented.*;
import java.util.*;

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
public class StandardInstrumentalConjugator implements IUnaugmentedTrilateralNounConjugator{
    private StandardInstrumentalConjugator() {
    }

    private static StandardInstrumentalConjugator instance = new StandardInstrumentalConjugator();

    public static StandardInstrumentalConjugator getInstance() {
        return instance;
    }

    private static List formulas = new LinkedList();
    static {
        formulas.add("مِفْعَل");
        formulas.add("مِفْعَلَة");
        formulas.add("مِفْعَال");
        formulas.add("فَعَّالَة");
    }

    public NounFormula createNoun(UnaugmentedTrilateralRoot root, int suffixNo, int formulaNo) {
        String formulaClassName = getClass().getPackage().getName()+".standard.NounFormula"+formulaNo;
        Object [] parameters = {root, suffixNo+""};

        try {
            NounFormula noun = (NounFormula) Class.forName(formulaClassName).getConstructors()[0].newInstance(parameters);
            return noun;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List createNounList(UnaugmentedTrilateralRoot root, int formulaNo) {
        List result = new LinkedList();
        for (int i = 0; i < 18; i++) {
            NounFormula noun = createNoun(root, i, formulaNo);
            result.add(noun);
        }
        return result;
    }

    public List createNounList(UnaugmentedTrilateralRoot root, String formulaName) {
        return createNounList(root, formulas.indexOf(formulaName) + 1);
    }

    public List getAppliedFormulaList(UnaugmentedTrilateralRoot root) {
        //فقط للفعل المتعدي
        if (root.getVerbtype().equals(ArabCharUtil.MEEM) || root.getVerbtype().equals("ك"))
            return formulas;
        return new LinkedList();
    }


}
