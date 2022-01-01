package org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple.hamza;

import java.util.*;



import org.sj.nounConjugation.trilateral.augmented.modifier.AbstractEinMahmouz;
import org.sj.verbConjugation.trilateral.Substitution.*;

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
public class EinMahmouz extends AbstractEinMahmouz {
    List substitutions = new LinkedList();

    public EinMahmouz() {

        substitutions.add(new InfixSubstitution("اءٍ","اءٍ"));// EX: (متراءٍ، متشاءٍ)
        substitutions.add(new InfixSubstitution("ْءٍ","ْءٍ"));// EX: (مُنْءٍ، مَمْءٍ)
        substitutions.add(new InfixSubstitution("َءٍ","َأٍ"));// EX: (مُنْفَأٍ)
        substitutions.add(new InfixSubstitution("َءٍّ","َأٍّ"));// EX: (مُرَأٍّ، مُتَرَأٍّ)
        substitutions.add(new InfixSubstitution("ءُ","ؤُ"));// EX: (مُنْؤُون، مُمْؤُون، مُراؤُون)
        substitutions.add(new InfixSubstitution("ءِ","ئِ"));// EX: (مُشْئِمٌ، مُبْتَئِسٌ، مُتَسائِلٌ، مُسْتَرئِفٌ)
        substitutions.add(new InfixSubstitution("ءِّ","ئِّ"));// EX: (مُرَئِّسٌ، مُتَرَئِّفٌ)
        substitutions.add(new InfixSubstitution("ءُّ","ؤُّ"));// EX: (مُرَؤُّون)
        substitutions.add(new InfixSubstitution("ْءَ","ْأَ"));// EX: (مُجْأَلٌّ)

    }


    public List getSubstitutions() {
        return substitutions;
    }
}
