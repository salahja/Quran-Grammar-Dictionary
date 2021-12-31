package org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple.vocalizer;

import java.util.*;

import org.sj.nounConjugation.*;
import org.sj.verbConjugation.trilateral.Substitution.*;
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult;
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier;


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
public class YaeiNakesLafifVocalizer extends TrilateralNounSubstitutionApplier implements IAugmentedTrilateralModifier {

    private List substitutions = new LinkedList();

    public YaeiNakesLafifVocalizer() {
        substitutions.add(new SuffixSubstitution("ِيُ", "ِي")); // EX: (هذا المُهْدِي، )
        substitutions.add(new SuffixSubstitution("ِيَ", "ِيَ")); // EX: (رأيتُ المُهْدِيَ، )
        substitutions.add(new SuffixSubstitution("ِيِ", "ِي")); // EX: (مررتُ على المُهْدِي ، )
        substitutions.add(new InfixSubstitution("ِيٌ", "ٍ")); // EX: (هذا مُهْدٍ)
        substitutions.add(new InfixSubstitution("ِيٍ", "ٍ")); // EX: (مررتُ على مُهْدٍ)
        substitutions.add(new InfixSubstitution("ِيُ", "ُ")); // EX: (مُهْدُونَ، )
        substitutions.add(new InfixSubstitution("ِيِ", "ِ")); // EX: (مُهْدِينَ، )
    }

    public boolean isApplied(MazeedConjugationResult mazeedConjugationResult) {
        if (mazeedConjugationResult.getRoot().getC3() != 'ي')
            return false;

        int kov = mazeedConjugationResult.getKov();
        int formulaNo = mazeedConjugationResult.getFormulaNo();

        switch (kov) {
            case 24:
            case 30:
                switch (formulaNo) {
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                        return true;
                }

            case 25:
                switch (formulaNo) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                    case 11:
                        return true;
                }

            case 26:
                switch (formulaNo) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        return true;
                }

            case 27:
                switch (formulaNo) {
                    case 1:
                    case 2:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                        return true;
                }

            case 28:
                switch (formulaNo) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                        return true;
                }

            case 29:
                switch (formulaNo) {
                    case 5:
                    case 7:
                    case 9:
                        return true;
                }

        }
        return false;
    }

    public List getSubstitutions() {
        return substitutions;
    }
}
