package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.connected;

import java.util.*;

import org.sj.verbConjugation.trilateral.Substitution.*;
import org.sj.verbConjugation.trilateral.unaugmented.modifier.*;
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult;

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
public class ImperativeVocalizer extends SubstitutionsApplier implements IUnaugmentedTrilateralModifier {

    private List substitutions = new LinkedList();

    public ImperativeVocalizer() {
        substitutions.add(new SuffixSubstitution("ِيْ", "ِ")); // EX: (اشْوِ)
        substitutions.add(new InfixSubstitution("ِيِن", "ِن")); // EX: (أنتِ اشْوِنَّ)
        substitutions.add(new InfixSubstitution("ِيِ", "ِ")); // EX: (أنتِ اشْوِي)
        substitutions.add(new InfixSubstitution("ِيْ", "ِي")); // EX: (أنتن اشْوِينَ)
        substitutions.add(new InfixSubstitution("ِيُ", "ُ")); // EX: (أنتم اشْوُوا)
        substitutions.add(new SuffixSubstitution("َيْ", "َ")); // EX: (أنتَ اقْوَ، احْيَ)
        substitutions.add(new InfixSubstitution("َيِي", "َيْ")); // EX: (أنتِ اقوَيْ، احْيَيْ)
        substitutions.add(new InfixSubstitution("َيُو", "َوْ")); // EX: (أنتم اقْوَوْا، احْيَوْا)
        substitutions.add(new InfixSubstitution("َيُن", "َوُن")); // EX: (أنتم اقْوَوُنَّ، احيَوُنَّ)
        substitutions.add(new SuffixSubstitution("َوْ", "َ")); // EX: (اسْوَ)
        substitutions.add(new InfixSubstitution("َوِي", "َيْ")); // EX: (أنتِ اسْوَيْ)
        substitutions.add(new InfixSubstitution("َوَن", "َيَن")); // EX: (أنتَ اسْوَيَنَّ)
        substitutions.add(new InfixSubstitution("َوْن", "َيْن")); // EX: (أنتن اسْوَيْنَ)
        substitutions.add(new InfixSubstitution("َوِن", "َيِن")); // EX: (أنتِ اسْوَيِنَّ)
        substitutions.add(new InfixSubstitution("َوُو", "َوْ")); // EX: (أنتم اسْوَوْا)
        substitutions.add(new InfixSubstitution("َوَ","َيَ"));// EX: (أنتما اسْويا)
    }


    public List getSubstitutions() {
        return substitutions;
    }

    public boolean isApplied(ConjugationResult conjugationResult) {
        int kov = conjugationResult.getKov();
        int noc = Integer.parseInt(conjugationResult.getRoot().getConjugation());
        return ((kov == 27 || kov == 28) && (noc == 2)) ||
                (kov == 28 && noc == 4);
    }
}
