package tirke.cupPlugin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import tirke.cupPlugin.psi.CupNamedElement;

/**
 * Created by Tirke on 22/02/2016
 */
public abstract class CupNamedElementImpl extends ASTWrapperPsiElement implements CupNamedElement {

    public CupNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    @NotNull
    public String getName() {
        return CupPsiImplUtil.getName(this);
    }

    @NotNull
    @Override
    public PsiElement getNameIdentifier() {
        return CupPsiImplUtil.getNameIdentifier(this);
    }

    @NotNull
    @Override
    public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException {
        return CupPsiImplUtil.setName(this,name);
    }

}
