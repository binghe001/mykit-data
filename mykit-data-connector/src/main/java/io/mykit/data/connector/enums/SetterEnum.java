/**
 * Copyright 2020-9999 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mykit.data.connector.enums;

import io.mykit.data.connector.database.Setter;
import io.mykit.data.connector.database.setter.*;
import io.mykit.data.connector.exception.ConnectorException;

import java.sql.Types;

/**
 * @author binghe
 * @version 1.0.0
 * @description 根据列类型设值
 */
public enum SetterEnum {

    // 常用类型(靠前，减少查找次数)
    VARCHAR(Types.VARCHAR, new VarcharSetter()),
    INTEGER(Types.INTEGER, new IntegerSetter()),
    BIGINT(Types.BIGINT, new BigintSetter()),
    TIMESTAMP(Types.TIMESTAMP, new TimestampSetter()),
    DATE(Types.DATE, new DateSetter()),
    TIME(Types.TIME, new TimeSetter()),

    // 较少使用
    CHAR(Types.CHAR, new CharSetter()),
    LONGVARCHAR(Types.LONGVARCHAR, new LongVarcharSetter()),
    NUMERIC(Types.NUMERIC, new NumericSetter()),
    DECIMAL(Types.DECIMAL, new DecimalSetter()),
    NULL(Types.NULL, new NullSetter()),

    // 很少使用
    TINYINT(Types.TINYINT, new TinyintSetter()),
    SMALLINT(Types.SMALLINT, new SmallintSetter()),
    DOUBLE(Types.DOUBLE, new DoubleSetter()),
    FLOAT(Types.FLOAT, new FloatSetter()),
    REAL(Types.REAL, new RealSetter());

    private int type;

    private Setter setter;

    SetterEnum(int type, Setter setter) {
        this.type = type;
        this.setter = setter;
    }

    public static Setter getSetter(int type) throws ConnectorException {
        for (SetterEnum e : SetterEnum.values()) {
            if (e.getType() == type) {
                return e.getSetter();
            }
        }
        throw new ConnectorException(String.format("Setter type \"%s\" does not exist.", type));
    }

    public int getType() {
        return type;
    }

    public Setter getSetter() {
        return setter;
    }
}
