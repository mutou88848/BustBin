package dust.clientBase.model;

import java.io.Serializable;

/**
 * Created by pony on 2018/11/26.
 */

public class TypeModel implements Serializable {


    /**
     * typeName : 西安球迷
     * typeTime : 2018-11-26 11:41
     * typeId : 26
     */

    private String typeName;
    private String typeTime;
    private int typeId;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeTime() {
        return typeTime;
    }

    public void setTypeTime(String typeTime) {
        this.typeTime = typeTime;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
