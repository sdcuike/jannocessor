/**
 * Copyright 2011 Nikolche Mihajlovski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jannocessor.model.variable;

import org.jannocessor.annotation.Calculated;
import org.jannocessor.annotation.DomainModel;
import org.jannocessor.collection.api.PowerList;
import org.jannocessor.model.code.JavaExpression;
import org.jannocessor.model.modifier.FieldModifiers;
import org.jannocessor.model.structure.AbstractJavaStructure;
import org.jannocessor.model.structure.JavaMetadata;

@DomainModel
public interface JavaField extends AbstractJavaVariable {

	@Override
	@Calculated
	JavaField copy();

	FieldModifiers getModifiers();

	JavaExpression getValue();

	PowerList<JavaMetadata> getMetadata();

	@Override
	AbstractJavaStructure getParent();

}
