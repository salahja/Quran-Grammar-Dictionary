package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.lam;

import java.util.*;
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.*;
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
public class ImperativeMahmouz extends AbstractLamMahmouz {
    private List substitutions = new ArrayList();

    public ImperativeMahmouz() {
        substitutions.add(new InfixSubstitution("َءْ","َأْ"));// EX: (ابدَأْ، دَأَ [داء]، شَأْ [شاء])
        substitutions.add(new InfixSubstitution("َءَ","َأَ"));// EX: (ابدَأَا)
        substitutions.add(new InfixSubstitution("َءُ","َؤُ"));// EX: (ابدَؤُوا)
        substitutions.add(new InfixSubstitution("ءِ","ئِ"));// EX: (ابدَئِي، شَئِنَّ، اجرُئِي، بُوئِي، قِيئِي)
        substitutions.add(new InfixSubstitution("ُءُ","ُؤُ"));// EX: (اجرُؤُوا)
        substitutions.add(new InfixSubstitution("ُءَ","ُؤَ"));// EX: (اجرُؤَا)
        substitutions.add(new InfixSubstitution("ُءْ","ُؤْ"));// EX: (اجرُؤْ، بُؤْ، بُؤْنَ، بُؤْنانِّ)
        substitutions.add(new InfixSubstitution("يءَ","يئَ"));// EX: (قِيئَا)
        substitutions.add(new InfixSubstitution("يءُ","يئُ"));// EX: (قِيئُوا)
        substitutions.add(new InfixSubstitution("اءِ","ائِ"));// EX: (شائِي)
        substitutions.add(new InfixSubstitution("اءُ","اؤُ"));// EX: (شاؤُوا)
        substitutions.add(new InfixSubstitution("ِء","ِئ"));// EX: (قِئْ، قِئْنَ، قِئُنَّ)
    }

    public List getSubstitutions() {
        return substitutions;
    }
}
