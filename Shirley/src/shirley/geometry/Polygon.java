/*
 *
 *  Copyright (C) 2017 Aaron Powers
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package shirley.geometry;

import isaac.math.Evaluatable;
import isaac.math.LinearMath;
import isaac.math.Matrix;
import isaac.math.Numeric;

public interface Polygon<T extends Evaluatable<T>> {

	public Vertex<T> vertex(int index);

	public int numVertices();

	@SuppressWarnings("unchecked")
	public default Vertex<Numeric> unitNormal() {
		Matrix<T> matrix1 = new Matrix<T>(3, 3);
		Matrix<T> matrix2 = new Matrix<T>(3, 3);
		Matrix<T> matrix3 = new Matrix<T>(3, 3);

		matrix1.set(0, 0, (T) new Numeric(1));
		matrix1.set(0, 1, vertex(0).y());
		matrix1.set(0, 2, vertex(0).z());
		matrix1.set(1, 0, (T) new Numeric(1));
		matrix1.set(1, 1, vertex(1).y());
		matrix1.set(1, 2, vertex(1).z());
		matrix1.set(2, 0, (T) new Numeric(1));
		matrix1.set(2, 1, vertex(2).y());
		matrix1.set(2, 2, vertex(2).z());

		matrix2.set(0, 0, vertex(0).x());
		matrix2.set(0, 1, (T) new Numeric(1));
		matrix2.set(0, 2, vertex(0).z());
		matrix2.set(1, 0, vertex(1).x());
		matrix2.set(1, 1, (T) new Numeric(1));
		matrix2.set(1, 2, vertex(1).z());
		matrix2.set(2, 0, vertex(2).x());
		matrix2.set(2, 1, (T) new Numeric(1));
		matrix2.set(2, 2, vertex(2).z());

		matrix3.set(0, 0, vertex(0).x());
		matrix3.set(0, 1, vertex(0).y());
		matrix3.set(0, 2, (T) new Numeric(1));
		matrix3.set(1, 0, vertex(1).x());
		matrix3.set(1, 1, vertex(1).y());
		matrix3.set(1, 2, (T) new Numeric(1));
		matrix3.set(2, 0, vertex(2).x());
		matrix3.set(2, 1, vertex(2).y());
		matrix3.set(2, 2, (T) new Numeric(1));

		double x = matrix1.determinant();
		double y = matrix2.determinant();
		double z = matrix3.determinant();

		double magnitude = Math.sqrt(x * x + y * y + z * z);

		return new Vertex<Numeric>(x / magnitude, y / magnitude, z / magnitude);

	}

	@SuppressWarnings("unchecked")
	public default double area() {
		Vertex<T> total = new Vertex<T>();
		for(int i=0;i<numVertices();i++){
			Vertex<T> v1 = vertex(i);
			Vertex<T> v2;
			if(i==numVertices()-1){
				v2 = vertex(0);
			} else {
				v2 = vertex(i+1);
			}
			
			Vertex<T> prod = v1.cross(v2);
			
			total.setX((T)new Numeric(total.x().get() + prod.x().get()));
			total.setY((T)new Numeric(total.y().get() + prod.y().get()));
			total.setZ((T)new Numeric(total.z().get() + prod.z().get()));			
		}
		return Math.abs(0.5*LinearMath.dotProduct(total, this.unitNormal()).get());
	}

}
