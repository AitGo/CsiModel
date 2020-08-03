package com.liany.csiserverapp.diagnose;

/**
 * @创建者 ly
 * @创建时间 2019/4/8
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class CrimeListEntity {

    private boolean isCheck = false;
    private CrimeItem crimeItem;

    public CrimeItem getCrimeItem() {
        return crimeItem;
    }

    public void setCrimeItem(CrimeItem crimeItem) {
        this.crimeItem = crimeItem;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

}
