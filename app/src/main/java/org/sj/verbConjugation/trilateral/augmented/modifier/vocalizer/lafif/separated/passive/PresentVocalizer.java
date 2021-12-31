package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.separated.passive;

import java.util.*;

import org.sj.verbConjugation.trilateral.Substitution.*;
import org.sj.verbConjugation.trilateral.augmented.modifier.*;
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult;


public class PresentVocalizer extends SubstitutionsApplier implements IAugmentedTrilateralModifier {

    private List substitutions = new LinkedList();

    public PresentVocalizer() {
        substitutions.add(new SuffixSubstitution("َيُ", "َى")); // EX: (يوصَى، يُوَصَّى، يُوالَى، يُتَّقَى، يُتَوارَى، يُتَوَلَّى، يُسْتَوْفَى)
        substitutions.add(new SuffixSubstitution("َيَ", "َى")); // EX: (لن يُوصَى، يُوَصَّى، يُوالَى، يُتَّقَى، يُتَوارَى، يُتَوَلَّى، يُسْتَوْفَى)
        substitutions.add(new SuffixSubstitution("َيْ", "َ")); // EX: (لم يُوصَ، يُوَصَّ، يُوالَ، يُتَّقَ، يُتَوارَ، يُتَوَلَّ، يُسْتَوْفَ)
        substitutions.add(new InfixSubstitution("َيِي", "َيْ")); // EX: (أنتِ تُوصَيْنَ، تُوَصَّيْنَ، تُوالَين، تُتَّقَين، تُتَوارَين، تُتَوَلَّين، تُسْتَوْفَين)
        substitutions.add(new InfixSubstitution("َيُو", "َوْ")); // EX: (أنتم تُوصَوْنَ، تُوَصَّوْنَ، تُوالَون، تُتَّقَون، تُتَوارَوْن، تُتَوَلَّوْن، تُسْتَوْفَون)
        substitutions.add(new InfixSubstitution("َيُن", "َوُن")); // EX: (أنتم تُوصَوُنَّ، تُوَصَّوُنَّ، تُوالَوُنَّ، تُتَّقَوُنَّ، تُتَوارَوُنَّ، تُتَوَلَّوُنَّ، تُسْتَوْفَوُنَّ)

    }

    public List getSubstitutions() {
        return substitutions;
    }

    public boolean isApplied(MazeedConjugationResult mazeedConjugationResult) {
        int kov = mazeedConjugationResult.getKov();
        int formulaNo = mazeedConjugationResult.getFormulaNo();

        switch (kov) {
        case 29:
            switch (formulaNo) {
            case 5:
            case 7:
            case 9:
                return true;
            }

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
        }

        return false;
    }



}
