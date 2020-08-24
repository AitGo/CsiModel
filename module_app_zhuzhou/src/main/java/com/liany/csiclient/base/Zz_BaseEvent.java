package com.liany.csiclient.base;

/**
 * @创建者 ly
 * @创建时间 2019/6/24
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public interface Zz_BaseEvent {
    void setObject(Object obj);
    Object getObject();

    enum CommonEvent implements Zz_BaseEvent {
        JWTPT_GETCURRENTUSER_SUCCESS,
        JWTPT_GETCURRENTUSER_ERROR,
        JWTPT_GETGPSINFO_SUCCESS,
        JWTPT_GETGPSINFO_ERROR
        ;

        private Object object;
        @Override
        public void setObject(Object obj) {
            this.object = obj;
        }

        @Override
        public Object getObject() {
            return object;
        }
    }
}
