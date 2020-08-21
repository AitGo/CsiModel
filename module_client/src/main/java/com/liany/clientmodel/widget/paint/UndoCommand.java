package com.liany.clientmodel.widget.paint;

/**
 * Created by user on 2016/12/16.
 */
public interface UndoCommand {
    public void undo();
    public void redo();
    public boolean canUndo();
    public boolean canRedo();
}
