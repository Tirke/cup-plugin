package tirke.cupPlugin;


import com.intellij.openapi.fileTypes.LanguageFileType;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tirke.cupPlugin.icons.CupIcons;

/**
 * Created by Tirke on 13/02/2016
 */
public class CupFileType extends LanguageFileType {

  public static final CupFileType INSTANCE = new CupFileType();

  private CupFileType() {
    super(CupLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public String getName() {
    return "Cup file";
  }

  @NotNull
  @Override
  public String getDescription() {
    return "Cup parser file";
  }

  @NotNull
  @Override
  public String getDefaultExtension() {
    return "cup";
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return CupIcons.FILE;
  }
}
