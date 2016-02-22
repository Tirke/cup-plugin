package tirke.cupPlugin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import tirke.cupPlugin.psi.CupNamedElement;

/**
 * Created by Tirke on 22/02/2016
 */
public abstract class CupNamedElementImpl extends ASTWrapperPsiElement implements CupNamedElement {

    public CupNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }


}
