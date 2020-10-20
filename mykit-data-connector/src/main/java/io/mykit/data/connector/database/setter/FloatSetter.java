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
package io.mykit.data.connector.database.setter;

import io.mykit.data.connector.database.AbstractSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author binghe
 * @version 1.0.0
 * @description FloatSetter
 */
public class FloatSetter extends AbstractSetter {
    @Override
    protected void set(PreparedStatement ps, int i, Object val) throws SQLException {
        ps.setFloat(i, Float.parseFloat(String.valueOf(val)));
    }
}
