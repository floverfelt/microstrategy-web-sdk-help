package transforms;

import com.microstrategy.web.app.Shortcut;
import com.microstrategy.web.app.transforms.TransformContext;
import com.microstrategy.web.app.transforms.contextmenus.AbstractFolderContextMenuItemBuilder;
import com.microstrategy.web.objects.WebObjectInfo;

public class FolderContextMenuTransform extends AbstractFolderContextMenuItemBuilder {

    public FolderContextMenuTransform(Shortcut shortcut) {
        super(shortcut);
    }

    /**
     * The result of this method is AND'ed with the result of isActionEnabled
     *
     * @return non-zero integer to display menu, 0 to not display
     */
    protected int getContextMenuFlag() {
        return 1;
    }

    protected boolean isActionEnabled(TransformContext transformContext) {
        // The object the context menu is being rendered for
        WebObjectInfo targetObject = getObjectInfo(transformContext);

        // Null protection
        if (targetObject == null) {
            return false;
        }
        // Pulling into own if block for clarity
        if ("your-guid-here".equalsIgnoreCase(targetObject.getID())) {
            return true;
        }

        // Default to not display
        return false;
    }
}
