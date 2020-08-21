package com.liany.clientmodel.widget.image;

public interface IFootprintAnalyze {

    final static int MODE_NEW = 0x1;
    final static int MODE_EDIT = 0x2;
    final static int MODE_VIEW = 0x3;
    final static int MODE_NEW_ANALYSIS = 0x4;

    String getFilePath();

    boolean isMode(int mode);

}
