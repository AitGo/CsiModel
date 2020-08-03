package com.liany.csiserverapp.diagnose;

import java.util.List;

/**
 * @创建者 ly
 * @创建时间 2020/4/2
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class SelectLocationBean {
    private String casetypeKey;
    private String casetype;
    private List<selectLocation> selectLocationList;

    public String getCasetypeKey() {
        return casetypeKey;
    }

    public void setCasetypeKey(String casetypeKey) {
        this.casetypeKey = casetypeKey;
    }

    public String getCasetype() {
        return casetype;
    }

    public void setCasetype(String casetype) {
        this.casetype = casetype;
    }

    public List<selectLocation> getSelectLocationList() {
        return selectLocationList;
    }

    public void setSelectLocationList(List<selectLocation> selectLocationList) {
        this.selectLocationList = selectLocationList;
    }

    public class selectLocation {
        private String selectLocationKey;
        private String selectLocationParentKey;

        public String getSelectLocationKey() {
            return selectLocationKey;
        }

        public void setSelectLocationKey(String selectLocationKey) {
            this.selectLocationKey = selectLocationKey;
        }

        public String getSelectLocationParentKey() {
            return selectLocationParentKey;
        }

        public void setSelectLocationParentKey(String selectLocationParentKey) {
            this.selectLocationParentKey = selectLocationParentKey;
        }
    }
}
