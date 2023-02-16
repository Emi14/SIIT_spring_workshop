package org.siit.springworkshop.enums;

public enum Gender {
    MALE(1),
    FEMALE(2);

    int dbValue;

    Gender(int i) {
        dbValue = i;
    }

    public Gender fromDbValue(int dbValue) {
        if (dbValue == MALE.dbValue) {
            return MALE;
        }
        return FEMALE;
    }

    public int getDbValue() {
        return dbValue;
    }
}
