package tirke.cupPlugin.psi.impl;

import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiRecursiveElementWalkingVisitor;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.util.ArrayUtil;
import com.intellij.util.CommonProcessors;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.Processor;
import com.intellij.util.containers.ContainerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tirke.cupPlugin.psi.CupActionCode;
import tirke.cupPlugin.psi.CupImportDeclaration;
import tirke.cupPlugin.psi.CupNamedElement;
import tirke.cupPlugin.psi.CupPackageDeclaration;
import tirke.cupPlugin.psi.CupParserCode;
import tirke.cupPlugin.psi.CupProduction;
import tirke.cupPlugin.psi.CupPsiElementFactory;
import tirke.cupPlugin.psi.CupStartWith;
import tirke.cupPlugin.psi.CupSymbolDefinition;
import tirke.cupPlugin.psi.CupSymbolId;

/**
 * Created by Tirke on 24/02/2016
 */
public class CupPsiImplUtil {

    @NotNull
    public static String getName(PsiNameIdentifierOwner element) {
        return Objects.requireNonNull(element.getNameIdentifier()).getText();
    }

    @NotNull
    public static PsiNameIdentifierOwner setName(PsiNameIdentifierOwner element, String newName) {
        Objects.requireNonNull(element.getNameIdentifier()).
                replace(CupPsiElementFactory.createSymbolFromText(element.getProject(), newName));
        return element;
    }

    @NotNull
    public static PsiElement getNameIdentifier(CupNamedElement element) {
        return element.getIdentifier();
    }

    @NotNull
    public static PsiReference getReference(CupSymbolId symbolId) {
        return new PsiReferenceBase<>(symbolId,
                TextRange.from(0, symbolId.getTextRange().getLength())) {
            @Nullable
            @Override
            public PsiElement resolve() {
                final String name = getElement().getIdentifier().getText();
                CommonProcessors.FindFirstProcessor<CupSymbolDefinition> processor =
                        new CommonProcessors.FindFirstProcessor<>() {
                            @Override
                            protected boolean accept(CupSymbolDefinition cupSymbolDefinition) {
                                return Comparing.equal(cupSymbolDefinition.getName(), name);
                            }
                        };
                processSymbolsVariants(getElement(), processor);
                return processor.getFoundValue();
            }

            @org.jetbrains.annotations.NotNull
            @Override
            public Object[] getVariants() {
                CommonProcessors.CollectProcessor<CupSymbolDefinition> processor =
                        new CommonProcessors.CollectProcessor<>();
                processSymbolsVariants(getElement(), processor);
                return ArrayUtil.toObjectArray(processor.getResults());
            }

            @Override
            public PsiElement handleElementRename(@NotNull String newElementName)
                    throws IncorrectOperationException {
                return getElement().getIdentifier().replace(
                        CupPsiElementFactory.createSymbolFromText(getElement().getProject(), newElementName));
            }
        };

    }

    private static boolean processSymbolsVariants(PsiElement context,
                                                  Processor<CupSymbolDefinition> processor) {
        final PsiFile file = context.getContainingFile();
        List<CupSymbolDefinition> symbols = CachedValuesManager.getCachedValue(
                file, () -> CachedValueProvider.Result.create(computeDefinitions(file, CupSymbolDefinition.class), file)
        );
        return ContainerUtil.process(symbols, processor);
    }


    public static <T> List<T> computeDefinitions(PsiFile file, final Class<T> tClass) {
        final List<T> result = new ArrayList<>();
        file.acceptChildren(new PsiRecursiveElementWalkingVisitor() {
            @Override
            public void visitElement(@NotNull PsiElement element) {
                if (tClass.isInstance(element)) {
                    result.add((T) element);
                } else if (!(element instanceof CupPackageDeclaration) &&
                        !(element instanceof CupImportDeclaration) &&
                        !(element instanceof CupActionCode) &&
                        !(element instanceof CupParserCode) &&
                        !(element instanceof CupStartWith) &&
                        !(element instanceof CupProduction)) {
                    super.visitElement(element);
                }
            }
        });
        return result;

    }


}
