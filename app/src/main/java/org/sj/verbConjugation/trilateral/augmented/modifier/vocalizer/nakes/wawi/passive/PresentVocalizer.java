package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.passive;

import java.util.*;

import org.sj.verbConjugation.trilateral.Substitution.*;
import org.sj.verbConjugation.trilateral.augmented.modifier.*;
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult;


public class PresentVocalizer extends SubstitutionsApplier implements IAugmentedTrilateralModifier {

    private List substitutions = new LinkedList();

    public PresentVocalizer() {
        substitutions.add(new SuffixSubstitution("وُ", "ى")); // EX: (يُدْنَى، يُسَمَّى، يحابَى، يرتضى، يرعوَى، يُتسامى، يُتزكَّى، يسترضى، يحلولى)
        substitutions.add(new SuffixSubstitution("وَ", "ى")); // EX: (لن يُدْنَى، يُسَمَّى، يحابَى، يرتضى، يُتسامى، يُتزكَّى، يسترضى، يحلولى)
        substitutions.add(new SuffixSubstitution("وْ", "")); // EX: (لم يُدْنَ، يُسَمَّ، يحابَ، يرتضَ، يرعوَ، يُتسامَ، يُتزكَّ، يسترضَ، يحلولَ)
        substitutions.add(new InfixSubstitution("وِي", "يْ")); // EX: (أنتِ تُدْنَيْنَ، تُسَمَّيْن، تحابَين، ترتضَين، تُتسامَين، تُتزكَّين، تسترضَين، تحلولَين)
        substitutions.add(new InfixSubstitution("وَا", "يَا")); // EX: (أنتما تُدْنَيان، تُسَمَّيان، تحابَيان، ترتضَيان، تُتسامَيان، تُتزكَّيان، تسترضَيان، تحلولَيان)
        substitutions.add(new InfixSubstitution("وُو", "وْ")); // EX: (أنتم تُدْنَوْن، تُسَمَّوْن، تحابَوْن، ترتضَوْن، تُتسامَوْن، تُتزكَّوْن، تسترضَوْن، تحلولَوْن)
        substitutions.add(new InfixSubstitution("وْن", "يْن")); // EX: (أنتن تُدْنَيْنَ، تُسَمَّيْن، تحابَين، ترتضَين، تُتسامَين، تُتزكَّين، تسترضَين، تحلولَين)
        substitutions.add(new InfixSubstitution("وَن", "يَن")); // EX: (هو يُدْنَيَنَّ، يُسَمَّيَنَّ، يحابَيَنَّ، يرتضَيَنَّ، يُتسامَيَنَّ، يُتزكَّيَنَّ، يسترضَيَنَّ، يحلولَيَنَّ)
        substitutions.add(new InfixSubstitution("وِن", "يِن")); // EX: (أنتِ تُدْنَيِنَّ، تُسَمَّيِنَّ، تحابَيِنَّ، ترتضَيِنَّ، تُتسامَيِنَّ، تُتزكَّيِنَّ، تسترضَيِنَّ، تحلولَيِنَّ)

    }


    public List getSubstitutions() {
        return substitutions;
    }

    public boolean isApplied(MazeedConjugationResult mazeedConjugationResult) {
        int kov = mazeedConjugationResult.getKov();
        int formulaNo = mazeedConjugationResult.getFormulaNo();

        switch (kov) {
        case 21:
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

        case 22:
            switch (formulaNo) {
            case 1:
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
                return true;
            }

        case 23:
            switch (formulaNo) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                return true;
            }

        }
        return false;
    }
}
