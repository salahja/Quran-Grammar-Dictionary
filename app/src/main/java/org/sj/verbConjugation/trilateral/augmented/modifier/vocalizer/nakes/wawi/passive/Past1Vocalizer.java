package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.passive;

import java.util.*;

import org.sj.verbConjugation.trilateral.Substitution.*;
import org.sj.verbConjugation.trilateral.augmented.modifier.*;
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult;


public class Past1Vocalizer extends SubstitutionsApplier implements IAugmentedTrilateralModifier {

    private List substitutions = new LinkedList();

    public Past1Vocalizer() {
        substitutions.add(new InfixSubstitution("وْ", "ي")); // EX: (أنا أُدْنِيتُ، حُوبِيتُ، انجُليت، ارتضيت، تُسُومِيتُ، استُرْضِيتُ، احْلُولِيتُ)
        substitutions.add(new InfixSubstitution("وَ", "يَ")); // EX: (هو أُدْنِيَ، حُوبِيَ، انجُلِيَ، ارتضيَ، تُسُومِيَ، استُرْضِيَ، احْلُولِيَ)
        substitutions.add(new InfixSubstitution("ِوُ", "ُ")); // EX: (هم أُدْنُوا، حُوبُوا، انجُلُوا، ارتضُوا، تُسُومُوا، استُرْضُوا، احْلُولُوا)


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
            case 3:
            case 5:
            case 7:
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
                return true;
            }

        case 23:
            switch (formulaNo) {
            case 1:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
                return true;
            }

        }
        return false;
    }
}
