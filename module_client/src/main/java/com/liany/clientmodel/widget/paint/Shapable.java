package com.liany.clientmodel.widget.paint;

import android.graphics.Path;

/**
 * Created by user on 2016/12/16.
 */
public interface Shapable {
    public Path getPath();

    public FirstCurrentPosition getFirstLastPoint();

    void setShap(ShapesInterface shape);
}
